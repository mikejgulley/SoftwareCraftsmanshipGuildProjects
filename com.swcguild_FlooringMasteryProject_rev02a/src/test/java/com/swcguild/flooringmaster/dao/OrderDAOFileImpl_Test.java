package com.swcguild.flooringmaster.dao;

import com.swcguild.flooringmastery.businesslogic.BusinessLogic;
import com.swcguild.flooringmastery.dao.OrderDAOFileImpl;
import com.swcguild.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class OrderDAOFileImpl_Test {

    public OrderDAOFileImpl_Test() {
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
//    public void getByDateAndNumberTest() {
//
//        OrderDAOFileImpl odfi = new OrderDAOFileImpl();
//
//        LocalDate date = LocalDate.parse("2013-06-01");
//
//        Order testOrder = new Order();
//        testOrder.setOrderNumber(35);
//        testOrder.setCustomerName("Gulley");
//        testOrder.setState("OH");
//        testOrder.setTaxRate(6.25);
//        testOrder.setProductType("Wood");
//        testOrder.setArea(100.00);
//        testOrder.setCostPerSquareFoot(5.15);
//        testOrder.setLaborCostPerSquareFoot(4.75);
//        testOrder.setMaterialCost(515.00);
//        testOrder.setLaborCost(475.00);
//        testOrder.setTax(61.88);
//        testOrder.setTotal(1051.88);
//
//        assertEquals(testOrder, odfi.getOrderByOrderNumber(date, 35));
//    }
//
//    @Test
//    public void addAndRetrieveTest() {
//
//        OrderDAOFileImpl testWrite = new OrderDAOFileImpl();
//        OrderDAOFileImpl testRead = new OrderDAOFileImpl();
//        BusinessLogic businessLogicTest = new BusinessLogic();
//        LocalDate date = LocalDate.parse("2013-01-01");
//
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddyyyy");
//        String dateString = date.format(dtf);
//
//        Order testOrder = new Order();
//        testOrder.setOrderNumber(26);
//        testOrder.setCustomerName("Bob");
//        testOrder.setState("OH");
//        testOrder.setTaxRate(6.25);
//        testOrder.setProductType("Wood");
//        testOrder.setArea(100.00);
//        testOrder.setCostPerSquareFoot(5.15);
//        testOrder.setLaborCostPerSquareFoot(4.75);
//        testOrder.setMaterialCost(515.00);
//        testOrder.setLaborCost(475.00);
//        testOrder.setTax(61.88);
//        testOrder.setTotal(1051.88);
//
//        testWrite.addOrder(businessLogicTest.buildOrder(testOrder.getCustomerName(), testOrder.getState(), testOrder.getProductType(), testOrder.getArea()));
//        testWrite.saveOrders(date);
//
//        assertEquals(testOrder, testRead.getOrderByOrderNumber(date, 26));
//    }
//    @Test
//    public void testingRemove() {
//        OrderDAOFileImpl testWrite = new OrderDAOFileImpl();
//        OrderDAOFileImpl testRead = new OrderDAOFileImpl();
//        BusinessLogic businessLogicTest = new BusinessLogic();
//        LocalDate date = LocalDate.parse("2013-01-01");
//
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddyyyy");
//        String dateString = date.format(dtf);
//
//        Order testOrder = new Order();
//        testOrder.setOrderNumber(27);
//        testOrder.setCustomerName("Bob");
//        testOrder.setState("OH");
//        testOrder.setTaxRate(6.25);
//        testOrder.setProductType("Wood");
//        testOrder.setArea(100.00);
//        testOrder.setCostPerSquareFoot(5.15);
//        testOrder.setLaborCostPerSquareFoot(4.75);
//        testOrder.setMaterialCost(515.00);
//        testOrder.setLaborCost(475.00);
//        testOrder.setTax(61.88);
//        testOrder.setTotal(1051.88);
//
//        testWrite.addOrder(businessLogicTest.buildOrder(testOrder.getCustomerName(), testOrder.getState(), testOrder.getProductType(), testOrder.getArea()));
//        testWrite.saveOrders(date);
//
////        testWrite.removeOrder(date, testOrder.getOrderNumber());
////
////        assertNull(testRead.getOrderByOrderNumber(date, testOrder.getOrderNumber()));
//        testWrite.removeOrder(date, 27);
////
//        assertNull(testRead.getOrderByOrderNumber(date, 27));
//    }
//    @Test
//    public void saveOrdersTest() {
//        OrderDAOFileImpl testWrite = new OrderDAOFileImpl();
//        OrderDAOFileImpl testRead = new OrderDAOFileImpl();
//        BusinessLogic businessLogicTest = new BusinessLogic();
//        LocalDate date = LocalDate.parse("2015-01-28");
//
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddyyyy");
//        String dateString = date.format(dtf);
//
//        Order testOrder = new Order();
//        testOrder.setOrderNumber(86);
//        testOrder.setCustomerName("Bob");
//        testOrder.setState("OH");
//        testOrder.setTaxRate(6.25);
//        testOrder.setProductType("Wood");
//        testOrder.setArea(100.00);
//        testOrder.setCostPerSquareFoot(5.15);
//        testOrder.setLaborCostPerSquareFoot(4.75);
//        testOrder.setMaterialCost(515.00);
//        testOrder.setLaborCost(475.00);
//        testOrder.setTax(61.88);
//        testOrder.setTotal(1051.88);
//
//        testWrite.addOrder(businessLogicTest.buildOrder(testOrder.getCustomerName(), testOrder.getState(), testOrder.getProductType(), testOrder.getArea()));
//        testWrite.saveOrders(date);
//
//        assertEquals(testOrder, testRead.getOrderByOrderNumber(date, 86));
//    }
}
