package com.swcguild.menatworkconsulting.dao;

import com.swcguild.menatworkconsulting.model.Comment;
import com.swcguild.menatworkconsulting.model.Page;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class CommentDaoDbImpl implements CommentDao {

    private static final String SQL_CREATE_COMMENT
            = "INSERT INTO comments (userId, postId, comment, date) VALUES (?,?,?,?)";

    private static final String SQL_GET_COMMENTS
            = "SELECT * FROM comments "
			+ "JOIN users ON users.userId=comments.userId "
			+ "WHERE postId=? "
                        + "ORDER BY commentId";

    private static final String SQL_GET_COMMENT_BY_COMMENT_ID
            = "SELECT * FROM comments "
			+ "JOIN users ON users.userId=comments.userId "
			+ "WHERE commentId=?";

    private static final String SQL_DELETE_COMMENT
            = "DELETE FROM comments WHERE commentId=?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void createComment(Comment comment) {
        jdbcTemplate.update(SQL_CREATE_COMMENT,
                comment.getUserId(),
                comment.getPostId(),
                comment.getComment(),
                comment.getDate()
        );
    }

    @Override
    public void deleteComment(int id) {
        jdbcTemplate.update(SQL_DELETE_COMMENT, id);
    }

    @Override
    public List<Comment> getCommentsByPostId(int id) {
        return jdbcTemplate.query(SQL_GET_COMMENTS, new CommentMapper(), id);
    }

    @Override
    public List<Comment> getCommentByCommentId(int commentId) {
        return jdbcTemplate.query(SQL_GET_COMMENT_BY_COMMENT_ID, new CommentMapper(), commentId);
    }

    private class CommentMapper implements ParameterizedRowMapper<Comment> {

        @Override
        public Comment mapRow(ResultSet rs, int i) throws SQLException {
            Comment comment = new Comment();
            comment.setPostId(rs.getInt("postId"));
            comment.setUserId(rs.getInt("userid"));
            comment.setComment(rs.getString("comment"));
            comment.setCommentId(rs.getInt("commentId"));
			comment.setUser(rs.getString("userName"));
            return comment;
        }
    }
}
