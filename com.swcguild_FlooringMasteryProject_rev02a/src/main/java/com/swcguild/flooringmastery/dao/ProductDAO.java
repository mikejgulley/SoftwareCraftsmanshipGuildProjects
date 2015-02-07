package com.swcguild.flooringmastery.dao;

import com.swcguild.flooringmastery.dto.Product;
import java.util.ArrayList;

/**
 *
 * @author Michael J. Gulley & Zach Sullivan
 */
public interface ProductDAO {
//    public Product addProduct();
    public ArrayList<Product> getAllProducts();
    public void removeProduct(String productType);
    public Product getProductByType(String productType);
}
