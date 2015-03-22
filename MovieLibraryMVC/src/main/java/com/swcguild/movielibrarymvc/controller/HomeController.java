package com.swcguild.movielibrarymvc.controller;

import com.swcguild.movielibrarymvc.dao.MovieLibraryDao;
import com.swcguild.movielibrarymvc.dto.Movie;
import java.util.List;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Michael J. Gulley
 */
@Controller
public class HomeController {
    private MovieLibraryDao dao;
    
    @Inject
    public HomeController(MovieLibraryDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayHomePage() {
        return "index";
    }
    
    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody public Movie createMovie(@RequestBody Movie movie) {
        dao.addMovie(movie);
        return movie;
    }
    
    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    @ResponseBody public List<Movie> getAllMovies() {
        return dao.getAllMovies();
    }
    
    @RequestMapping(value = "/movie/{id}", method = RequestMethod.GET)
    @ResponseBody public Movie getMovie(@PathVariable("id") int id) {
        return dao.getMovieById(id);
    }
    
    @RequestMapping(value = "/movie/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putMovie(@PathVariable("id") int id, @RequestBody Movie movie) {
        movie.setMovieId(id);
        dao.updateMovie(movie);
    }
    
    @RequestMapping(value = "/movie/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable("id") int id) {
        dao.removeMovie(id);
    }
}
