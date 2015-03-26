/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.menatworkconsulting.test;

import com.swcguild.menatworkconsulting.dao.PostDao;
import com.swcguild.menatworkconsulting.model.Post;
import java.time.LocalDate;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class PostTestDbImmpl {
//although this is called DbImpl, there are no Impl references here
    
    private PostDao dao;

    public PostTestDbImmpl() {
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
        dao = (PostDao) ctx.getBean("postDao");

        // Get a JdbcTemplate to use for cleaning up
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from posts");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addGetDeletePost() {
        // create new posts
        Post post1 = new Post();
        post1.setTitle("Blog Post 1");
        post1.setAuthorId(1);
        post1.setBody("A very interesting paragraph");
        
//        post1.setDate(LocalDate.parse("2015-03-10"));
//        post1.setExpirationDate(LocalDate.parse("2015-04-10"));
//        post1.setStartDate(LocalDate.parse("2015-03-11"));

        //COMMMENTED OUT FOR NOW
//        ArrayList<String> tagsList1 = new ArrayList<>();
//        tagsList1.add("cats");
//        tagsList1.add("dogs");
//        tagsList1.add("birds");
//        post1.setTags(tagsList1);

        dao.createPost(post1);

//JUST EXTRA POSTS TO TEST BUT NOT NEEDED HERE
//        Post post2 = new Post();
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
//        dao.createPost(post2);
//        
//        Post post3 = new Post();
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
//        dao.createPost(post3);
        Post fromDb = dao.getPostById(post1.getPostId());

        assertEquals(fromDb.getPostId(), post1.getPostId());
        assertEquals(fromDb.getTitle(), post1.getTitle());
        assertEquals(fromDb.getAuthorId(), post1.getAuthorId());
        assertEquals(fromDb.getBody(), post1.getBody());
        assertEquals(fromDb.getDate(), post1.getDate());
        assertEquals(fromDb.getExpirationDate(), post1.getExpirationDate());
        assertEquals(fromDb.getStartDate(), post1.getStartDate());
//        assertEquals(fromDb.getTags(), post1.getTags());

        dao.deletePost(post1.getPostId());
        assertNull(dao.getPostById(post1.getPostId()));
    }

    //THIS IS THE NEXT STEP
 /*   
    @Test
    public void addUpdateMovie() {
        // create new movie
        Movie nm = new Movie();
        nm.setTitle("Alien");
        nm.setDirector("Scott");
        nm.setYear("1979");
        nm.setRating("PG");

        dao.createMovie(nm);

        nm.setRating("R");

        dao.updateMovie(nm);

        Movie fromDb = dao.getMovieById(nm.getMovieId());

        assertEquals(fromDb.getMovieId(), nm.getMovieId());
        assertEquals(fromDb.getTitle(), nm.getTitle());
        assertEquals(fromDb.getDirector(), nm.getDirector());
        assertEquals(fromDb.getYear(), nm.getYear());
        assertEquals(fromDb.getRating(), nm.getRating());
    }
    
    */
    

//    @Test
//    public void getAllMovies() {
    // create new movie
//        Contact nc = new Contact();
//        nc.setName("Jimmy Smith");
//        nc.setEmail("jimmy@smith.com");
//        nc.setPhone("1112223333");
//
//        dao.addContact(nc);
//
//        // create new contact
//        Contact nc2 = new Contact();
//        nc2.setName("John Jones");
//        nc2.setEmail("john@jones.com");
//        nc2.setPhone("5556667777");
//
//        dao.addContact(nc);
// 
//        Contact[] cArr = dao.getAllContacts();
//        assertEquals(cArr.length, 2);
//    }
}

//    
//  
//    
//    @Test
//    public void TestAddRemoveGetAll() {
//        //see Mike King for systematic use of this test
//        //ensures that we start with no movies
//        List<Movie> testList = brimpl.getAllMovies();
//        assertEquals(0, testList.size());
//
//        //tests getAll as part of other tests
//        testList = brimpl.getAllMovies();
//
//        //tests the add function
//        testList.add(movie1);
//        testList.add(movie2);
//        testList.add(movie3);
//        assertEquals(3, testList.size());
//
//        //tests the remove function
//        testList.remove(movie1);
//        assertEquals(2, testList.size());
//    }
//
//    @Test
//    public void TestGetById() {
//        brimpl.createMovie(movie1);
//        brimpl.createMovie(movie2);
//        brimpl.createMovie(movie3);
//        List<Movie> testList = new ArrayList<>();
//        testList.add(brimpl.getMovieById(2));
//        assertEquals(1, testList.size());
////    }
//
//}
