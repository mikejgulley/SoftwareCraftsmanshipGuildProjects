package com.swcguild.menatworkconsulting.controller;

import com.swcguild.menatworkconsulting.dao.CommentDao;
import com.swcguild.menatworkconsulting.dao.PageDao;
import com.swcguild.menatworkconsulting.dao.PostDao;
import com.swcguild.menatworkconsulting.dao.UserDao;
import com.swcguild.menatworkconsulting.model.Comment;
import com.swcguild.menatworkconsulting.model.DateRange;
import com.swcguild.menatworkconsulting.model.Page;
import com.swcguild.menatworkconsulting.model.Post;
import com.swcguild.menatworkconsulting.model.User;
import java.text.ParseException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletRequest;
import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;
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

/**
 *
 * @author Christopher Becker <no.one.laughed@gmail.com>
 */
@Controller
public class HomeController {

    private PostDao dao;
    private PageDao pageDao;
    private CommentDao commentDao;
    private UserDao userDao;
    private ReCaptcha reCaptcha;

    @Inject
    public HomeController(PostDao dao, PageDao pageDao, CommentDao commentDao, UserDao userDao, ReCaptcha reCaptcha) {
        this.dao = dao;
        this.pageDao = pageDao;
        this.commentDao = commentDao;
        this.userDao = userDao;
        this.reCaptcha = reCaptcha;
    }

    // This method will be invoked by Spring MVC when it sees a request for
    // ContactListMVC/mainAjaxPage.
    @RequestMapping(value = {"/mainAjaxPage"}, method = RequestMethod.GET)
    public String displayMainAjaxPage() {
        // This method does nothing except return the logical name of the 
        // view component (/jsp/home.jsp) that should be invoked in response
        // to this request.
        return "mainAjaxPage";
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String displayHomePage() {
        return "home";
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.PUT)
    @ResponseBody
    public void addComment(Authentication auth, @RequestBody Comment comment) {
		comment.setUserId(userDao.getUserIdByUsername(auth.getName()));
        commentDao.createComment(comment);
    }

    @RequestMapping(value = "/comments/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Comment> displayComments(@PathVariable("id") int id) {
        return commentDao.getCommentsByPostId(id);
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getAllPosts() {
        return dao.getAllPosts();
    }

    //CHECK THIS ENDPOINT
    @RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable("id") int id) {
        dao.deletePost(id);
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Post getPostById(@PathVariable("id") int id) {
        return dao.getPostById(id);
    }

    @RequestMapping(value = "/posts/{tag}", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getPostsByTag(@PathVariable("tag") String tag) {
        return dao.getPostByTag(tag);
    }

    @RequestMapping(value = "/post/daterange", method = RequestMethod.POST)
    @ResponseBody
    public List<Post> getPostsByDateRange(@RequestBody DateRange dateRange) throws ParseException {
        return dao.getPostsByDateRange(dateRange);
    }

    @RequestMapping(value = "/pages", method = RequestMethod.GET)
    @ResponseBody
    public List<Page> getAllPages() {
        return pageDao.getAllPages();
    }

    @RequestMapping(value = "/pages/{id}", method = RequestMethod.GET)
    public String getPage(@PathVariable("id") int id, Model model) {
        Page page = pageDao.getPage(id);
        model.addAttribute("pageTitle", page.getTitle());
        model.addAttribute("pageBody", page.getBody());
        model.addAttribute("pageId", id);
        return "userPage";
    }

    @RequestMapping(value = "/pages/editPage/{id}", method = RequestMethod.GET)
    public String displayPageEdit(@PathVariable("id") int id, Model model) {
        Page page = pageDao.getPage(id);
        model.addAttribute("pageTitle", page.getTitle());
        model.addAttribute("pageBody", page.getBody());
        model.addAttribute("pageId", id);
        return "editPage";
    }

    @RequestMapping(value = "/{ath2}/{path}/editPage", method = RequestMethod.POST)
    @ResponseBody
    public void updatePage(@RequestBody Page page) {
        pageDao.updatePage(page);
    }

    @RequestMapping(value = "/{path}/pages/{id}", method = RequestMethod.POST)
    public String deletePage(@PathVariable("id") int id) {
        pageDao.deletePage(id);
        return "home";
    }

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable("id") int id) {
        commentDao.deleteComment(id);
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String displayRegistration() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@RequestParam("userName") String username,
            @RequestParam("password") String password,
            @RequestParam("recaptcha_challenge_field") String challengeField,
            @RequestParam("recaptcha_response_field") String responseField,
            ServletRequest servletRequest, Model model) {

        String remoteAddress = servletRequest.getRemoteAddr();

        ReCaptchaResponse reCaptchaResponse = this.reCaptcha.checkAnswer(remoteAddress, challengeField, responseField);

        if (userDao.userExists(username)){
            model.addAttribute("usernameMessage", "Username already exists.");
            return "register";
            
        }
        
        if (reCaptchaResponse.isValid()) {
            User user = new User();
            user.setUserName(username);
            user.setPassword(password);
            userDao.createUser(user);
            return "redirect:home";
        } else {
            return "redirect:captchaError";
        }
    }
}
