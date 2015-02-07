package com.swcguild.dvdlibrary.dao;

import com.swcguild.dvdlibrary.dto.DVD;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Michael J. Gulley
 */
public class DVDLibraryTest {
    
    public DVDLibraryTest() {
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
    
    @Test
    public void test1() {
        DVDLibrary dvdWrite = new DVDLibrary();
        DVDLibrary dvdRead = new DVDLibrary();
        
        // Load DVD Library
        try {
            dvdRead.loadLibrary();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DVDLibraryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Create DVD
        int id = 1234;
        DVD dvdTest = new DVD();
        dvdTest.setId(id);
        dvdTest.setTitle("Notorious");
        dvdTest.setReleaseDate("2009");
        dvdTest.setMpaaRating("R");
        dvdTest.setFormat("DVD");
        dvdTest.setDirectorName("George Tillman, Jr.");
        dvdTest.setStudio("Fox Searchlight Pictures");
        dvdTest.setUserRating("***");
        dvdTest.setUserNote("Biggie Smalls is the illest.");
        
        // Write new DVD to DVD Library
        dvdWrite.addDVD(id, dvdTest);
        try {
            dvdWrite.writeLibrary();
        } catch (IOException ex) {
            Logger.getLogger(DVDLibraryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Compare DVD
        assertEquals(dvdWrite.getDVD(id), dvdRead.getDVD(id));
        
        // Compare if lists are equal
        assertEquals(dvdRead.getAllDvdIDs(), dvdWrite.getAllDvdIDs());
        
        String title = "Notorious";
        assertEquals(dvdRead.getDVDsByTitle(title), dvdWrite.getDVDsByTitle(title));
        String releaseDate = "2009";
        assertEquals(dvdRead.getDVDsByReleaseDate(releaseDate), dvdWrite.getDVDsByReleaseDate(releaseDate));
        String mpaaRating = "R";
        assertEquals(dvdRead.getDVDsByMpaaRating(mpaaRating), dvdWrite.getDVDsByMpaaRating(mpaaRating));
        String format = "DVD";
        assertEquals(dvdRead.getDVDsByFormat(format), dvdWrite.getDVDsByFormat(format));
        String directorName = "George Tillman, Jr.";
        assertEquals(dvdRead.getDVDsByDirectorName(directorName), dvdWrite.getDVDsByDirectorName(directorName));
        String studio = "Fox Searchlight Pictures";
        assertEquals(dvdRead.getDVDsByStudio(studio), dvdWrite.getDVDsByStudio(studio));
        
        // Remove DVD
        dvdWrite.removeDVD(id);
        
        //Confirm if removed
        assertNull(dvdWrite.getDVD(id));
        
    }
}
