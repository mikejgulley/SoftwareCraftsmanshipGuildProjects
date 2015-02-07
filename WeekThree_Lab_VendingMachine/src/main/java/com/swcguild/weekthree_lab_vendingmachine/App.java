package com.swcguild.weekthree_lab_vendingmachine;

import com.swcguild.vendingmachine.controller.VendingMachineController;

/**
 *
 * @author Michael J. Gulley
 */
public class App {

    public static void main(String[] args) {
        VendingMachineController vmc = new VendingMachineController();
        vmc.run();
    }
}
