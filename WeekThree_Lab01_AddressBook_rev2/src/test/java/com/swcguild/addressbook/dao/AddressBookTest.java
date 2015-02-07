package com.swcguild.addressbook.dao;

import com.swcguild.addressbook.dto.Address;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Michael J. Gulley & Luke Robins
 */
public class AddressBookTest {

    public AddressBookTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
//    @Test
//    public void test1() {
//        AddressBook ab = new AddressBook();
//        
//        // Create new student
//        String lastName = "Jones";
//        Address addressTest = new Address(lastName);
//        addressTest.setFirstName("JimBob");
//        addressTest.setLastName("Jones");
//        addressTest.setStreetAddress("12345 Bean St.");
//        addressTest.setCity("Canton");
//        addressTest.setState("Ohio");
//        addressTest.setZip("44714");
//        
//        // Add 
//        ab.addAddress(lastName, addressTest);
//        
//        // Counter
//        // assertEquals(1, ab.);
//        
//        // Comparing if person objects are equal
//        assertEquals(ab.getAddress(addressTest.getLastName()), addressTest);
//        
//        // 
//        Address newAddress = ab.getAddress("Jones");
//        assertEquals(newAddress, addressTest);
//        
//        ab.removeAddress("Jones");
//        
//        newAddress = ab.getAddress("Jones");
//        assertNull(newAddress);
//        
//        // assertEquals(addressTest, ab.getAddress(lastName));
//        // assertEquals(addressTest.getLastName(), ab.getAddress(lastName).getLastName());
//    }
    @Test
    public void test2() {
        AddressBook abWrite = new AddressBook();
        AddressBook abRead = new AddressBook();

        // Load Address Book
        try {
            abRead.loadBook();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddressBookTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Create new student
        String id = "999";
        Address addressTest = new Address(id);
        addressTest.setFirstName("Rick");
        addressTest.setLastName("Sanchez");
        addressTest.setStreetAddress("74 E. Water St.");
        addressTest.setCity("Kent");
        addressTest.setState("OH");
        addressTest.setZip("47852");

        // Write new student to Address Book
        abWrite.addAddress(id, addressTest);
        try {
            abWrite.writeBook();
        } catch (IOException ex) {
            Logger.getLogger(AddressBookTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Compare student
        assertEquals(abWrite.getAddressById(id), abRead.getAddressById(id));
    }
}
