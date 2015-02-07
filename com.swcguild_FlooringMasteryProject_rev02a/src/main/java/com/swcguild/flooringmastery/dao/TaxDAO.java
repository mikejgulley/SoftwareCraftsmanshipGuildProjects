package com.swcguild.flooringmastery.dao;

import java.util.ArrayList;

/**
 *
 * @author Michael J. Gulley & Zach Sullivan
 */
public interface TaxDAO {
    public double getTaxRate(String state);
    public ArrayList<String> getStates();
//    public void loadTaxes();
}
