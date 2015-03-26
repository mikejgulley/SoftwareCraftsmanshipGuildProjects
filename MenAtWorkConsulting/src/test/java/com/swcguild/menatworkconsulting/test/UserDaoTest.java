/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.menatworkconsulting.test;

import com.swcguild.menatworkconsulting.dao.UserDao;
import com.swcguild.menatworkconsulting.model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author apprentice
 */
public class UserDaoTest {

    private UserDao dao;
    
	public UserDaoTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	// cleans up the database table before each test
        // Ask Spring for my DAO
        // Specific bean id is referred to here
        
            ApplicationContext ctx
                    = new ClassPathXmlApplicationContext("test-applicationContext.xml");
            dao = (UserDao) ctx.getBean("userDao");
            
             // Get a JdbcTemplate to use for cleaning up
            JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
            cleaner.execute("DELETE FROM users");
        }

	@After
	public void tearDown() {
	}

        //User DTO has to change
        
//        @Test
//        public void addGetDeleteUser(){
//        //create new users
//            User user1 = new User();
//            user1.setUserId("9");
//            user1.setUserName("Josef Schweik");
//            user1.setPassword("ptydepy");
//            user1.setEnabled("1");
//                    
//            
//        
//        }
        
}
