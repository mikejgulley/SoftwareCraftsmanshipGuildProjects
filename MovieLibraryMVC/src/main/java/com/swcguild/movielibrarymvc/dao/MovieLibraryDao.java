package com.swcguild.movielibrarymvc.dao;

import com.swcguild.movielibrarymvc.dto.Movie;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Michael J. Gulley
 */
public interface MovieLibraryDao {
    public void addMovie(Movie movie);
    public void removeMovie(int movieId);
    public void updateMovie(Movie movie);
    public List<Movie> getAllMovies();
    public Movie getMovieById(int movieId);
    public List<Movie> searchMovies(Map<SearchTerm, String> criteria);
}
