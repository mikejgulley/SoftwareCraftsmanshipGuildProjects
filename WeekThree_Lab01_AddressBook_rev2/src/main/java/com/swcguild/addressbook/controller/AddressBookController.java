package com.swcguild.addressbook.controller;

import com.swcguild.addressbook.UI.ConsoleIOrev2;
import com.swcguild.addressbook.dao.AddressBook;
import com.swcguild.addressbook.dto.Address;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Michael J. Gulley & Luke Robins
 */
public class AddressBookController {

    ConsoleIOrev2 io = new ConsoleIOrev2();
    AddressBook addressBook = new AddressBook();

    public void run() {
        boolean keepGoing = true;
        int userChoice = 0;

        try {
            addressBook.loadBook();
            while (keepGoing) {
                printMainMenu();
                userChoice = io.readInt("Please enter a choice from above: ");

                switch (userChoice) {
                    case 1:
                        createAddress();
                        break;
                    case 2:
                        removeAddress();
                        break;
                    case 3:
                        findAddress();
                        break;
                    case 4:
                        listAddressCount();
                        break;
                    case 5:
                        listAllAddresses();
                        break;
                    case 6:
                        editAddress();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        io.writeString("Invalid choice. Please try again.");
                }

            }
            io.writeString("Thanks for using Address Book!");
            addressBook.writeBook();
        } catch (FileNotFoundException fne) {
            io.writeString("Error loading Address Book. File not found.");
        } catch (IOException ioe) {
            io.writeString("Error writing to file.");
        }
    }

    private void printMainMenu() {
        io.writeString("Please select the operation you wish to perform: ");
        io.writeString("\t1. Add Address");
        io.writeString("\t2. Delete Address");
        io.writeString("\t3. Find Address");
        io.writeString("\t4. List Address Count");
        io.writeString("\t5. List All Addresses");
        io.writeString("\t6. Edit Address");
        io.writeString("\t7. Quit");
    }

    private void createAddress() {
        Random rand = new Random(); // change to counter from zero -- put in DAO in addAddress
        io.writeString("");
        int intId = rand.nextInt(1000);
        String strId = Integer.toString(intId);
        String firstName = io.readString("Please enter first name: ");
        String lastName = io.readString("Please enter last name: ");
        String streetAddress = io.readString("Please enter street address: ");
        String city = io.readString("Please enter city: ");
        String state = io.readString("Please enter state: ");
        String zip = io.readString("Please enter zip code: ");

        Address currentAddress = new Address(strId);

        currentAddress.setId(strId);
        currentAddress.setFirstName(firstName);
        currentAddress.setLastName(lastName);
        currentAddress.setStreetAddress(streetAddress);
        currentAddress.setCity(city);
        currentAddress.setState(state);
        currentAddress.setZip(zip);

//        addressBook.addAddress(lastName, currentAddress);
        addressBook.addAddress(strId, currentAddress);

        io.readString("Address successfully created. Please press <enter> to continue.");
        io.writeString("");
    }

    private void removeAddress() {
        String lastName = "";
        String userChoice = "";
        ArrayList<Address> contacts;
        try {
            if (addressBook.addressMap.isEmpty()) {
                io.writeString("There are currently no addresses in the database.");
                io.writeString("");
            } else {
                lastName = io.readString("Please enter the last name of the individual's address you would like to remove: ");
                contacts = addressBook.getAddressesByLastName(lastName);
//                for (Address currentContact : addressBook.getAddressesByLastName(lastName)) {
                for (Address currentContact : contacts) {
                    io.writeString("ID: " + currentContact.getId());
                    io.writeString(currentContact.getFirstName() + " " + currentContact.getLastName());
                    io.writeString(currentContact.getStreetAddress());
                    io.writeString(currentContact.getCity() + ", " + currentContact.getState() + " " + currentContact.getZip());
                    io.writeString("");
                }

                // Check to see if list of all contacts matching same last name is greater than 1 -- if so, then user need to pick which to remove based on ID
                if (contacts.size() > 1) {
                    userChoice = io.readString("Which ID would you like to delete? (Press <enter> to cancel) ");
                    if (!userChoice.equals("")) {
                        addressBook.removeAddress(userChoice);
                        io.readString("Address successfully removed. Please press <enter> to continue.");
                        io.writeString("");
                    } else {
                        io.writeString("No entries updated.");
                        io.writeString("");
                    }
                } else {
                    userChoice = io.readString("Are you sure you want to delete this entry? (y/n) ");
                    if (userChoice.equalsIgnoreCase("y")) {
                        addressBook.removeAddress(lastName);
                        io.readString("Address successfully removed. Please press <enter> to continue.");
                        io.writeString("");
                    } else {
                        io.writeString("No entries updated.");
                        io.writeString("");
                    }
                }
            }

        } catch (NullPointerException npe) {
            io.writeString("Last name does not exist in database.");
            io.writeString("");
        }
    }

    private void findAddress() {
        ArrayList<Address> contacts;
        if (addressBook.addressMap.isEmpty()) {
            io.writeString("There are currently no addresses in the database.");
            io.writeString("");
        } else {
            String lastName = io.readString("Please enter the last name of the individual's address you would like to find: ");
            contacts = addressBook.getAddressesByLastName(lastName);
            for (Address currentContact : contacts) {
                io.writeString("\n" + currentContact.getFirstName() + " " + currentContact.getLastName());
                io.writeString(currentContact.getStreetAddress());
                io.writeString(currentContact.getCity() + ", " + currentContact.getState() + " " + currentContact.getZip());
                io.writeString("");
            }
        }
    }

    private void listAddressCount() {
        int counter = 0;
        for (String allAddresses : addressBook.getAllAddresses()) {
            counter++;
        }
        io.writeString("Number of addresses in database: " + counter);
        io.writeString("");
    }

    private void listAllAddresses() {
        // ArrayList<String> addresses = addressBook.getAllAddresses();

        if (addressBook.addressMap.isEmpty()) {
            io.writeString("There are currently no addresses in the database.");
            io.writeString("");
        } else {
            for (String currentListing : addressBook.getAllAddresses()) {

                Address currentAddress = addressBook.getAddressById(currentListing);
                io.writeString("ID: " + currentAddress.getId());
                io.writeString(currentAddress.getFirstName() + " " + currentAddress.getLastName());
                io.writeString(currentAddress.getStreetAddress());
                io.writeString(currentAddress.getCity() + ", " + currentAddress.getState() + " " + currentAddress.getZip());
                io.writeString("");
            }
        }
    }

    private void editAddress() {
        ArrayList<Address> contacts;
        String userChoice = "";
        // TODO fix so that name entered ignores case (ex. if I type jones vs Jones)
        try {
            if (addressBook.addressMap.isEmpty()) {
                io.writeString("There are currently no addresses in the database.");
                io.writeString("");
            } else {
                String lastName = io.readString("Please the last name of the individual's address you would like to edit: ");
                contacts = addressBook.getAddressesByLastName(lastName);
                String newValue = "";
                Address currentAddress = addressBook.getAddressById(lastName);

                for (Address currentContact : contacts) {
                    io.writeString("ID: " + currentContact.getId());
                    io.writeString(currentContact.getFirstName() + " " + currentContact.getLastName());
                    io.writeString(currentContact.getStreetAddress());
                    io.writeString(currentContact.getCity() + ", " + currentContact.getState() + " " + currentContact.getZip());
                    io.writeString("");
                }

                // Check to see if list of all contacts matching same last name is greater than 1 -- if so, then user need to pick which to remove based on ID
                if (contacts.size() > 1) {
                    userChoice = io.readString("Which ID would you like to edit? (Press <enter> to cancel) ");
                    if (!userChoice.equals("")) {
//                        addressBook.removeAddress(userChoice);
//                        io.readString("Address successfully removed. Please press <enter> to continue.");
//                        io.writeString("");
                        io.writeString("Please enter new value for the following fields, or press <enter> to skip. ");

                        newValue = io.readString("First Name: ");

                        if (!newValue.equals("")) {
                            addressBook.getAddressById(userChoice).setFirstName(newValue);
                        }

                        newValue = io.readString("Last Name: ");

                        if (!newValue.equals("")) {
                            addressBook.getAddressById(userChoice).setLastName(newValue);
                        }

                        newValue = io.readString("Street Address: ");

                        if (!newValue.equals("")) {
                            addressBook.getAddressById(userChoice).setStreetAddress(newValue);
                        }

                        newValue = io.readString("City: ");

                        if (!newValue.equals("")) {
                            addressBook.getAddressById(userChoice).setCity(newValue);
                        }

                        newValue = io.readString("State: ");

                        if (!newValue.equals("")) {
                            addressBook.getAddressById(userChoice).setState(newValue);
                        }

                        newValue = io.readString("Zip Code: ");

                        if (!newValue.equals("")) {
                            addressBook.getAddressById(userChoice).setZip(newValue);
                        }

                        io.readString("Field(s) successfully updated. Please press <enter> to continue.");
                        io.writeString("");
                    } else {
                        io.writeString("No entries updated.");
                        io.writeString("");
                    }
                } else {
                    userChoice = io.readString("Are you sure you want to update this entry? (y/n) ");
                    if (userChoice.equalsIgnoreCase("y")) {
                        io.writeString("Please enter new value for the following fields, or press <enter> to skip. ");

                        newValue = io.readString("First Name: ");

                        if (!newValue.equals("")) {
                            currentAddress.setFirstName(newValue);
                        }

                        newValue = io.readString("Last Name: ");

                        if (!newValue.equals("")) {
                            currentAddress.setLastName(newValue);
                        }

                        newValue = io.readString("Street Address: ");

                        if (!newValue.equals("")) {
                            currentAddress.setStreetAddress(newValue);
                        }

                        newValue = io.readString("City: ");

                        if (!newValue.equals("")) {
                            currentAddress.setCity(newValue);
                        }

                        newValue = io.readString("State: ");

                        if (!newValue.equals("")) {
                            currentAddress.setState(newValue);
                        }

                        newValue = io.readString("Zip Code: ");

                        if (!newValue.equals("")) {
                            currentAddress.setZip(newValue);
                        }

                        io.readString("Field(s) successfully updated. Please press <enter> to continue.");
                        io.writeString("");
                    } else {
                        io.writeString("No entries updated.");
                        io.writeString("");
                    }
                }
            }
        } catch (NullPointerException npe) {
            io.writeString("Last name does not exist in database.");
            io.writeString("");
        }
    }
}
