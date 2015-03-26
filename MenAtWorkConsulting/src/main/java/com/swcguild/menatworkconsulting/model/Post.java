/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.menatworkconsulting.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Christopher Becker <no.one.laughed@gmail.com>
 */
public class Post {

    private int postId;
    private String title;
    private String author;
    private int authorId;
    private String body;
    private String date;
    private String expirationDate;
    private String startDate;

//    private LocalDate date;
//    private LocalDate expirationDate;
//    private LocalDate startDate;
    private List<String> tags;

    public int getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getBody() {
        return body;
    }

    public String getDate() {
        return date;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.postId;
        hash = 89 * hash + Objects.hashCode(this.title);
        hash = 89 * hash + Objects.hashCode(this.author);
        hash = 89 * hash + this.authorId;
        hash = 89 * hash + Objects.hashCode(this.body);
        hash = 89 * hash + Objects.hashCode(this.date);
        hash = 89 * hash + Objects.hashCode(this.expirationDate);
        hash = 89 * hash + Objects.hashCode(this.startDate);
        hash = 89 * hash + Objects.hashCode(this.tags);
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
        final Post other = (Post) obj;
        if (this.postId != other.postId) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (this.authorId != other.authorId) {
            return false;
        }
        if (!Objects.equals(this.body, other.body)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.expirationDate, other.expirationDate)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (!Objects.equals(this.tags, other.tags)) {
            return false;
        }
        return true;
    }

}
