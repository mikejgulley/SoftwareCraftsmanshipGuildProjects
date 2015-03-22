package com.swcguild.vendingmachinemvc.businesslogic;

/**
 *
 * @author Michael J. Gulley
 */
public interface BusinessLogic {

    double addToBalance(double moneyIn);
    
    double updateBalance(double newBalance);

    boolean areFundsSufficient(String id);

    double calcTransaction(String id);

    double getCurrentBalance();

    boolean isItemInStock(String id);
    
//    void makeChange(double changeDue);
    
}
