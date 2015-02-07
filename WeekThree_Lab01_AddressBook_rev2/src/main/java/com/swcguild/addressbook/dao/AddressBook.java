package com.swcguild.addressbook.dao;

import com.swcguild.addressbook.dto.Address;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Michael J. Gulley & Luke Robins
 */
public class AddressBook {

    // This is where we will store the Addresses
    // This is our DAO - Data Access Object
    public static final String ADDRESS_BOOK = "book.txt";
    public static final String DELIMITER = "::";

    public HashMap<String, Address> addressMap = new HashMap<>();
    private ArrayList<Address> addressList = new ArrayList<Address>();

    public Address addAddress(String id, Address address) {
        return addressMap.put(id, address);
    }

    public ArrayList<Address> getAddressesByLastName(String lastName) {
        Collection<Address> addressValues = addressMap.values();

        for (Address currentAddress : addressValues) {
            if (currentAddress.getLastName().equals(lastName)) {
                addressList.add(currentAddress);
            } else {
                addressList.remove(currentAddress);
            }
        }

        return addressList;
    }

    public Address getAddressById(String id) {
        return addressMap.get(id);
    }

    public Address removeAddress(String id) {
        return addressMap.remove(id);
    }

    public ArrayList<String> getAllAddresses() {
        Set<String> addressSet = addressMap.keySet();
        ArrayList<String> addressList = new ArrayList<String>(addressSet);

        return addressList;
    }

    public void loadBook() throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(ADDRESS_BOOK)));
        String currentLine;
        String[] currentTokens;

        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Address currentAddress = new Address(currentTokens[0]);
            currentAddress.setId(currentTokens[0]);
            currentAddress.setFirstName(currentTokens[1]);
            currentAddress.setLastName(currentTokens[2]);
            currentAddress.setStreetAddress(currentTokens[3]);
            currentAddress.setCity(currentTokens[4]);
            currentAddress.setState(currentTokens[5]);
            currentAddress.setZip(currentTokens[6]);

            addressMap.put(currentAddress.getId(), currentAddress);
        }

        sc.close();
    }

    public void writeBook() throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(ADDRESS_BOOK)));

        ArrayList<String> addresses = this.getAllAddresses();
        for (String currentListing : addresses) {
            Address currentAddress = this.getAddressById(currentListing);
            out.println(currentAddress.getId() + DELIMITER
                    + currentAddress.getFirstName() + DELIMITER
                    + currentAddress.getLastName() + DELIMITER
                    + currentAddress.getStreetAddress() + DELIMITER
                    + currentAddress.getCity() + DELIMITER
                    + currentAddress.getState() + DELIMITER
                    + currentAddress.getZip());

            out.flush();
        }
        out.close();
    }

}
