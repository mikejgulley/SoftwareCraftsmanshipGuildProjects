package com.swcguild.flooringmastery.dao;

import static com.swcguild.flooringmastery.dao.OrderDAOFileImpl.DELIMITER;
import com.swcguild.flooringmastery.ui.ConsoleIOrev2;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Michael J. Gulley & Zach Sullivan
 */
public class TaxDAOFileImpl implements TaxDAO {

//    ConsoleIO io = new ConsoleIO();
    ConsoleIOrev2 io = new ConsoleIOrev2();
    public static final String TAX_TOC = "State,TaxRate";

    @Override
    public double getTaxRate(String state) {
        HashMap<String, Double> taxMap = new HashMap<>();
        double taxRate;
        String stateName;
        String currentLine;
        String[] currentTokens;
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader("Data/Taxes.txt")));
            sc.nextLine();
            while (sc.hasNextLine()) {
                currentLine = sc.nextLine();
                currentTokens = currentLine.split(DELIMITER);
                stateName = currentTokens[0];
                taxRate = Double.parseDouble(currentTokens[1]);
                taxMap.put(stateName, taxRate);
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            io.writeString("Unable to load file");
        }
        taxRate = taxMap.get(state).doubleValue();
        return taxRate;
    }

    @Override
    public ArrayList<String> getStates() {
        ArrayList<String> states = new ArrayList<>();
        String state;
        String currentLine;
        String[] currentTokens;
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader("Data/Taxes.txt")));
            sc.nextLine();
            while (sc.hasNextLine()) {
                currentLine = sc.nextLine();
                currentTokens = currentLine.split(DELIMITER);
                state = currentTokens[0];
                states.add(state);
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            io.writeString("Unable to load file");
        }
        return states;
    }

//    @Override
//    public void loadTaxes() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
