package com.swcguild.menatworkconsulting.controller;

import com.swcguild.menatworkconsulting.dao.ContactDao;
import com.swcguild.menatworkconsulting.dao.PageDao;
import com.swcguild.menatworkconsulting.dao.PostDao;
import com.swcguild.menatworkconsulting.dao.UserDao;
import com.swcguild.menatworkconsulting.model.Contact;
import com.swcguild.menatworkconsulting.model.Page;
import com.swcguild.menatworkconsulting.model.Post;
import com.swcguild.menatworkconsulting.model.User;
import java.util.List;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = {"/admin"})
public class AdminController {

	private PostDao postDao;
	private PageDao pageDao;
	private ContactDao contactDao;
	private UserDao userDao;

	@Inject
	public AdminController(PostDao dao, PageDao pageDao, ContactDao contactDao, UserDao userDao) {
		this.postDao = dao;
		this.pageDao = pageDao;
		this.contactDao = contactDao;
		this.userDao = userDao;
	}

	@RequestMapping(value = "")
	public String admin() {
		return "admin";
	}

	@RequestMapping(value = "/addPost", method = RequestMethod.GET)
	public String displayPost() {
		return "addPost";
	}

	// role ADMIN
	@RequestMapping(value = "/addPost", method = RequestMethod.POST)
	@ResponseBody
	public void createPost(Authentication auth, @RequestBody Post post) {
		post.setAuthorId(userDao.getUserIdByUsername(auth.getName()));
		postDao.createPost(post);
	}

	@RequestMapping(value = "/approveGuestPost/{id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void approveGuestPost(@PathVariable("id") int id) {
		postDao.postGuestPost(id);
	}

	@RequestMapping(value = "/allGuestPosts", method = RequestMethod.GET)
	@ResponseBody
	public List<Post> displayAllGuestPosts() {
		return postDao.getAllGuestPosts();
	}

	// role SUBAMIN
	@RequestMapping(value = "/addGuestPost", method = RequestMethod.GET)
	public String displayGuestPostPage() {
		return "addPost";
	}

	@RequestMapping(value = "/submitGuestPost", method = RequestMethod.POST)
	@ResponseBody
	public void createGuestPost(Authentication auth, @RequestBody Post post) {
		post.setAuthorId(userDao.getUserIdByUsername(auth.getName()));
		postDao.createGuestPost(post);
	}

	@RequestMapping(value = "/guestPosts/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteGuestPost(@PathVariable("id") int id) {
		postDao.deleteGuestPost(id);
	}

	// approve/postGuestPost
	@RequestMapping(value = "/addPage", method = RequestMethod.GET)
	public String displayAddPage() {
		return "addPage";
	}

	@RequestMapping(value = "/addPage", method = RequestMethod.POST)
	@ResponseBody
	public void createPage(@RequestBody Page page) {
		pageDao.createPage(page);
	}

	@RequestMapping(value = "/editPost/{id}", method = RequestMethod.GET)
	public String displayPostEdit(@PathVariable("id") int id, Model model) {
		StringBuilder sb = new StringBuilder();

		Post post = postDao.getPostById(id);
		model.addAttribute("postTitle", post.getTitle());
		model.addAttribute("postBody", post.getBody());
		model.addAttribute("postId", id);
		model.addAttribute("postStartDate", post.getStartDate());
		model.addAttribute("postExpirationDate", post.getExpirationDate());

		for (String s : post.getTags()) {
			sb.append(s + ", ");
		}
		model.addAttribute("postTags", sb);
		return "editPost";
	}

	@RequestMapping(value = "{path}/editPost", method = RequestMethod.POST)
	@ResponseBody
	public void updatePost(@RequestBody Post post) {
		postDao.updatePost(post);
	}

	//new material
	@RequestMapping(value = "/readMessages", method = RequestMethod.GET)
	public String displayContacts() {
		return "readMessages";
	}

	@RequestMapping(value = "/messages", method = RequestMethod.GET)
	@ResponseBody
	public List<Contact> getAllMessage() {
		return contactDao.getAllContactMessages();
	}

	@RequestMapping(value = "/messages/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMessage(@PathVariable("id") int id) {
		contactDao.deleteContact(id);
	}

	@RequestMapping(value = "/message/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Contact getMessageById(@PathVariable("id") int id) {
		return contactDao.getContactById(id);
	}

	@RequestMapping(value = "/editUsers", method = RequestMethod.GET)
	public String displayEditUsersPage() {
		return "editUsers";
	}

	@RequestMapping(value = "/guestPosts", method = RequestMethod.GET)
	public String displayGuestPostsPage() {
		return "guestPosts";
	}

	@RequestMapping(value = "/editAuthorities", method = RequestMethod.GET)
	public String displayEditAuthorities() {
		return "editAuthorities";
	}

	@RequestMapping(value = "/authority", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getAllUserNames() {
		return userDao.getAllUsernames();
	}

	@RequestMapping(value = "/editAuthorities", method = RequestMethod.POST)
	public String editAuthority(@RequestParam("username") String username,
			@RequestParam("authority") String authority) {

		switch (authority) {
			case "User":
				authority = "ROLE_USER";
				break;
			case "Guest Poster":
				authority = "ROLE_SUBADMIN";
				break;
			case "Admin":
				authority = "ROLE_ADMIN";
				break;
			default:
				userDao.deleteUsername(username);
				return "redirect:/home";
		}
		userDao.setAuthorities(username, authority);
		return "redirect:/home";
	}
}
