/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.menatworkconsulting.test;

import com.swcguild.menatworkconsulting.dao.PageDao;
import com.swcguild.menatworkconsulting.dao.PostDao;
import com.swcguild.menatworkconsulting.model.Page;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author apprentice
 */
public class PageDaoDbImplTest {
	
	private PageDao dao;
	private PageDao dao2;
	private Page page;
	
	public PageDaoDbImplTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
		
		page = new Page();
		page.setTitle("title");
		page.setBody("<p>body</p>");
		
		// cleans up the database table before each test
        // Ask Spring for my DAO
        // Specific bean id is referred to here
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = (PageDao) ctx.getBean("pageDao");
		dao2 = (PageDao) ctx.getBean("pageDao");

        // Get a JdbcTemplate to use for cleaning up
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("DELETE FROM pages");
	}
	
	@After
	public void tearDown() {
	}

    @Test
	public void addGetDeletePageTest() {
		dao.createPage(page);
		
		assertEquals(page, dao2.getPage(page.getPageId()));
		
		dao.deletePage(page.getPageId());
		
		assertNull(dao2.getPage(page.getPageId()));
	}
}
