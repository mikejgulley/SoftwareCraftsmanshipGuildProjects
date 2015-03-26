/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.menatworkconsulting.dao;

import com.swcguild.menatworkconsulting.model.DateRange;
import com.swcguild.menatworkconsulting.model.Post;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class PostDaoDbImpl implements PostDao {

	//PREPARED STATEMENTS
	private static final String SQL_CREATE_POST
			= "INSERT INTO posts (userId, title, body, createDate, expirationDate, startDate) VALUES (?,?,?,?,?,?)";

	private static final String SQL_CREATE_GUEST_POST
			= "INSERT INTO guestPosts (userId, title, body, createDate, expirationDate, startDate) "
			+ "VALUES (?,?,?,?,?,?)";

	private static final String SQL_CREATE_TAG
			= "INSERT INTO tags (tag) VALUES (?)";

	private static final String SQL_GET_TAG_BY_NAME
			= "SELECT * FROM tags WHERE tag=?";

	private static final String SQL_GET_TAG_ID
			= "SELECT tagId FROM tags WHERE tag=?";

	private static final String SQL_GET_POST_TAGS
			= "SELECT tag "
			+ "FROM tags JOIN taggedPosts ON tags.tagId = taggedPosts.tagId "
			+ "WHERE taggedPosts.postId=?";

	private static final String SQL_GET_GUEST_POST_TAGS
			= "SELECT tag "
			+ "FROM tags JOIN guestTaggedPosts ON tags.tagId = guestTaggedPosts.tagId "
			+ "WHERE guestTaggedPosts.postId=?";

	private static final String SQL_GET_TAG_POSTS
			= "SELECT * FROM taggedPosts "
			+ "JOIN tags ON tags.tagId = taggedPosts.tagId "
			+ "WHERE tag=?";

	private static final String SQL_CREATE_TAGGEDPOSTS
			= "INSERT INTO taggedPosts (tagId, postId) VALUES (?, ?)";
	
	private static final String SQL_CREATE_GUEST_TAGGEDPOSTS
			= "INSERT INTO guestTaggedPosts (tagId, postId) VALUES (?, ?)";

	private static final String SQL_DELETE_POST
			= "DELETE FROM posts WHERE postId = ?";
	
	private static final String SQL_DELETE_GUEST_POST
			= "DELETE FROM guestPosts WHERE postId = ?";

	private static final String SQL_DELETE_POST_COMMENTS
			= "DELETE FROM comments WHERE postId=?";

	private static final String SQL_SELECT_POST
			= "SELECT * FROM posts JOIN users ON users.userId = posts.userId WHERE postId = ?";
	
	private static final String SQL_SELECT_GUEST_POST
			= "SELECT * FROM guestPosts JOIN users ON users.userId = guestPosts.userId WHERE postId = ?";

	private static final String SQL_UPDATE_POST
			= "UPDATE posts SET title=?, body=?, startDate=?, expirationDate=? WHERE postId=?";

	private static final String SQL_SELECT_ALL_POSTS
			= "SELECT * FROM posts JOIN users on posts.userId = users.userId WHERE startDate<=NOW() AND expirationDate>NOW() ORDER BY startDate";
	
	private static final String SQL_SELECT_ALL_GUEST_POSTS
			= "SELECT * FROM guestPosts JOIN users on guestPosts.userId = users.userId WHERE startDate<=NOW() AND expirationDate>NOW() ORDER BY startDate";

	private static final String SQL_SELECT_POSTS_IN_DATE_RANGE
			= "SELECT * FROM posts JOIN users ON posts.userId = users.userId "
			+ "WHERE startDate>=? AND startDate<=? AND startDate<=NOW() AND expirationDate>NOW() ORDER BY startDate";

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	//I think this method has to have fields in the same order as the String CREATE_POST above
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void createPost(Post post) {
		try {

			java.util.Date utilDate = new SimpleDateFormat("MM/dd/yyyy").parse(post.getDate());
			java.util.Date utilDate2 = new SimpleDateFormat("MM/dd/yyyy").parse(post.getStartDate());
			java.util.Date utilDate3 = new SimpleDateFormat("MM/dd/yyyy").parse(post.getExpirationDate());
			java.sql.Date sqlCreateDate = new java.sql.Date(utilDate.getTime());
			java.sql.Date sqlStartDate = new java.sql.Date(utilDate2.getTime());
			java.sql.Date sqlExpirationDate = new java.sql.Date(utilDate3.getTime());

			jdbcTemplate.update(SQL_CREATE_POST,
					post.getAuthorId(),
					post.getTitle(),
					post.getBody(),
					sqlCreateDate,
					sqlExpirationDate,
					sqlStartDate);
//        post.getTags(),
			post.setPostId(jdbcTemplate.queryForObject("select LAST_INSERT_ID();", Integer.class));

			// No mortal should be here
			for (String tagName : post.getTags()) {
				try {
					jdbcTemplate.queryForObject(SQL_GET_TAG_BY_NAME, new TagMapper(), tagName);
					jdbcTemplate.update(SQL_CREATE_TAGGEDPOSTS, jdbcTemplate.queryForObject(SQL_GET_TAG_ID, Integer.class, tagName), post.getPostId());
				} catch (EmptyResultDataAccessException e) {
					jdbcTemplate.update(SQL_CREATE_TAG, tagName);
					int tagId = jdbcTemplate.queryForObject("select LAST_INSERT_ID();", Integer.class);
					jdbcTemplate.update(SQL_CREATE_TAGGEDPOSTS, tagId, post.getPostId());
				}
			}

		} catch (ParseException ex) {
			Logger.getLogger(PostDaoDbImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void createGuestPost(Post post) {
		try {

			java.util.Date utilDate = new SimpleDateFormat("MM/dd/yyyy").parse(post.getDate());
			java.util.Date utilDate2 = new SimpleDateFormat("MM/dd/yyyy").parse(post.getStartDate());
			java.util.Date utilDate3 = new SimpleDateFormat("MM/dd/yyyy").parse(post.getExpirationDate());
			java.sql.Date sqlCreateDate = new java.sql.Date(utilDate.getTime());
			java.sql.Date sqlStartDate = new java.sql.Date(utilDate2.getTime());
			java.sql.Date sqlExpirationDate = new java.sql.Date(utilDate3.getTime());

			jdbcTemplate.update(SQL_CREATE_GUEST_POST,
					post.getAuthorId(),
					post.getTitle(),
					post.getBody(),
					sqlCreateDate,
					sqlExpirationDate,
					sqlStartDate);
//        post.getTags(),
			post.setPostId(jdbcTemplate.queryForObject("select LAST_INSERT_ID();", Integer.class));

			// No mortal should be here
			for (String tagName : post.getTags()) {
				try {
					jdbcTemplate.queryForObject(SQL_GET_TAG_BY_NAME, new TagMapper(), tagName);
					jdbcTemplate.update(SQL_CREATE_GUEST_TAGGEDPOSTS, jdbcTemplate.queryForObject(SQL_GET_TAG_ID, Integer.class, tagName), post.getPostId());
				} catch (EmptyResultDataAccessException e) {
					jdbcTemplate.update(SQL_CREATE_TAG, tagName);
					int tagId = jdbcTemplate.queryForObject("select LAST_INSERT_ID();", Integer.class);
					jdbcTemplate.update(SQL_CREATE_GUEST_TAGGEDPOSTS, tagId, post.getPostId());
				}
			}

		} catch (ParseException ex) {
			Logger.getLogger(PostDaoDbImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void deletePost(int postId) {
		jdbcTemplate.update(SQL_DELETE_POST, postId);
		jdbcTemplate.update(SQL_DELETE_POST_COMMENTS, postId);

		clearTags(postId);
	}
	
        @Override
	public void deleteGuestPost(int postId) {
		jdbcTemplate.update(SQL_DELETE_GUEST_POST, postId);
		clearGuestTags(postId);
	}

	@Override
	public void updatePost(Post post) {

		try {
			java.util.Date utilDate = new SimpleDateFormat("MM/dd/yyyy").parse(post.getStartDate());
			java.util.Date utilDate3 = new SimpleDateFormat("MM/dd/yyyy").parse(post.getExpirationDate());
			java.sql.Date sqlStartDate = new java.sql.Date(utilDate.getTime());
			java.sql.Date sqlExpirationDate = new java.sql.Date(utilDate3.getTime());

			jdbcTemplate.update(SQL_UPDATE_POST,
					post.getTitle(),
					post.getBody(),
					sqlStartDate,
					sqlExpirationDate,
					post.getPostId()
			);

			clearTags(post.getPostId());

			for (String tagName : post.getTags()) {

				try {
					jdbcTemplate.queryForObject(SQL_GET_TAG_BY_NAME, new TagMapper(), tagName);
					jdbcTemplate.update(SQL_CREATE_TAGGEDPOSTS, jdbcTemplate.queryForObject(SQL_GET_TAG_ID, Integer.class, tagName), post.getPostId());
				} catch (EmptyResultDataAccessException e) {
					jdbcTemplate.update(SQL_CREATE_TAG, tagName);
					int tagId = jdbcTemplate.queryForObject("select LAST_INSERT_ID();", Integer.class);
					jdbcTemplate.update(SQL_CREATE_TAGGEDPOSTS, tagId, post.getPostId());
				}
			}

		} catch (ParseException ex) {
			Logger.getLogger(PostDaoDbImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@Override
	public List<Post> getAllPosts() {
		List<Post> postList = jdbcTemplate.query(SQL_SELECT_ALL_POSTS, new PostMapper());
		for (Post post : postList) {
			List<String> tagList;
			tagList = jdbcTemplate.query(SQL_GET_POST_TAGS, new TagMapper(), post.getPostId());
			post.setTags(tagList);
		}
		return postList;
	}
	
        @Override
	public List<Post> getAllGuestPosts() {
		List<Post> postList = jdbcTemplate.query(SQL_SELECT_ALL_GUEST_POSTS, new PostMapper());
		for (Post post : postList) {
			List<String> tagList;
			tagList = jdbcTemplate.query(SQL_GET_GUEST_POST_TAGS, new TagMapper(), post.getPostId());
			post.setTags(tagList);
		}
		return postList;
	}

	@Override
	public Post getPostById(int postId) {
		try {
			Post post = jdbcTemplate.queryForObject(SQL_SELECT_POST, new PostMapper(), postId);
			List<String> tagList;
			tagList = jdbcTemplate.query(SQL_GET_POST_TAGS, new TagMapper(), post.getPostId());
			post.setTags(tagList);
			return post;
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
	public Post getGuestPostById(int postId) {
		try {
			Post post = jdbcTemplate.queryForObject(SQL_SELECT_GUEST_POST, new PostMapper(), postId);
			List<String> tagList;
			tagList = jdbcTemplate.query(SQL_GET_GUEST_POST_TAGS, new TagMapper(), post.getPostId());
			post.setTags(tagList);
			return post;
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

	@Override
	public List<Post> getPostByTag(String tag) {
		List<Integer> postIdList = jdbcTemplate.query(SQL_GET_TAG_POSTS, new TaggedPostMapper(), tag);
		List<Post> postList = new ArrayList<>();
		for (int postId : postIdList) {
			postList.add(getPostById(postId));
		}
		return postList;
	}

	@Override
	public List<Post> getPostsByDateRange(DateRange dateRange) throws ParseException {
		java.util.Date utilDate = new SimpleDateFormat("MM/dd/yyyy").parse(dateRange.getDate1());
		java.util.Date utilDate3 = new SimpleDateFormat("MM/dd/yyyy").parse(dateRange.getDate2());
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(utilDate);
		String date2 = new SimpleDateFormat("yyyy-MM-dd").format(utilDate3);

		List<Post> postList = jdbcTemplate.query(SQL_SELECT_POSTS_IN_DATE_RANGE, new PostMapper(), date1, date2);
		for (Post post : postList) {
			List<String> tagList;
			tagList = jdbcTemplate.query(SQL_GET_POST_TAGS, new TagMapper(), post.getPostId());
			post.setTags(tagList);
		}
		return postList;
	}
	
        @Override
	public void postGuestPost(int id) {
		Post post = getGuestPostById(id);
		createPost(post);
		deleteGuestPost(id);
		clearGuestTags(id);
	}

	@Override
	public List<Post> searchPosts(Map<SearchTerm, String> criteria) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	private static final class PostMapper implements ParameterizedRowMapper<Post> {

//this is retrieving data from the db and mapping it to the fields of a post.
		@Override
		public Post mapRow(ResultSet rs, int i) throws SQLException {

			SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

			try {
				String currentDate = formatter.format(parser.parse(rs.getString("createDate")));
				String startDate = formatter.format(parser.parse(rs.getString("startDate")));
				String expirationDate = formatter.format(parser.parse(rs.getString("expirationDate")));

				Post post = new Post();
				post.setAuthor(rs.getString("userName"));
				post.setAuthorId(rs.getInt("userId"));
				post.setTitle(rs.getString("title"));
				post.setBody(rs.getString("body"));
				post.setDate(currentDate);
				post.setExpirationDate(expirationDate);
				post.setStartDate(startDate);
				post.setPostId(rs.getInt("postId"));
				return post;

			} catch (ParseException ex) {
				Logger.getLogger(PostDaoDbImpl.class.getName()).log(Level.SEVERE, null, ex);
				return null;
			}
		}
	}

	private static final class TagMapper implements ParameterizedRowMapper<String> {

		@Override
		public String mapRow(ResultSet rs, int i) throws SQLException {
			return rs.getString("tag");
		}

	}

	private static final class TaggedPostMapper implements ParameterizedRowMapper<Integer> {

		@Override
		public Integer mapRow(ResultSet rs, int i) throws SQLException {
			return rs.getInt("postId");
		}

	}

	private void clearTags(int postId) {
		jdbcTemplate.update("DELETE FROM taggedPosts WHERE postId=?", postId);
	}
	
	private void clearGuestTags(int postId) {
		jdbcTemplate.update("DELETE FROM guestTaggedPosts WHERE postId=?", postId);
	}
}
