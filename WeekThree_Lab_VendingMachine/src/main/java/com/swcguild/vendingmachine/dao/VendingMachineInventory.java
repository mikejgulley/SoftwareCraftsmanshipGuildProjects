package com.swcguild.vendingmachine.dao;

import com.swcguild.vendingmachine.dto.Item;
import com.swcguild.vendingmachine.ui.View;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Michael J. Gulley
 */
public class VendingMachineInventory {

    View io = new View();
    DecimalFormat df = new DecimalFormat("#0.00");

    private HashMap<String, Item> inventoryMap = new HashMap<>();
    private static final String MACHINE_INVENTORY_FILE = "machineinventory.txt";
    private static final String DELIMITER = "::";

    public ArrayList<String> getAvailableInventory() {
        ArrayList<String> availableInventoryList = new ArrayList<String>(inventoryMap.keySet());
        return availableInventoryList;
    }

    public Item addItem(Item item) {
        return inventoryMap.put(item.getId(), item);
    }

    public Item removeItem(Item item) {
        if (item.getQuantity() == 0) {
            return inventoryMap.remove(item);
        }
        return item;
    }
    
    public Item getItem(String id) {
        return inventoryMap.get(id);
    }

    public double calcTransaction(String id, double currentBalance, double itemCost) {
        if (currentBalance >= inventoryMap.get(id).getPrice()) {
            if (inventoryMap.get(id).getQuantity() > 0) {
                currentBalance -= inventoryMap.get(id).getPrice();
                inventoryMap.get(id).setQuantity(inventoryMap.get(id).getQuantity() - 1);
            } else {
                io.writeString("There is no remaining inventory for this item. Please choose again.");
            }
        } else {
            io.writeString("Insufficient funds. Please make another selection, or get change and exit.");
        }
        return currentBalance;
    }

    public void loadInventory() throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(MACHINE_INVENTORY_FILE)));
        String currentLine;
        String[] currentTokens;

        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Item currentItem = new Item(currentTokens[0], currentTokens[1], Double.parseDouble(currentTokens[2]), Integer.parseInt(currentTokens[3]));

            inventoryMap.put(currentItem.getId(), currentItem);
        }
    }

    public void writeInventory() throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(MACHINE_INVENTORY_FILE)));
        ArrayList<String> itemIds = this.getAvailableInventory();

        for (String currentItemId : itemIds) {
            Item currentItem = this.getItem(currentItemId);

            out.println(currentItem.getId() + DELIMITER
                    + currentItem.getName() + DELIMITER
                    + currentItem.getPrice() + DELIMITER
                    + currentItem.getQuantity());
            out.flush();
        }
        out.close();
    }
}
