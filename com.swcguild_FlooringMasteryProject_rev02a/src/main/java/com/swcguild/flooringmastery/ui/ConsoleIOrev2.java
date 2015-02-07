package com.swcguild.flooringmastery.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author Michael J. Gulley
 */
public class ConsoleIOrev2 {

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
                System.out.print("Please enter a numeric value.\n");
                badInput = true;
            }
        } while (badInput);

        return result;
    }

    public int readInt(String prompt, int min, int max) {
        int result = 0;
        do {
            result = readInt(prompt);
        } while (result < min || result > max);

        return result;
    }

    public String readString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    public float readFloat(String prompt) {
        boolean badInput;
        float result = 0;
        do {
            try {
                System.out.print(prompt);
                result = Float.parseFloat(sc.nextLine());
                badInput = false;
            } catch (NumberFormatException fne) {
                System.out.println("Please enter a numeric value.\n");
                badInput = true;
            }
        } while (badInput);
        return result;
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
        boolean badInput;
        double result = 0;
        do {
            try {
                System.out.print(prompt);
                result = Double.parseDouble(sc.nextLine());
                badInput = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter a numeric value.\n");
                badInput = true;
            }
        } while (badInput);
        return result;
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

    public Integer readIntOrNull(String prompt) {
        boolean badInput = true;
        Integer result = null;
        do {
            try {
                String inputChecker = sc.nextLine();
                System.out.print(prompt);
                if (inputChecker.equalsIgnoreCase("")) {
                    return null;
                }
                result = Integer.parseInt(inputChecker);
                badInput = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter a numeric value.");
            }
        } while (badInput);
        return result;
    }

    public Double readDoubleOrNull(String prompt) {
        boolean badInput = true;
        Double result = null;
        do {
            try {
                String inputChecker = sc.nextLine();
                System.out.print(prompt);
                if (inputChecker.equalsIgnoreCase("")) {
                    return null;
                }
                result = Double.parseDouble(inputChecker);
                badInput = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter a numeric value.");
            }
        } while (badInput);
        return result;
    }

    public String readLocalDateConvertToString(String message, String format) {
        boolean badInput = false;
        LocalDate myDate = null;
        do {
            try {
                System.out.println("\n" + message);
                String input = sc.nextLine();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                myDate = LocalDate.parse(input, dtf);
                badInput = false;
            } catch (DateTimeParseException dte) {
                System.out.println("\nThat date is not possible. Please enter again...");
                badInput = true;
            }
        } while (badInput);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        String dateString = myDate.format(dtf);
        return dateString;
    }
}
