package com.swcguild.flooringmastery.dao;

import com.swcguild.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Michael J. Gulley & Zach Sullivan
 */
public interface OrderDAO {
    public void addOrder(Order order);
    public int genOrderNumber();
    public void updateOrder(Order order);
    public ArrayList<Order> getOrders();
    public void removeOrder(int orderNumber);
    public void removeOrderByOrder(LocalDate date, Order order);
    public void removeAllOrdersByCurrentDate(LocalDate date);
    public Order getOrderByOrderNumber(int orderNumber);
    public void loadOrdersByDate(LocalDate date);
    public void saveOrders(LocalDate date);
    public void clearAll();
}
