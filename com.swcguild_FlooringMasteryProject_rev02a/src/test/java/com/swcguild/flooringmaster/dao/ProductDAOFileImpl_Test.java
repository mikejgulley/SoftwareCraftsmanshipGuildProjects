package com.swcguild.flooringmaster.dao;

import com.swcguild.flooringmastery.dao.ProductDAOFileImpl;
import com.swcguild.flooringmastery.dto.Product;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Michael J. Gulley & Zach Sullivan
 */
public class ProductDAOFileImpl_Test {

    public ProductDAOFileImpl_Test() {
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
    public void getAllProductsTest() {
        ProductDAOFileImpl pdfi = new ProductDAOFileImpl();

        ArrayList<Product> productList = new ArrayList<Product>();

        Product carpet = new Product();
        carpet.setProductType("Carpet");
        carpet.setProductCostPerSquareFoot(2.25);
        carpet.setLaborCostPerSquareFoot(2.10);

        Product laminate = new Product();
        laminate.setProductType("Laminate");
        laminate.setProductCostPerSquareFoot(1.75);
        laminate.setLaborCostPerSquareFoot(2.10);

        Product tile = new Product();
        tile.setProductType("Tile");
        tile.setProductCostPerSquareFoot(3.50);
        tile.setLaborCostPerSquareFoot(4.15);

        Product wood = new Product();
        wood.setProductType("Wood");
        wood.setProductCostPerSquareFoot(5.15);
        wood.setLaborCostPerSquareFoot(4.75);

        productList.add(carpet);
        productList.add(laminate);
        productList.add(tile);
        productList.add(wood);

        assertEquals(productList, pdfi.getAllProducts());
    }

//    @Test
//    public void removeProductTest() {
//        ProductDAOFileImpl pdfi = new ProductDAOFileImpl();
//
//        Product testProduct = new Product();
//        testProduct.setProductType("Wood");
//        testProduct.setProductCostPerSquareFoot(5.15);
//        testProduct.setLaborCostPerSquareFoot(4.75);
//
//        // FIXME -- this is not removing the product from Products.txt -- only from the ArrayList produced by getAllProducts()
//        pdfi.removeProduct("Wood");
//
//        Assert.assertNull(pdfi.getProductByType("Wood"));
////        (pdfi.getProductByType("Wood"));
//    }
//
    @Test
    public void getProductByTypeTest() {
        ProductDAOFileImpl pdfi = new ProductDAOFileImpl();

        Product testProduct = new Product();
        testProduct.setProductType("Wood");
        testProduct.setProductCostPerSquareFoot(5.15);
        testProduct.setLaborCostPerSquareFoot(4.75);

        assertEquals(testProduct, pdfi.getProductByType("Wood"));

    }
}
