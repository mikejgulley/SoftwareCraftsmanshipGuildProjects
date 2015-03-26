/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.menatworkconsulting.dao;

import com.swcguild.menatworkconsulting.model.Comment;
import java.util.List;

public interface CommentDao {

	public void createComment(Comment comment);
	public List<Comment> getCommentsByPostId(int id);
        public List<Comment> getCommentByCommentId(int commentId);
	public void deleteComment(int id);
}
