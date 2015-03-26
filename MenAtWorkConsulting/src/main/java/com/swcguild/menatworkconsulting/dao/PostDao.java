/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.menatworkconsulting.dao;

import com.swcguild.menatworkconsulting.model.DateRange;
import com.swcguild.menatworkconsulting.model.Post;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Christopher Becker <no.one.laughed@gmail.com>
 */
public interface PostDao {
  
    public List<Post> searchPosts(Map<SearchTerm, String> criteria);
    public void createPost(Post post);
    public List<Post> getAllPosts();
    public Post getPostById(int postId);
    public List<Post> getPostByTag(String tag);
    public void updatePost(Post post);
    public void deletePost(int postId);
    public List<Post> getPostsByDateRange(DateRange dateRange) throws ParseException;
    public void createGuestPost(Post post);
    public void deleteGuestPost(int id);
    public void postGuestPost(int id);
    public List<Post> getAllGuestPosts();
}
