package com.swcguild.flooringmastery.dao;

import static com.swcguild.flooringmastery.businesslogic.BusinessLogic.COUNTER_FILE;
import com.swcguild.flooringmastery.dto.Order;
import com.swcguild.flooringmastery.exceptions.DataReadException;
import com.swcguild.flooringmastery.ui.ConsoleIOrev2;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Michael J. Gulley & Zach Sullivan
 */
public class OrderDAOFileImpl implements OrderDAO {

    ConsoleIOrev2 io = new ConsoleIOrev2();
    SimpleDateFormat sdf = new SimpleDateFormat("MMDDYYYY");

    public static final String ORDER_TOC = "OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total";
    public static final String DELIMITER = ",";

    private ArrayList<Order> ordersList = new ArrayList<Order>();

    @Override
    public void addOrder(Order order) {
        ordersList.add(order);
    }

    @Override
    public void updateOrder(Order order) {
        ArrayList<Order> tempList = new ArrayList<>();
        for (Order currentOrder : ordersList) {
            if (currentOrder.getOrderNumber() != order.getOrderNumber()) {
                tempList.add(currentOrder);
            }
        }
        tempList.add(order);
        ordersList = tempList;
    }

    @Override
    public int genOrderNumber() {
        int counter = 0;
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(COUNTER_FILE)));
            counter = Integer.parseInt(sc.nextLine());
            counter++;
            sc.close();

            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(COUNTER_FILE)));

            out.println(counter);
            out.flush();
            out.close();
        } catch (FileNotFoundException ex) {
            io.writeString("Error loading file.");
        } catch (IOException ioe) {
            io.writeString("Error writing to file.");
        }
        return counter;
    }

    @Override
    public void removeOrder(int orderNumber) {
        ArrayList<Order> tempList = new ArrayList<>();
        for (Order currentOrder : ordersList) {
            if (currentOrder.getOrderNumber() != orderNumber) {
                tempList.add(currentOrder);
            }
        }
        ordersList = tempList;
    }

    @Override
    public void clearAll() {
        ordersList.clear();
    }

    @Override
    public void removeOrderByOrder(LocalDate date, Order order) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddyyyy");
        String dateString = date.format(dtf);
        for (Order currentOrder : ordersList) {
            if (currentOrder == order) {
                ordersList.remove(currentOrder);
                return;
            }
        }
    }

    @Override
    public void removeAllOrdersByCurrentDate(LocalDate date) {
        ordersList.clear();
    }

    @Override
    public Order getOrderByOrderNumber(int orderNumber) {
        Order thisOrder = new Order();
        for (Order currentOrder : ordersList) {
            if (currentOrder.getOrderNumber() == orderNumber) {
                thisOrder = currentOrder;
            }
        }
        return thisOrder;
    }

    @Override
    public ArrayList<Order> getOrders() {
        return ordersList;
    }

    @Override
    public void loadOrdersByDate(LocalDate date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddyyyy");
        String dateString = date.format(dtf);

        String currentLine;
        String[] currentTokens;

        try {
            String fileName = "Data/Orders_" + dateString + ".txt";
            if (fileName != null) {
                Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)));
                sc.nextLine();
                while (sc.hasNextLine()) {
                    currentLine = sc.nextLine();
                    currentTokens = currentLine.split(DELIMITER);
                    Order currentOrder = new Order();
                    currentOrder.setOrderNumber(Integer.parseInt(currentTokens[0]));
                    currentOrder.setCustomerName(currentTokens[1]);
                    currentOrder.setState(currentTokens[2]);
                    currentOrder.setTaxRate(Double.parseDouble(currentTokens[3]));
                    currentOrder.setProductType(currentTokens[4]);
                    currentOrder.setArea(Double.parseDouble(currentTokens[5]));
                    currentOrder.setCostPerSquareFoot(Double.parseDouble(currentTokens[6]));
                    currentOrder.setLaborCostPerSquareFoot(Double.parseDouble(currentTokens[7]));
                    currentOrder.setMaterialCost(Double.parseDouble(currentTokens[8]));
                    currentOrder.setLaborCost(Double.parseDouble(currentTokens[9]));
                    currentOrder.setTax(Double.parseDouble(currentTokens[10]));
                    currentOrder.setTotal(Double.parseDouble(currentTokens[11]));

                    String str = currentOrder.getCustomerName();
                    if (str.contains("::")) {
                        currentOrder.setCustomerName(str.replace("::", ","));
                    }

                    ordersList.add(currentOrder);
                }
                sc.close();
            } else {
                throw new DataReadException();
            }
        } catch (DataReadException dre) {
            io.writeString("Data input form not found.");
        } catch (FileNotFoundException fnf) {
//            io.writeString("File not found.");
            saveOrders(date);
        }
    }

    @Override
    public void saveOrders(LocalDate date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddyyyy");
        String dateString = date.format(dtf);

        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Data/Orders_" + dateString + ".txt")));
            out.println(ORDER_TOC);
            for (Order currentOrder : ordersList) {
                String str = currentOrder.getCustomerName();
                if (str.contains(DELIMITER)) {
                    currentOrder.setCustomerName(str.replace(DELIMITER, "::"));
                }
                out.println(currentOrder.getOrderNumber() + DELIMITER
                        + currentOrder.getCustomerName() + DELIMITER
                        + currentOrder.getState() + DELIMITER
                        + currentOrder.getTaxRate() + DELIMITER
                        + currentOrder.getProductType() + DELIMITER
                        + currentOrder.getArea() + DELIMITER
                        + currentOrder.getCostPerSquareFoot() + DELIMITER
                        + currentOrder.getLaborCostPerSquareFoot() + DELIMITER
                        + currentOrder.getMaterialCost() + DELIMITER
                        + currentOrder.getLaborCost() + DELIMITER
                        + currentOrder.getTax() + DELIMITER + currentOrder.getTotal());
                out.flush();
            }
            out.close();
        } catch (IOException ex) {
            io.writeString("Error writing to file.");
        }
    }

}
