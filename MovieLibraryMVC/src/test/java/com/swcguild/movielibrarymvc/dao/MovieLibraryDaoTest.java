package com.swcguild.movielibrarymvc.dao;

import com.swcguild.movielibrarymvc.dto.Movie;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Michael J. Gulley
 */
public class MovieLibraryDaoTest {

    Movie movie1;
    Movie movie2;
    Movie movie3;
    MovieLibraryDao dao;

    public MovieLibraryDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-persistence.xml");
        dao = ctx.getBean("movieLibraryDao", MovieLibraryDao.class);

        // Grab a JdbcTemplate to use for cleaning up
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from movies");

        movie1 = new Movie();
        movie1.setTitle("Alien");
        movie1.setReleaseDate("1979");
        movie1.setMpaaRating("R");
        movie1.setDirector("Ridley Scott");
        movie1.setStudio("Fox");
        movie1.setUserNote("Note note note");

        movie2 = new Movie();
        movie2.setTitle("Robin Hood");
        movie2.setReleaseDate("1964");
        movie2.setMpaaRating("G");
        movie1.setDirector("Awesome Possum");
        movie2.setStudio("Disney");
        movie2.setUserNote("Note note note");

        movie3 = new Movie();
        movie3.setTitle("Raiders of the Lost Ark");
        movie3.setReleaseDate("1980");
        movie3.setMpaaRating("R");
        movie1.setDirector("Steven Spielberg");
        movie3.setStudio("Lucasfilm");
        movie3.setUserNote("Note note note");
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void addGetGetAllRemoveTest() {
        dao.addMovie(movie1);
        Movie actual = dao.getMovieById(movie1.getMovieId());
        assertEquals(movie1, actual);

        dao.removeMovie(movie1.getMovieId());
        assertEquals(0, dao.getAllMovies().size());
    }

    @Test
    public void updateMovieTest() {
        dao.addMovie(movie3);

        Movie updatedMovie = dao.getMovieById(movie3.getMovieId());
        updatedMovie.setTitle("Indiana Jones");
        dao.updateMovie(movie3);

        assertEquals("Indiana Jones", updatedMovie.getTitle());
    }
}
