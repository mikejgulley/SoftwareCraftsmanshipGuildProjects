package com.swcguild.vendingmachinemvc.businesslogic;

import com.swcguild.vendingmachinemvc.dao.VendingMachineDao;
import java.text.DecimalFormat;
import javax.inject.Inject;

/**
 *
 * @author Michael J. Gulley
 */
public class BusinessLogicImpl implements BusinessLogic {

    private VendingMachineDao dao;

    public static double currentBalance = 0.0;
    DecimalFormat df = new DecimalFormat("#0.00");

    @Inject
    public BusinessLogicImpl(VendingMachineDao dao) {
        this.dao = dao;
    }

    @Override
    public double addToBalance(double moneyIn) {
        return currentBalance += moneyIn;
    }

    @Override
    public double updateBalance(double newBalance) {
        return currentBalance = newBalance;
    }

    @Override
    public double getCurrentBalance() {
        return currentBalance;
    }

    @Override
    public boolean isItemInStock(String id) {
        return dao.getItemById(id).getQuantity() > 0;
    }

    @Override
    public boolean areFundsSufficient(String id) {
        return currentBalance >= dao.getItemById(id).getPrice();
    }

    @Override
    public double calcTransaction(String id) {
        if (isItemInStock(id) && areFundsSufficient(id)) {
            currentBalance -= dao.getItemById(id).getPrice();
            dao.getItemById(id).setQuantity(dao.getItemById(id).getQuantity() - 1);
        }
        return currentBalance;
    }

//    @Override // might this need moved to .js ? then it can just update the currentBalance
//    public void makeChange(double changeDue) {
//        changeDue = currentBalance / 100;
//        int numQuarters, numDimes, numNickels, numPennies;
//
//        numQuarters = (int) currentBalance / 25;
//        currentBalance %= 25;
//        numDimes = (int) currentBalance / 10;
//        currentBalance %= 10;
//        numNickels = (int) currentBalance / 5;
//        currentBalance %= 5;
//        numPennies = (int) currentBalance / 1;
//        currentBalance %= 1;
//    }

}
