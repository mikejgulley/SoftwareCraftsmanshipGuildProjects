package com.swcguild.movielibrarymvc.dao;

import com.swcguild.movielibrarymvc.dto.Movie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class MovieLibraryDaoInMemImpl implements MovieLibraryDao {

    Map<Integer, Movie> movieMap = new HashMap<>();
    public static int movieIdCounter = 0;
    
    @Override
    public void addMovie(Movie movie) {
        movie.setMovieId(movieIdCounter++);
        movieMap.put(movie.getMovieId(), movie);
    }

    @Override
    public void removeMovie(int movieId) {
        movieMap.remove(movieId);
    }

    @Override
    public void updateMovie(Movie movie) {
        movieMap.put(movie.getMovieId(), movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return new ArrayList<>(movieMap.values());
    }

    @Override
    public Movie getMovieById(int movieId) {
        return movieMap.get(movieId);
    }

    @Override
    public List<Movie> searchMovies(Map<SearchTerm, String> criteria) {
        // Get all search terms from the map
        String titleCriteria = criteria.get(SearchTerm.TITLE);
        String releaseDateCriteria = criteria.get(SearchTerm.RELEASE_DATE);
        String mpaaRatingCriteria = criteria.get(SearchTerm.MPAA_RATING);
        String directorCriteria = criteria.get(SearchTerm.DIRECTOR);
        String studioCriteria = criteria.get(SearchTerm.STUDIO);
        
        // Declare all the predicate conditions
        Predicate<Movie> titleMatches;
        Predicate<Movie> releaseDateMatches;
        Predicate<Movie> mpaaRatingMatches;
        Predicate<Movie> directorMatches;
        Predicate<Movie> studioMatches;
        
        // Placeholder predicate -- always returns true. Used for search terms that are empty
        Predicate<Movie> truePredicate = (c) -> {return true;};
        
        // Assign values to predicates. If a given search term is empty, just
        // assign default truePredicate. Otherwise, assign the predicate that
        // properly filters for the given term.
        titleMatches = (titleCriteria == null || titleCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getTitle().equals(titleCriteria);
        
        releaseDateMatches = (releaseDateCriteria == null || releaseDateCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getReleaseDate().equals(releaseDateCriteria);
        
        mpaaRatingMatches = (mpaaRatingCriteria == null || mpaaRatingCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getMpaaRating().equals(mpaaRatingCriteria);
        
        directorMatches = (directorCriteria == null || directorCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getDirector().equals(directorCriteria);
        
        studioMatches = (studioCriteria == null || studioCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getStudio().equals(studioCriteria);
        
        // Return the list of Movies that match the given criteria. To do this we just
        // AND all the predicates together in a filter operation.
        return movieMap.values().stream()
                .filter(titleMatches
                    .and(releaseDateMatches)
                    .and(mpaaRatingMatches)
                    .and(directorMatches)
                    .and(studioMatches))
                .collect(Collectors.toList());
    }
}
