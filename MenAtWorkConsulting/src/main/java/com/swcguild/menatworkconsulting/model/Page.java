package com.swcguild.menatworkconsulting.model;

import java.util.Objects;

public class Page {
	private int pageId;
	private String title;
	private String body;

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 79 * hash + this.pageId;
		hash = 79 * hash + Objects.hashCode(this.title);
		hash = 79 * hash + Objects.hashCode(this.body);
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
		final Page other = (Page) obj;
		if (this.pageId != other.pageId) {
			return false;
		}
		if (!Objects.equals(this.title, other.title)) {
			return false;
		}
		if (!Objects.equals(this.body, other.body)) {
			return false;
		}
		return true;
	}
	
	
}
