package com.swcguild.menatworkconsulting.dao;

import com.swcguild.menatworkconsulting.model.Page;
import java.util.List;

public interface PageDao {
	public void createPage(Page page);
	public void deletePage(int id);
	public void updatePage(Page page);
	public Page getPage(int id);
	public List<Page> getAllPages();
}
