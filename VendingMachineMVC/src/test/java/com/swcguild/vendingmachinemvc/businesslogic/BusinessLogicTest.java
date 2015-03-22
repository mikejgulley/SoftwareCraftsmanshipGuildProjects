package com.swcguild.vendingmachinemvc.businesslogic;

import com.swcguild.vendingmachinemvc.dao.VendingMachineDao;
import com.swcguild.vendingmachinemvc.dto.Item;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Michael J. Gulley
 */
public class BusinessLogicTest {
    
    Item item1;
    Item item2;
    Item item3;
    Item item4;
    
    BusinessLogic bLogic;
    VendingMachineDao dao;
    
    public BusinessLogicTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        bLogic = ctx.getBean("businessLogic", BusinessLogic.class);
        dao = ctx.getBean("vendingMachineDao", VendingMachineDao.class);
        
        item1 = new Item();
        item1.setId("A1");
        item1.setName("Donuts");
        item1.setPrice(1.25);
        item1.setQuantity(3);

        item2 = new Item();
        item2.setId("A2");
        item2.setName("M&M's");
        item2.setPrice(0.76);
        item2.setQuantity(2);

        item3 = new Item();
        item3.setId("B1");
        item3.setName("Snickers");
        item3.setPrice(0.75);
        item3.setQuantity(2);

        item4 = new Item();
        item4.setId("B2");
        item4.setName("Doritos");
        item4.setPrice(1.15);
        item4.setQuantity(0);
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
    public void addGetBalanceTest() {
        bLogic.updateBalance(0.0);
        bLogic.addToBalance(55.23);
        double expected = 55.23;
        double actual = bLogic.getCurrentBalance();
        
        assertEquals(expected, actual, 0.01);
    }
    
    @Test
    public void inStockTest() {
        dao.addItem(item1);
        dao.addItem(item2);
        dao.addItem(item3);
        dao.addItem(item4);
        
        assertFalse(bLogic.isItemInStock(item4.getId()));
    }
    
    @Test
    public void areFundsSufficientTest() {
        dao.addItem(item1);
        dao.addItem(item2);
        dao.addItem(item3);
        dao.addItem(item4);
        
        bLogic.updateBalance(0.0);
        double currentBalance = bLogic.addToBalance(1.00);
        assertFalse(bLogic.areFundsSufficient(item4.getId()));
    }
    
    @Test
    public void calcTransactionTest() {
        dao.addItem(item1);
        dao.addItem(item2);
        dao.addItem(item3);
        dao.addItem(item4);
        
        bLogic.updateBalance(2.00);
        
        double expected = 0.75;
        double actual = bLogic.calcTransaction(item1.getId());
        
        assertEquals(expected, actual, 0.01);
    }
    
}
