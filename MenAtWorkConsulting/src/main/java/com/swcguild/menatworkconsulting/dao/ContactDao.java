package com.swcguild.menatworkconsulting.dao;

import com.swcguild.menatworkconsulting.model.Contact;
import java.util.List;

/**
 *
 * @author Christopher Becker <no.one.laughed@gmail.com>
 */
public interface ContactDao {
    public void createContact(Contact contact);
    public void deleteContact(int contactId);
    public Contact getContactById(int contactId);
    public List<Contact> getAllContactMessages();
}
