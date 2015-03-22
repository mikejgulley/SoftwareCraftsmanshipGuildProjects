package com.swcguild.movielibrarymvc.controller;

import com.swcguild.movielibrarymvc.dao.MovieLibraryDao;
import com.swcguild.movielibrarymvc.dto.Movie;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
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
public class StatsController {
//    private MovieLibraryDao dao;
//    
//    @Inject
//    public StatsController(MovieLibraryDao dao) {
//        this.dao = dao;
//    }
    
    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public String displayStats() {
        return "stats";
    }

//    @RequestMapping(value = "/movie", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    @ResponseBody
//    public Movie createMovieOnStatsPage(@RequestBody Movie movie) {
//        dao.addMovie(movie);
//        return movie;
//    }
}
