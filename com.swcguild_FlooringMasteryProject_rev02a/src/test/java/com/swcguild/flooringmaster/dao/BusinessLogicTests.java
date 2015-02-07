/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmaster.dao;

import com.swcguild.flooringmastery.businesslogic.BusinessLogic;
import com.swcguild.flooringmastery.dto.Order;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class BusinessLogicTests {

    BusinessLogic businessLogicTests = new BusinessLogic();

    public BusinessLogicTests() {
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
    public void testingIdGen() throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader("Data/ordercounter.txt")));
        int testNumber = Integer.parseInt(sc.nextLine()) + 1;
        int testActual;
//        testActual = businessLogicTests.buildOrder("Sullivan", "OH", "Wood", 100).getOrderNumber();
        Order testOrder = new Order();
        testOrder = businessLogicTests.buildOrder("Sullivan", "OH", "Wood", 100);

        assertEquals(testNumber, testOrder.getOrderNumber());

    }

    public void testingCalcMaterialCost() {
        Order testOrder = new Order();
        testOrder = businessLogicTests.buildOrder("Sullivan", "OH", "Wood", 100);
        double testCost = 515.00;

        assertEquals(testCost, testOrder.getMaterialCost(), .001);
    }

    public void testingCalcLaborCost() {
        Order testOrder = new Order();
        testOrder = businessLogicTests.buildOrder("Sullivan", "OH", "Wood", 100);
        double testCost = 475.00;

        assertEquals(testCost, testOrder.getLaborCost(), .001);

    }

    public void testingCalcTax() {
        Order testOrder = new Order();
        testOrder = businessLogicTests.buildOrder("Sullivan", "OH", "Wood", 100);
        double testCost = 61.88;

        assertEquals(testCost, testOrder.getMaterialCost(), .01);

    }

    public void testingCalcTotal() {
        Order testOrder = new Order();
        testOrder = businessLogicTests.buildOrder("Sullivan", "OH", "Wood", 100);
        double testCost = 1051.88;

        assertEquals(testCost, testOrder.getTotal(), .001);

    }

}
