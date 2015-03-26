package com.swcguild.menatworkconsulting.dao;

import com.swcguild.menatworkconsulting.model.User;
import java.util.List;

public interface UserDao {

	public void createUser(User user);

	public boolean userExists(String username);
	
	public List<User> getAllUsernames();
	
	public void deleteUsername(String username);
	
	public void setAuthorities(String username, String authority);
	
	public int getUserIdByUsername(String username);
}
