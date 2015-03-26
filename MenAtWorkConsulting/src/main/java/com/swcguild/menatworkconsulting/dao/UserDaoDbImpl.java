package com.swcguild.menatworkconsulting.dao;

import com.swcguild.menatworkconsulting.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class UserDaoDbImpl implements UserDao {

	private static final String SQL_CREATE_USER
			= "INSERT INTO users (userName, password, enabled) VALUES (?, ?, 1)";

	private static final String SQL_GIVE_AUTHORITY
			= "INSERT INTO authorities (userName, authority) VALUES (?, ?)";

	private static final String SQL_SELECT_ALL_USERS
			= "SELECT * FROM users ORDER BY userName";

	private static final String SQL_DELETE_USER
			= "DELETE FROM users WHERE userName=?";

	private static final String SQL_DELETE_AUTHORITIES
			= "DELETE FROM authorities WHERE userName=?";
	
	private static final String SQL_SELECT_USERNAME
			= "SELECT userId FROM users WHERE userName=?";

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void createUser(User user) {
		jdbcTemplate.update(SQL_CREATE_USER, user.getUserName(), user.getPassword());
		jdbcTemplate.update(SQL_GIVE_AUTHORITY, user.getUserName(), "ROLE_USER");
	}

	@Override
	public boolean userExists(String username) {
		try {
			jdbcTemplate.queryForObject("SELECT userId FROM users WHERE username = ?", Integer.class, username);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}

	@Override
	public List<User> getAllUsernames() {
		return jdbcTemplate.query(SQL_SELECT_ALL_USERS, new UserMapper());
	}

	@Override
	public void deleteUsername(String username) {
		jdbcTemplate.update(SQL_DELETE_AUTHORITIES, username);
		jdbcTemplate.update(SQL_DELETE_USER, username);
	}

	@Override
	public void setAuthorities(String username, String authority) {
		jdbcTemplate.update(SQL_DELETE_AUTHORITIES, username);

		jdbcTemplate.update(SQL_GIVE_AUTHORITY, username, authority);

		if (authority.equals("ROLE_SUBADMIN")) {
			jdbcTemplate.update(SQL_GIVE_AUTHORITY, username, "ROLE_USER");
		}
	}

	@Override
	public int getUserIdByUsername(String username) {
		return jdbcTemplate.queryForObject(SQL_SELECT_USERNAME, Integer.class, username);
	}

	private static final class UserMapper implements ParameterizedRowMapper<User> {

		public User mapRow(ResultSet rs, int i) throws SQLException {
			User user = new User();
			user.setUserId(rs.getInt("userId"));
			user.setUserName(rs.getString("userName"));
			user.setPassword("password");

			return user;
		}
	}
}
