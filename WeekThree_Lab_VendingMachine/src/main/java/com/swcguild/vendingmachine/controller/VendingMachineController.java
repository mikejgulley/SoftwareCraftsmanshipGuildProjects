package com.swcguild.vendingmachine.controller;

import com.swcguild.vendingmachine.dao.VendingMachineInventory;
import com.swcguild.vendingmachine.dto.Item;
import com.swcguild.vendingmachine.ui.View;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Michael J. Gulley
 */
// FIXME -- I made it so the user must enter a value for moneyIn (meaning they
// can press enter with no input and be reprompted). However
// if they enter letters, it breaks.
public class VendingMachineController {

    View io = new View();
    VendingMachineInventory vmi = new VendingMachineInventory();
    DecimalFormat df = new DecimalFormat("#0.00");

    public void run() {
        boolean keepGoing = true;
        String moneyIn = "X";
        double currentBalance = 0.0;
        String selectionChoice = "";

        // Initial add to inventory -- don't need to leave this active once done the first time. It's overwritten by loadInventory()
        addAllItemsToInventory();
        io.writeString("Welcome to Vending Machine!");
        // Prompt for money user wants to spend in format $X.XX
        do {
            moneyIn = io.readString("Please enter the amount of money you would like to spend (in format X.XX): $");
        } while (moneyIn.equals(""));

        currentBalance = Double.parseDouble(moneyIn);

        // Convert money to cents (so $1.25 becomes 125 --> moneyIn * 100)
        currentBalance *= 100;

        try {
            // Load Inventory overwrites addAllItemsToInventory() -- if you forget to comment it back out
            vmi.loadInventory();
            while (keepGoing) {
                // Prompt user for menu choice and show current balance
                io.writeString("\nCurrent Balance: $" + (df.format(currentBalance / 100)));

                // Prompt user to make a selection, or make change and exit
                io.writeString("\nMain Menu:");
                io.writeString("1. Make a selection from our scrumptious inventory.");
                io.writeString("2. Make change and exit.");

                int menuChoice = io.readInt("\nPlease choose an option: ");
                switch (menuChoice) {
                    case 1:
                        listAllAvailableInventory();
                        io.writeString("\nCurrent Balance: $" + df.format(currentBalance / 100));

                        selectionChoice = io.readString("Please make a selection, or press <enter> to return to main menu: ");
                        if (selectionChoice.equals("")) {
                            continue;
                        } else {
                            while (!vmi.getAvailableInventory().contains(selectionChoice)) {
                                io.writeString("Invalid selection. Please try again.");
                                selectionChoice = io.readString("Please make a selection: ");
                            }
                        }
                        currentBalance = approveTransaction(selectionChoice, currentBalance, vmi.getItem(selectionChoice).getPrice());
                        io.writeString("Vending " + vmi.getItem(selectionChoice).getName() + "...");
                        break;
                    case 2:
                        calcChange(currentBalance);
                        keepGoing = false;
                        break;
                    default:
                        io.writeString("Invalid choice. Please try again.");
                }
            }
            io.writeString("");
            io.writeString("Thank you for using Vending Machine!");
            vmi.writeInventory();
        } catch (FileNotFoundException fnf) {
            io.writeString("Error loading Vending Machine Inventory.");
        } catch (IOException ioe) {
            io.writeString("Error writing to file.");
        }
    }

    private void addAllItemsToInventory() {
        Item donuts = new Item("A1", "Donuts", 125, 6);
        Item snickers = new Item("B1", "Snickers", 75, 5);
        Item mms = new Item("A2", "M&M's", 76, 2);
        Item doritos = new Item("B2", "Dorito's", 115, 3);
        Item pbTwix = new Item("C1", "PB Twix", 85, 1);
        Item oreos = new Item("C2", "Oero's", 173, 3);
        Item gum = new Item("D1", "Gum", 65, 6);
        Item mints = new Item("D2", "Mints", 40, 7);

        vmi.addItem(donuts);
        vmi.addItem(snickers);
        vmi.addItem(mms);
        vmi.addItem(doritos);
        vmi.addItem(pbTwix);
        vmi.addItem(oreos);
        vmi.addItem(gum);
        vmi.addItem(mints);
    }

    private void listAllAvailableInventory() {
        ArrayList<String> availableInventoryList = vmi.getAvailableInventory();
        io.writeString("\nVending Machine - Current Inventory:");
        io.writeString("------------------------------------");

        for (String currentItem : availableInventoryList) {

            if (vmi.getItem(currentItem).getQuantity() != 0) {
                io.writeString(vmi.getItem(currentItem).toString());
            }
        }
    }

    private double approveTransaction(String id, double currentBalance, double itemCost) {
        currentBalance = vmi.calcTransaction(id, currentBalance, itemCost);

        return currentBalance;
    }

    private void calcChange(double currentBalance) {
        double changeDue = currentBalance / 100;
        io.writeString("");
        io.writeString("Change Due: $" + df.format(changeDue));
        int numQuarters, numDimes, numNickels, numPennies;

        numQuarters = (int) currentBalance / 25;
        currentBalance %= 25;
        numDimes = (int) currentBalance / 10;
        currentBalance %= 10;
        numNickels = (int) currentBalance / 5;
        currentBalance %= 5;
        numPennies = (int) currentBalance / 1;
        currentBalance %= 1;

        // List number of each coin due to customer
        io.writeString("");
        io.writeString("Quarters: " + numQuarters);
        io.writeString("Dimes:    " + numDimes);
        io.writeString("Nickels:  " + numNickels);
        io.writeString("Pennies:  " + numPennies);
    }

}
