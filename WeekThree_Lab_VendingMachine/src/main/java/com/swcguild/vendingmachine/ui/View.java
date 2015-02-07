package com.swcguild.vendingmachine.ui;

import java.util.Scanner;

/**
 *
 * @author Michael J. Gulley
 */
public class View {

    Scanner sc = new Scanner(System.in);

    public int readInt(String prompt) {
        boolean badInput;
        int result = 0;
        do {
            try {
                System.out.print(prompt);
                result = Integer.parseInt(sc.nextLine());
                badInput = false;
            } catch (NumberFormatException nfe) {
                System.out.print("Please enter a numeric value. ");
                badInput = true;
            }
        } while (badInput);

        return result;
    }

//    public int readInt(String prompt) {
//        System.out.print(prompt);
//        return Integer.parseInt(sc.nextLine());
//    }
    // Make sure prompt states min and max (ex. Please enter number between 1 and 10)
    public int readInt(String prompt, int min, int max) {
        int result = 0;
        do {
            result = readInt(prompt);
        } while (result < min || result > max);

        return result;
    }

//    public int readInt(String prompt, int min, int max) {
//        int a;
//        do {
//            System.out.print(prompt);
//            a = Integer.parseInt(sc.nextLine());
//        } while (a < min || a > max);
//        return a;
//    }
    public String readString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    public float readFloat(String prompt) {
        System.out.print(prompt);
        return Float.parseFloat(sc.nextLine());
    }

    public float readFloat(String prompt, int min, int max) {
        float a;
        do {
            System.out.print(prompt);
            a = Float.parseFloat(sc.nextLine());
        } while (a < min || a > max);
        return a;
    }

    public double readDouble(String prompt) {
        System.out.print(prompt);
        return Double.parseDouble(sc.nextLine());
    }

    public double readDouble(String prompt, int min, int max) {
        double a;
        do {
            System.out.print(prompt);
            a = Double.parseDouble(sc.nextLine());
        } while (a < min || a > max);
        return a;
    }

    public void writeString(String prompt) {
        System.out.println(prompt);
    }

    public void writeDouble(Double a) {
        System.out.println(a);
    }

}
