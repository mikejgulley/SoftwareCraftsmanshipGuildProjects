package com.swcguild.menatworkconsulting.dao;

import com.swcguild.menatworkconsulting.model.Page;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class PageDaoDbImpl implements PageDao {
	
	private static final String SQL_CREATE_PAGE = "INSERT INTO pages (title, body) VALUES (?,?)";
	private static final String SQL_DELETE_PAGE = "DELETE FROM pages WHERE pageId=?";
	private static final String SQL_UPDATE_PAGE = "UPDATE pages SET title=?, body=? WHERE pageId=?";
	private static final String SQL_SELECT_PAGE = "SELECT * FROM pages WHERE pageId=?";
	private static final String SQL_SELECT_ALL_PAGES = "SELECT * FROM pages";

	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void createPage(Page page) {
		jdbcTemplate.update(SQL_CREATE_PAGE, page.getTitle(), page.getBody());
		page.setPageId(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class));
	}
	
	@Override
	public void deletePage(int id) {
		jdbcTemplate.update(SQL_DELETE_PAGE, id);
	}

	@Override
	public void updatePage(Page page) {
		jdbcTemplate.update(SQL_UPDATE_PAGE, page.getTitle(), page.getBody(), page.getPageId());
	}

	@Override
	public Page getPage(int id) {
		try {
			return jdbcTemplate.queryForObject(SQL_SELECT_PAGE, new PageMapper(), id);
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
	@Override
	public List<Page> getAllPages() {
		return jdbcTemplate.query(SQL_SELECT_ALL_PAGES, new PageMapper());
	}
	
	private class PageMapper implements ParameterizedRowMapper<Page> {
		@Override
		public Page mapRow(ResultSet rs, int i) throws SQLException {
			Page page = new Page();
			page.setPageId(rs.getInt("pageId"));
			page.setTitle(rs.getString("title"));
			page.setBody(rs.getString("body"));
			return page;
		}
	}
}
