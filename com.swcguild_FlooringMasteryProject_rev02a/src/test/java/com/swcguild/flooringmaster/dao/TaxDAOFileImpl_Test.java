package com.swcguild.flooringmaster.dao;

import com.swcguild.flooringmastery.dao.TaxDAOFileImpl;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michael J. Gulley & Zach Sullivan
 */
public class TaxDAOFileImpl_Test {

    TaxDAOFileImpl tdfi = new TaxDAOFileImpl();

    public TaxDAOFileImpl_Test() {
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
    public void getTaxRateTest() {
        double taxRateOhio = 6.25;
        String state = "OH";
        assertEquals(taxRateOhio, tdfi.getTaxRate(state), 0.01);
    }

    @Test
    public void getStatesTest() {
        ArrayList<String> statesList = new ArrayList<String>();
        statesList.add("OH");
        statesList.add("PA");
        statesList.add("MI");
        statesList.add("IN");
        
        assertEquals(statesList, tdfi.getStates());
    }
}
