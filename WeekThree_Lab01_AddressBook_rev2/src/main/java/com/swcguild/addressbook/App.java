package com.swcguild.addressbook;

import com.swcguild.addressbook.controller.AddressBookController;

/**
 *
 * @author Michael J. Gulley & Luke Robins
 */
public class App {

    public static void main(String[] args) {
        AddressBookController abc = new AddressBookController();
        abc.run();
    }
}
