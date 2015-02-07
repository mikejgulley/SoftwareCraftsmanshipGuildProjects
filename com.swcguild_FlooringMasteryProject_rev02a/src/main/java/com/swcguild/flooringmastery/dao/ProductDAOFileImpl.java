package com.swcguild.flooringmastery.dao;

import com.swcguild.flooringmastery.dto.Product;
import com.swcguild.flooringmastery.ui.ConsoleIOrev2;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Michael J. Gulley & Zach Sullivan
 */
public class ProductDAOFileImpl implements ProductDAO {

    ConsoleIOrev2 io = new ConsoleIOrev2();

    public static final String PRODUCT_TOC = "ProductType,CostPerSquareFoot,LaborCostPerSquareFoot";
    public static final String PRODUCT_FILE = "Data/Products.txt";
    public static final String DELIMITER = ",";
//    @Override
//    public Product addProduct() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> productList = new ArrayList<Product>();
        String currentLine;
        String[] currentTokens;

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)));
            sc.nextLine();
            while (sc.hasNextLine()) {
                Product currentProduct = new Product();

                currentLine = sc.nextLine();
                currentTokens = currentLine.split(DELIMITER);

                currentProduct.setProductType(currentTokens[0]);
                currentProduct.setProductCostPerSquareFoot(Double.parseDouble(currentTokens[1]));
                currentProduct.setLaborCostPerSquareFoot(Double.parseDouble(currentTokens[2]));

                productList.add(currentProduct);
            }
            sc.close();

        } catch (FileNotFoundException ex) {
            io.writeString("File not found.");
        }
        return productList;
    }

    @Override
    public void removeProduct(String productType) {
        String currentLine;

        Product currentProduct = new Product();
        ArrayList<Product> productList = new ArrayList<>();
        for (Product currentProductListing : getAllProducts()) {
            if (!currentProductListing.getProductType().equals(productType)) {
                productList.add(currentProductListing);
            }
        }
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(PRODUCT_FILE)));
            out.println(PRODUCT_TOC);
            for (Product product : productList) {
                currentProduct = product;
                out.println(currentProduct.getProductType() + DELIMITER
                        + currentProduct.getProductCostPerSquareFoot() + DELIMITER
                        + currentProduct.getLaborCostPerSquareFoot());
                out.flush();
            }
            out.close();
        } catch (IOException ex) {
            io.writeString("Unable to save new product file.");
        }
    }

    @Override
    public Product getProductByType(String productType) {
        Product currentProduct = new Product();

        for (Product currentProductListing : getAllProducts()) {
            if (currentProductListing.getProductType().equals(productType)) {
                currentProduct = currentProductListing;
            }
        }
        return currentProduct;
    }

}
