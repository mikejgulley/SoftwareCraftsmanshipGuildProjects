package com.swcguild.menatworkconsulting.dao;

import com.swcguild.menatworkconsulting.model.Contact;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Christopher Becker <no.one.laughed@gmail.com>
 */
public class ContactDaoDbImpl implements ContactDao {

//PREPARED STATEMENT
    private static final String SQL_CREATE_CONTACT
            = "insert into contacts (first_name, last_name, email, message) values (?,?,?,?)";
    private static final String SQL_SELECT_ALL_MESSAGES
            = "SELECT * FROM contacts";
    private static final String SQL_DELETE_CONTACT
            = "DELETE FROM contacts WHERE contact_id = ?";
    private static final String SQL_SELECT_POST
            = "SELECT * FROM contacts WHERE contact_id = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void createContact(Contact contact) {
        jdbcTemplate.update(SQL_CREATE_CONTACT,
                contact.getFirstName(),
                contact.getLastName(),
                contact.getEmail(),
                contact.getMessage());
        contact.setContactId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
    }

    @Override
    public List<Contact> getAllContactMessages() {
        List<Contact> contactList = jdbcTemplate.query(SQL_SELECT_ALL_MESSAGES, new ContactDaoDbImpl.ContactMapper());

        return contactList;
    }

    @Override
    public void deleteContact(int contactId) {
        jdbcTemplate.update(SQL_DELETE_CONTACT, contactId);
    }

    @Override
    public Contact getContactById(int contactId) {
        try {
            Contact contact = jdbcTemplate.queryForObject(SQL_SELECT_POST, new ContactDaoDbImpl.ContactMapper(), contactId);
            return contact;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private static final class ContactMapper implements ParameterizedRowMapper<Contact> {

        @Override
        public Contact mapRow(ResultSet rs, int i) throws SQLException {

            Contact contact = new Contact();
            contact.setFirstName(rs.getString("first_name"));
            contact.setLastName(rs.getString("last_name"));
            contact.setEmail(rs.getString("email"));
            contact.setMessage(rs.getString("message"));
            contact.setContactId(rs.getInt("contact_id"));
            return contact;
        }
    }
}
