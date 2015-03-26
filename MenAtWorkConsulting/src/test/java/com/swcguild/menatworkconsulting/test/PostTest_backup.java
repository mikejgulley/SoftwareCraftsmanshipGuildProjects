///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.swcguild.menatworkconsulting.test;
//
//import com.swcguild.menatworkconsulting.dao.MenAtWorkDao;
//import com.swcguild.menatworkconsulting.model.Post;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import org.junit.After;
//import org.junit.AfterClass;
//import static org.junit.Assert.assertEquals;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
///**
// *
// * @author Christopher Becker <no.one.laughed@gmail.com>
// */
//public class MenAtWorkTest_backup {
//
//    //Declarations
//    MenAtWorkDao dao;
//    Post post1;
//    Post post2;
//    Post post3;
//    Post post4;
//
//    public MenAtWorkTest_backup() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() {
////commented out because this is the name of the Impl and cannot be instantiated
////        dao = new MenAtWorkDao();
//        
//        post1 = new Post();
//        post1.setTitle("Blog Post 1");
//        post1.setAuthor("Christopher");
//        post1.setBody("A very interesting paragraph");
//        post1.setDate(LocalDate.parse("2015-03-10"));
//        post1.setExpirationDate(LocalDate.parse("2015-04-10"));
//        post1.setStartDate(LocalDate.parse("2015-03-11"));
//        
//        ArrayList<String> tagsList1 = new ArrayList<>();
//        tagsList1.add("cats");
//        tagsList1.add("dogs");
//        tagsList1.add("birds");
//        post1.setTags(tagsList1);
//        
//        post2 = new Post();
//        post2.setTitle("Blog Post 2");
//        post2.setAuthor("Mike");
//        post2.setBody("An even more interesting paragraph");
//        post2.setDate(LocalDate.parse("2015-03-11"));
//        post2.setExpirationDate(LocalDate.parse("2015-04-11"));
//        post2.setStartDate(LocalDate.parse("2015-03-12"));
//        
//        ArrayList<String> tagsList2 = new ArrayList<>();
//        tagsList2.add("blue");
//        tagsList2.add("green");
//        tagsList2.add("red");
//        post2.setTags(tagsList2);
//        
//        post3 = new Post();
//        post3.setTitle("Blog Post 3");
//        post3.setAuthor("Joe");
//        post3.setBody("The most interesting paragraph");
//        post3.setDate(LocalDate.parse("2015-03-12"));
//        post3.setExpirationDate(LocalDate.parse("2015-04-12"));
//        post3.setStartDate(LocalDate.parse("2015-03-13"));
//        
//        ArrayList<String> tagsList3 = new ArrayList<>();
//        tagsList3.add("blue");
//        tagsList3.add("green");
//        tagsList3.add("red");
//        post3.setTags(tagsList3);
//        
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    @Test
//    public void TestAddRemoveGetAll(){
//    //ensures that we start with no posts
//    List<Post> testList = dao.getAllPosts();
//    assertEquals(0, testList.size());
//    
//    //tests getAll as part of other tests
//    testList = dao.getAllPosts();
//
//    //tests the add function
//    testList.add(post1);
//    testList.add(post2);
//    testList.add(post3);
//    assertEquals(3, testList.size());
//    
//    //tests the remove function
//    testList.remove(post1);
//    assertEquals(2, testList.size());
//    }
//    
//    @Test
//    public void TestGetById(){
//    dao.createPost(post1);
//    dao.createPost(post2);
//    dao.createPost(post3);
//    List<Post> testList = new ArrayList<>();
//    testList.add(dao.getPostById(2));
//    assertEquals(1, testList.size());
//    }
//    
//    @Test
//    public void TestUpdate(){
//    //post something to HashMap
//        //pull it 
//        //edit it
//        //repost it
//        //compare edit version of post with memory version of post
//    }
//        
//}
