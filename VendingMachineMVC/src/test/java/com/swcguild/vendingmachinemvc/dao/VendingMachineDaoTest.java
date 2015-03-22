package com.swcguild.vendingmachinemvc.dao;

import com.swcguild.vendingmachinemvc.dto.Item;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Michael J. Gulley
 */
public class VendingMachineDaoTest {

    Item item1;
    Item item2;
    Item item3;
    Item item4;
    Item item5;
    Item item6;
    Item item7;
    Item item8;

    VendingMachineDao dao;

    public VendingMachineDaoTest() {
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
        item4.setQuantity(3);
        
        item5 = new Item();
        item5.setId("C1");
        item5.setName("PB Twix");
        item5.setPrice(0.85);
        item5.setQuantity(1);
        
        item6 = new Item();
        item6.setId("C2");
        item6.setName("Oero's");
        item6.setPrice(1.73);
        item6.setQuantity(3);
        
        item7 = new Item();
        item7.setId("D1");
        item7.setName("Gum");
        item7.setPrice(0.65);
        item7.setQuantity(4);
        
        item8 = new Item();
        item8.setId("D2");
        item8.setName("Mints");
        item8.setPrice(0.40);
        item8.setQuantity(6);
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
//    public void addGetGetAllRemoveTest() {
//        dao.addItem(item1);
//        dao.addItem(item2);
//        dao.addItem(item3);
//        dao.addItem(item4);
//        
//        assertEquals(4, dao.getAllItems().size());
//        
//        Item expected = new Item();
//        expected.setId("A1");
//        expected.setName("Donuts");
//        expected.setPrice(1.25);
//        expected.setQuantity(3);
//        
//        Item actual = dao.getItemById("A1");
//        
//        assertEquals(expected, actual);
//        
//        dao.removeItem(item3.getId());
//        assertEquals(3, dao.getAllItems().size());
//        
//        assertNull(dao.getItemById(item3.getId()));
//    }
//    
//    @Test
//    public void getAvailableTest() {
//        dao.addItem(item1);
//        dao.addItem(item2);
//        dao.addItem(item3);
//        dao.addItem(item4);
//        
//        item3.setQuantity(0);
//        assertEquals(4, dao.getAllItems().size());
//        assertEquals(3, dao.getAvailableItems().size());
//    }
}
