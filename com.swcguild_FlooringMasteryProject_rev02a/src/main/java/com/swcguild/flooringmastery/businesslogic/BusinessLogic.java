package com.swcguild.flooringmastery.businesslogic;

import com.swcguild.flooringmastery.dao.OrderDAOFileImpl;
import com.swcguild.flooringmastery.dao.ProductDAOFileImpl;
import com.swcguild.flooringmastery.dao.TaxDAOFileImpl;
import com.swcguild.flooringmastery.dto.Order;
import com.swcguild.flooringmastery.ui.ConsoleIOrev2;
import java.text.DecimalFormat;

/**
 *
 * @author Michael J. Gulley & Zach Sullivan
 */
public class BusinessLogic {

    public static final String COUNTER_FILE = "Data/ordercounter.txt";
    DecimalFormat df = new DecimalFormat("#0.00");
    OrderDAOFileImpl odfi = new OrderDAOFileImpl();
    TaxDAOFileImpl tdfi = new TaxDAOFileImpl();
    ProductDAOFileImpl pdfi = new ProductDAOFileImpl();
    ConsoleIOrev2 io = new ConsoleIOrev2();

    public Order buildOrder(String customerName, String state, String productType, double area) {
        Order currentOrder = new Order();
        currentOrder.setOrderNumber(odfi.genOrderNumber());
        currentOrder.setCustomerName(customerName);
        currentOrder.setState(state);
        currentOrder.setTaxRate(tdfi.getTaxRate(state));
        currentOrder.setProductType(productType);
        currentOrder.setArea(area);
        currentOrder.setCostPerSquareFoot(pdfi.getProductByType(productType).getProductCostPerSquareFoot());
        currentOrder.setLaborCostPerSquareFoot(pdfi.getProductByType(productType).getLaborCostPerSquareFoot());
        currentOrder.setMaterialCost(Double.parseDouble(calcMaterialCost(currentOrder.getArea(), currentOrder.getCostPerSquareFoot())));
        currentOrder.setLaborCost(Double.parseDouble(calcLaborCost(currentOrder.getArea(), currentOrder.getLaborCostPerSquareFoot())));
        currentOrder.setTax(Double.parseDouble(calcTax(currentOrder.getLaborCost(), currentOrder.getMaterialCost(), currentOrder.getTaxRate())));
        currentOrder.setTotal(Double.parseDouble(calcFinalTotal(currentOrder.getMaterialCost(), currentOrder.getLaborCost(), currentOrder.getTax())));

        return currentOrder;
    }

    public Order updateOrder(int orderNumber, String customerName, String state, String productType, double area) {
        Order updatedOrder = new Order();
        updatedOrder.setOrderNumber(orderNumber);
        updatedOrder.setCustomerName(customerName);
        updatedOrder.setState(state);
        updatedOrder.setTaxRate(tdfi.getTaxRate(state));
        updatedOrder.setProductType(productType);
        updatedOrder.setArea(area);
        updatedOrder.setCostPerSquareFoot(pdfi.getProductByType(productType).getProductCostPerSquareFoot());
        updatedOrder.setLaborCostPerSquareFoot(pdfi.getProductByType(productType).getLaborCostPerSquareFoot());
        updatedOrder.setMaterialCost(Double.parseDouble(calcMaterialCost(updatedOrder.getArea(), updatedOrder.getCostPerSquareFoot())));
        updatedOrder.setLaborCost(Double.parseDouble(calcLaborCost(updatedOrder.getArea(), updatedOrder.getLaborCostPerSquareFoot())));
        updatedOrder.setTax(Double.parseDouble(calcTax(updatedOrder.getLaborCost(), updatedOrder.getMaterialCost(), updatedOrder.getTaxRate())));
        updatedOrder.setTotal(Double.parseDouble(calcFinalTotal(updatedOrder.getMaterialCost(), updatedOrder.getLaborCost(), updatedOrder.getTax())));

        return updatedOrder;
    }

    private String calcMaterialCost(double area, double costPerSquareFoot) {
        return df.format(area * costPerSquareFoot);
    }

    private String calcLaborCost(double area, double laborCostPerSquareFoot) {
        return df.format(area * laborCostPerSquareFoot);
    }

    private String calcTax(double laborCost, double materialCost, double taxRate) {
        return df.format((laborCost + materialCost) * (taxRate / 100));
    }

    private String calcFinalTotal(double materialCost, double laborCost, double tax) {
        return df.format(materialCost + laborCost + tax);
    }
}