package com.swcguild.movielibrarymvc.dao;

import com.swcguild.movielibrarymvc.dto.Movie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Michael J. Gulley
 */
public class MovieLibraryDaoDbImpl implements MovieLibraryDao {
    
    private static final String SQL_INSERT_MOVIE
            = "insert into movies (title, release_date, mpaa_rating, director, studio, user_note) values(?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_MOVIE
            = "delete from movies where movie_id = ?";
    private static final String SQL_SELECT_MOVIE
            = "select * from movies where movie_id = ?";
    private static final String SQL_UPDATE_MOVIE
            = "update movies set title = ?, release_date = ?, mpaa_rating = ?, director = ?, studio = ?, user_note = ? where movie_id = ?";
    private static final String SQL_SELECT_ALL_MOVIES
            = "select * from movies";
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
            
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addMovie(Movie movie) {
        jdbcTemplate.update(SQL_INSERT_MOVIE,
                movie.getTitle(),
                movie.getReleaseDate(),
                movie.getMpaaRating(),
                movie.getDirector(),
                movie.getStudio(),
                movie.getUserNote());
        movie.setMovieId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
    }

    @Override
    public void removeMovie(int movieId) {
        jdbcTemplate.update(SQL_DELETE_MOVIE, movieId);
    }

    @Override
    public void updateMovie(Movie movie) {
        jdbcTemplate.update(SQL_UPDATE_MOVIE,
                movie.getTitle(),
                movie.getReleaseDate(),
                movie.getMpaaRating(),
                movie.getDirector(),
                movie.getStudio(),
                movie.getUserNote(),
                movie.getMovieId());
    }

    @Override
    public List<Movie> getAllMovies() {
        return jdbcTemplate.query(SQL_SELECT_ALL_MOVIES, new MovieMapper());
    }

    @Override
    public Movie getMovieById(int movieId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_MOVIE, new MovieMapper(), movieId);
        } catch (EmptyResultDataAccessException es) {
            return null;
        }
    }

    @Override
    public List<Movie> searchMovies(Map<SearchTerm, String> criteria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    private static final class MovieMapper implements ParameterizedRowMapper<Movie> {

        @Override
        public Movie mapRow(ResultSet rs, int i) throws SQLException {
            Movie movie = new Movie();
            movie.setMovieId(rs.getInt("movie_id"));
            movie.setTitle(rs.getString("title"));
            movie.setReleaseDate(rs.getString("release_date"));
            movie.setMpaaRating(rs.getString("mpaa_rating"));
            movie.setDirector(rs.getString("director"));
            movie.setStudio(rs.getString("studio"));
            movie.setUserNote(rs.getString("user_note"));
            return movie;
        }
        
    }
    
}
