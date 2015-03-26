package com.swcguild.menatworkconsulting.model;

import java.util.Objects;

/**
 *
 * @author Christopher Becker <no.one.laughed@gmail.com>
 */
public class Comment {

    private int commentId;
    private int postId;
    private String comment;
    private int userId;
    private String Date;
	private String user;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 47 * hash + this.commentId;
		hash = 47 * hash + this.postId;
		hash = 47 * hash + Objects.hashCode(this.comment);
		hash = 47 * hash + this.userId;
		hash = 47 * hash + Objects.hashCode(this.Date);
		hash = 47 * hash + Objects.hashCode(this.user);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Comment other = (Comment) obj;
		if (this.commentId != other.commentId) {
			return false;
		}
		if (this.postId != other.postId) {
			return false;
		}
		if (!Objects.equals(this.comment, other.comment)) {
			return false;
		}
		if (this.userId != other.userId) {
			return false;
		}
		if (!Objects.equals(this.Date, other.Date)) {
			return false;
		}
		if (!Objects.equals(this.user, other.user)) {
			return false;
		}
		return true;
	}

    

}
