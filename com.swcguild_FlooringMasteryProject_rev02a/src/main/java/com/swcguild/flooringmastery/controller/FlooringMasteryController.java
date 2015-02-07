package com.swcguild.flooringmastery.controller;

import com.swcguild.flooringmastery.businesslogic.BusinessLogic;
import com.swcguild.flooringmastery.dao.OrderDAO;
import com.swcguild.flooringmastery.dao.OrderDAOFileImpl;
import com.swcguild.flooringmastery.dao.ProductDAO;
import com.swcguild.flooringmastery.dao.ProductDAOFileImpl;
import com.swcguild.flooringmastery.dao.TaxDAO;
import com.swcguild.flooringmastery.dao.TaxDAOFileImpl;
import com.swcguild.flooringmastery.dto.Order;
import com.swcguild.flooringmastery.dto.Product;
import com.swcguild.flooringmastery.ui.ConsoleIOrev2;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author Michael J. Gulley & Zach Sullivan
 */
public class FlooringMasteryController {

    OrderDAO myOrderDAOImpl = new OrderDAOFileImpl();
    TaxDAO myTaxDAOImpl = new TaxDAOFileImpl();
    ProductDAO myProductDAOImpl = new ProductDAOFileImpl();
    BusinessLogic myBusinessLogic = new BusinessLogic();
    ConsoleIOrev2 io = new ConsoleIOrev2();

    LocalDate currentWorkingDate = LocalDate.now();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    DecimalFormat df = new DecimalFormat("#0.00");

    boolean keepGoing = true;
    int menuChoice = 0;

    public void run() {
        LocalDate date = currentWorkingDate;
        myOrderDAOImpl.loadOrdersByDate(date);

        while (keepGoing) {
            printMenu();
            menuChoice = io.readInt("Which of the options above would you like to access? ");

            switch (menuChoice) {
                case 1:
                    displayOrders();
                    break;
                case 2:
                    createOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    deleteOrder();
                    break;
                case 5:
                    saveCurrentWork();
                    break;
                case 6:
                    setWorkingDate();
                    break;
                case 7:
                    keepGoing = false;
                    break;
                default:
                    io.writeString("Invalid input.");
            }
        }
        saveCurrentWork();
    }

    private void printMenu() {
        io.writeString("***************************************************************");
        io.writeString("*                       Flooring Program \n"
                + "* \n"
                + "* Current Working Date: " + displayWorkingDate() + "\n"
                + "* \n"
                + "* 1: Display Orders \n"
                + "* 2: Add an Order \n"
                + "* 3: Edit an Order \n"
                + "* 4: Remove an Order \n"
                + "* 5: Save Current Work \n"
                + "* 6: Change Date \n"
                + "* 7: Quit \n"
                + "* \n"
                + "***************************************************************\n");
    }

    private void displayOrders() {
        boolean badInput = false;
        int orderNumber = 0;
        for (Order order : myOrderDAOImpl.getOrders()) {
            io.writeString(order.getOrderNumber() + ", " + order.getCustomerName());
        }
        if (!myOrderDAOImpl.getOrders().isEmpty()) {
            orderNumber = io.readInt("Please enter the order number you would like to display: ");
            while (myOrderDAOImpl.getOrderByOrderNumber(orderNumber).getCustomerName() == null) {
                orderNumber = io.readInt("Please enter a valid order number: ");
            }
            io.writeString("");
            io.writeString("Name: " + this.myOrderDAOImpl.getOrderByOrderNumber(orderNumber).getCustomerName() + "\n"
                    + "State: " + this.myOrderDAOImpl.getOrderByOrderNumber(orderNumber).getState() + " \n"
                    + "Tax Rate: " + df.format(this.myOrderDAOImpl.getOrderByOrderNumber(orderNumber).getTaxRate()) + "% \n"
                    + "Product Type: " + this.myOrderDAOImpl.getOrderByOrderNumber(orderNumber).getProductType() + "\n"
                    + "Area: " + df.format(this.myOrderDAOImpl.getOrderByOrderNumber(orderNumber).getArea()) + " sq. ft. \n"
                    + "Cost Per Square Foot: $" + df.format(this.myOrderDAOImpl.getOrderByOrderNumber(orderNumber).getCostPerSquareFoot()) + "\n"
                    + "Labor Cost Per Square Foot: $" + df.format(this.myOrderDAOImpl.getOrderByOrderNumber(orderNumber).getLaborCostPerSquareFoot()) + "\n"
                    + "Material Cost: $" + df.format(this.myOrderDAOImpl.getOrderByOrderNumber(orderNumber).getMaterialCost()) + "\n"
                    + "Labor Cost: $" + df.format(this.myOrderDAOImpl.getOrderByOrderNumber(orderNumber).getLaborCost()) + "\n"
                    + "Tax: $" + df.format(this.myOrderDAOImpl.getOrderByOrderNumber(orderNumber).getTax()) + "\n"
                    + "Total Cost: $" + df.format(this.myOrderDAOImpl.getOrderByOrderNumber(orderNumber).getTotal()));
            io.writeString("");
        } else {
            io.writeString("There are no orders to display for this date.\n");
        }
    }

    private void createOrder() {
        int stateCounter = 1;
        int productCounter = 1;

        Order currentOrder;

        String customerName = io.readString("\nCustomer Name (First and Last): ");
        io.writeString("");
        io.writeString("State List: ");
        for (String state : myTaxDAOImpl.getStates()) {
            io.writeString(stateCounter + ": " + state);
            stateCounter++;
        }
        int stateChoice = io.readInt("\nChoose a State: ");
        String customerState = myTaxDAOImpl.getStates().get(stateChoice - 1);

        io.writeString("");
        io.writeString("Product List:");
        for (Product product : myProductDAOImpl.getAllProducts()) {
            io.writeString(productCounter + ": " + product.getProductType());
            productCounter++;
        }
        int productChoice = io.readInt("\nChoose a Product: ");
        String customerProduct = myProductDAOImpl.getAllProducts().get(productChoice - 1).getProductType();

        double area = io.readDouble("\nEnter the Area: ");

        currentOrder = myBusinessLogic.buildOrder(customerName, customerState, customerProduct, area);

        io.writeString("\n" + currentOrder.toString());
        io.writeString("");

        String confirmAdd = io.readString("Are you sure you want to commit this order? (y/n) ");

        if (confirmAdd.equalsIgnoreCase("y") || confirmAdd.equals("")) {
            myOrderDAOImpl.addOrder(currentOrder);
            io.readString("Order successfully created. Please <enter> to continue. ");
            io.writeString("");
        }
        saveCurrentWork();
    }

    private void editOrder() {
        String confirmSave = "";
        String confirmArea = "";
        String confirmOrderNumber = "";
        int orderNumber = 0;
        int counter = 0;

        for (Order order : myOrderDAOImpl.getOrders()) {
            io.writeString(order.getOrderNumber() + ", " + order.getCustomerName());
        }
        if (!myOrderDAOImpl.getOrders().isEmpty()) {
            orderNumber = io.readInt("Please enter the order number you would like to edit: ");
            while (myOrderDAOImpl.getOrderByOrderNumber(orderNumber).getCustomerName() == null) {
                orderNumber = io.readInt("Please enter a valid order number: ");
            }
            for (Order currentOrder : myOrderDAOImpl.getOrders()) {
                if (currentOrder.getOrderNumber() == orderNumber) {
                    counter++;
                }
            }
            if (counter == 0) {
                io.writeString("Order does not exist.");
            } else {
                io.writeString("");
                io.writeString("Please enter new values for the following fields, or press <enter> to keep the current value: ");

                Order currentOrder = myOrderDAOImpl.getOrderByOrderNumber(orderNumber);

                io.writeString("");

                String newCustomerName = io.readString("Customer Name (" + currentOrder.getCustomerName() + "): ");
                if (!newCustomerName.equals("")) {
                    currentOrder.setCustomerName(newCustomerName);
                }
                io.writeString("");

                Integer stateChoice;
                int stateCounter = 1;
                String newCustomerState = "";
                io.writeString("State List:");
                for (String state : myTaxDAOImpl.getStates()) {
                    io.writeString(stateCounter + ": " + state);
                    stateCounter++;
                }
                io.writeString("Enter state (" + currentOrder.getState() + "): ");
                stateChoice = io.readIntOrNull("");
                if (stateChoice != null) {
                    currentOrder.setState(newCustomerState);
                    newCustomerState = myTaxDAOImpl.getStates().get(stateChoice - 1);
                }

                io.writeString("");

                Integer productChoice;
                int productCounter = 1;
                String newProductType = "";
                io.writeString("Product List:");
                for (Product product : myProductDAOImpl.getAllProducts()) {
                    io.writeString(productCounter + ": " + product.getProductType());
                    productCounter++;
                }
                io.writeString("Enter Product (" + currentOrder.getProductType() + "): ");
                productChoice = io.readIntOrNull("");
                if (productChoice != null) {
                    currentOrder.setProductType(newProductType);
                    newProductType = myProductDAOImpl.getAllProducts().get(productChoice - 1).getProductType();
                }

                io.writeString("");

                Double newCustomerArea;
                io.writeString("Enter Area (" + currentOrder.getArea() + "): ");
                newCustomerArea = io.readDoubleOrNull("");
                if (newCustomerArea != null) {
                    currentOrder.setArea(newCustomerArea);
                }

                io.writeString("");

                confirmSave = io.readString("Commit changes? (y/n) ");

                if (confirmSave.equalsIgnoreCase("y") || confirmSave.equals("")) {
                    String customerName = currentOrder.getCustomerName();
                    String state = currentOrder.getState();
                    String productType = currentOrder.getProductType();
                    double area = currentOrder.getArea();

                    myOrderDAOImpl.updateOrder(currentOrder);
                    
                    saveCurrentWork();
                }
            }
        } else {
            io.writeString("There are no orders to display for this date.");
        }
    }
//    private void editOrder() {
//        String confirmSave = "";
//        String confirmArea = "";
//        String confirmOrderNumber = "";
//        int orderNumber = 0;
//        int counter = 0;
//
//        for (Order order : myOrderDAOImpl.getOrders()) {
//            io.writeString(order.getOrderNumber() + ", " + order.getCustomerName());
//        }
//        if (!myOrderDAOImpl.getOrders().isEmpty()) {
//            orderNumber = io.readInt("Please enter the order number you would like to edit: ");
//            while (myOrderDAOImpl.getOrderByOrderNumber(orderNumber).getCustomerName() == null) {
//                orderNumber = io.readInt("Please enter a valid order number: ");
//            }
//            for (Order currentOrder : myOrderDAOImpl.getOrders()) {
//                if (currentOrder.getOrderNumber() == orderNumber) {
//                    counter++;
//                }
//            }
//            if (counter == 0) {
//                io.writeString("Order does not exist.");
//            } else {
//                io.writeString("");
//                io.writeString("Please enter new values for the following fields, or press <enter> to keep the current value: ");
//
//                Order currentOrder = myOrderDAOImpl.getOrderByOrderNumber(orderNumber);
//
//                io.writeString("");
//
//                String newCustomerName = io.readString("Customer Name (" + currentOrder.getCustomerName() + "): ");
//                if (!newCustomerName.equals("")) {
//                    currentOrder.setCustomerName(newCustomerName);
//                }
//                io.writeString("");
//
//                Integer stateChoice;
//                int stateCounter = 1;
//                String newCustomerState = "";
//                io.writeString("State List:");
//                for (String state : myTaxDAOImpl.getStates()) {
//                    io.writeString(stateCounter + ": " + state);
//                    stateCounter++;
//                }
//                io.writeString("Enter state (" + currentOrder.getState() + "): ");
//                stateChoice = io.readIntOrNull("");
//                if (stateChoice != null) {
//                    currentOrder.setState(newCustomerState);
//                    newCustomerState = myTaxDAOImpl.getStates().get(stateChoice - 1);
//                }
//
//                io.writeString("");
//
//                Integer productChoice;
//                int productCounter = 1;
//                String newProductType = "";
//                io.writeString("Product List:");
//                for (Product product : myProductDAOImpl.getAllProducts()) {
//                    io.writeString(productCounter + ": " + product.getProductType());
//                    productCounter++;
//                }
//                io.writeString("Enter Product (" + currentOrder.getProductType() + "): ");
//                productChoice = io.readIntOrNull("");
//                if (productChoice != null) {
//                    currentOrder.setProductType(newProductType);
//                    newProductType = myProductDAOImpl.getAllProducts().get(productChoice - 1).getProductType();
//                }
//
//                io.writeString("");
//
//                Double newCustomerArea;
//                io.writeString("Enter Area (" + currentOrder.getArea() + "): ");
//                newCustomerArea = io.readDoubleOrNull("");
//                if (newCustomerArea != null) {
//                    currentOrder.setArea(newCustomerArea);
//                }
//
//                io.writeString("");
//
//                confirmSave = io.readString("Commit changes? (y/n) ");
//
//                if (confirmSave.equalsIgnoreCase("y") || confirmSave.equals("")) {
//                    String customerName = currentOrder.getCustomerName();
//                    String state = currentOrder.getState();
//                    String productType = currentOrder.getProductType();
//                    double area = currentOrder.getArea();
//
//                    myOrderDAOImpl.updateOrder(currentOrder);
//
//                    // If they choose to save to a new date, call the save method where we can input that date.
//                    boolean badInput = false;
//                    String saveDate = io.readString("Would you like to save to a new date? (y/n) ");
//                    LocalDate previousWorkingDate = currentWorkingDate;
//                    myOrderDAOImpl.removeOrderByOrder(previousWorkingDate, currentOrder);
//                    if (saveDate.equalsIgnoreCase("y")) {
//                        do {
//                            try {
//                                String newSaveDateString = io.readString("Please enter the new date you would like to save to (YYYY-MM-DD): ");
//                                io.writeString("");
//                                currentWorkingDate = LocalDate.parse(newSaveDateString);
//                                myOrderDAOImpl.saveOrders(currentWorkingDate);
//                                badInput = false;
//                            } catch (DateTimeParseException dtpe) {
//                                io.writeString("Invalid date.");
//                                badInput = true;
//                            }
//                        } while (badInput);
//                        
//                    } else {
//                        saveCurrentWork();
//                    }
//                }
//            }
//        } else {
//            io.writeString("There are no orders to display for this date.");
//        }
//    }

    private void deleteOrder() {
        int orderNumber = 0;
        Order currentOrder = new Order();

        for (Order order : myOrderDAOImpl.getOrders()) {
            io.writeString(order.getOrderNumber() + ", " + order.getCustomerName());
        }
        if (!myOrderDAOImpl.getOrders().isEmpty()) {
            orderNumber = io.readInt("Please enter the order number you would like to remove: ");
            while (myOrderDAOImpl.getOrderByOrderNumber(orderNumber).getCustomerName() == null) {
                orderNumber = io.readInt("Please enter a valid order number: ");
            }

            io.writeString("");
            io.writeString("Name: " + this.myOrderDAOImpl.getOrderByOrderNumber(orderNumber).getCustomerName() + "\n"
                    + "State: " + this.myOrderDAOImpl.getOrderByOrderNumber(orderNumber).getState() + " \n"
                    + "Tax Rate: " + df.format(this.myOrderDAOImpl.getOrderByOrderNumber(orderNumber).getTaxRate()) + "% \n"
                    + "Product Type: " + this.myOrderDAOImpl.getOrderByOrderNumber(orderNumber).getProductType() + "\n"
                    + "Area: " + df.format(this.myOrderDAOImpl.getOrderByOrderNumber(orderNumber).getArea()) + " sq. ft. \n"
                    + "Cost Per Square Foot: $" + df.format(this.myOrderDAOImpl.getOrderByOrderNumber(orderNumber).getCostPerSquareFoot()) + "\n"
                    + "Labor Cost Per Square Foot: $" + df.format(this.myOrderDAOImpl.getOrderByOrderNumber(orderNumber).getLaborCostPerSquareFoot()) + "\n"
                    + "Material Cost: $" + df.format(this.myOrderDAOImpl.getOrderByOrderNumber(orderNumber).getMaterialCost()) + "\n"
                    + "Labor Cost: $" + df.format(this.myOrderDAOImpl.getOrderByOrderNumber(orderNumber).getLaborCost()) + "\n"
                    + "Tax: $" + df.format(this.myOrderDAOImpl.getOrderByOrderNumber(orderNumber).getTax()) + "\n"
                    + "Total Cost: $" + df.format(this.myOrderDAOImpl.getOrderByOrderNumber(orderNumber).getTotal()));
            io.writeString("");
        }
        String confirmRemove = io.readString("Are you sure you would like to remove this order? (y/n) ");

        if (confirmRemove.equalsIgnoreCase("y") || confirmRemove.equals("")) {
            myOrderDAOImpl.removeOrder(orderNumber);
        }
        saveCurrentWork();
    }

    private void saveCurrentWork() {
        if (modeCheck()) {
            io.writeString("Data saved.");
            io.writeString("");
            myOrderDAOImpl.saveOrders(currentWorkingDate);
        } else {
            io.writeString("Test mode enabled. Data not saved.\n");
        }
    }

    private boolean modeCheck() {
        boolean mode;
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader("config")));
            if (!sc.nextLine().equalsIgnoreCase("Test")) {
                return true;
            }

        } catch (FileNotFoundException ex) {
            io.writeString("Config file not found.");
        }
        return false;
    }

    private LocalDate setWorkingDate() {
        boolean badInput = false;

        myOrderDAOImpl.saveOrders(currentWorkingDate);
        myOrderDAOImpl.removeAllOrdersByCurrentDate(currentWorkingDate);

        do {
            try {
                String newWorkingDateString = io.readString("Please enter the new date you would like to work with (YYYY-MM-DD): ");
                io.writeString("");
                currentWorkingDate = LocalDate.parse(newWorkingDateString);
                myOrderDAOImpl.loadOrdersByDate(currentWorkingDate);
                badInput = false;
            } catch (DateTimeParseException dtpe) {
                io.writeString("Invalid date.");
                badInput = true;
            }
        } while (badInput);
        return currentWorkingDate;
    }

    private String displayWorkingDate() {
        return currentWorkingDate.format(dtf);
    }

}
