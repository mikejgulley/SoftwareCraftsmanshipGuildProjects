package com.swcguild.flooringmastery.dto;

import java.text.DecimalFormat;
import java.util.Objects;

/**
 *
 * @author Michael J. Gulley & Zach Sullivan
 */
public class Order {

    DecimalFormat df = new DecimalFormat("#0.00");
    
    private int orderNumber;
    private String customerName;
    private String state;
    private double taxRate;
    private String productType;
    private double area;
    private double costPerSquareFoot;
    private double laborCostPerSquareFoot;
    private double materialCost;
    private double laborCost;
    private double tax;
    private double total;

    public Order() {

    }

    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public void setCostPerSquareFoot(double costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public double getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(double laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    public double getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(double materialCost) {
        this.materialCost = materialCost;
    }

    public double getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(double laborCost) {
        this.laborCost = laborCost;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

//    @Override
//    public int hashCode() {
//        int hash = 3;
//        hash = 97 * hash + Objects.hashCode(this.customerName);
//        hash = 97 * hash + Objects.hashCode(this.state);
//        hash = 97 * hash + (int) (Double.doubleToLongBits(this.taxRate) ^ (Double.doubleToLongBits(this.taxRate) >>> 32));
//        hash = 97 * hash + Objects.hashCode(this.productType);
//        hash = 97 * hash + (int) (Double.doubleToLongBits(this.area) ^ (Double.doubleToLongBits(this.area) >>> 32));
//        hash = 97 * hash + (int) (Double.doubleToLongBits(this.costPerSquareFoot) ^ (Double.doubleToLongBits(this.costPerSquareFoot) >>> 32));
//        hash = 97 * hash + (int) (Double.doubleToLongBits(this.laborCostPerSquareFoot) ^ (Double.doubleToLongBits(this.laborCostPerSquareFoot) >>> 32));
//        hash = 97 * hash + (int) (Double.doubleToLongBits(this.materialCost) ^ (Double.doubleToLongBits(this.materialCost) >>> 32));
//        hash = 97 * hash + (int) (Double.doubleToLongBits(this.laborCost) ^ (Double.doubleToLongBits(this.laborCost) >>> 32));
//        hash = 97 * hash + (int) (Double.doubleToLongBits(this.tax) ^ (Double.doubleToLongBits(this.tax) >>> 32));
//        hash = 97 * hash + (int) (Double.doubleToLongBits(this.total) ^ (Double.doubleToLongBits(this.total) >>> 32));
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final Order other = (Order) obj;
//        if (!Objects.equals(this.customerName, other.customerName)) {
//            return false;
//        }
//        if (!Objects.equals(this.state, other.state)) {
//            return false;
//        }
//        if (Double.doubleToLongBits(this.taxRate) != Double.doubleToLongBits(other.taxRate)) {
//            return false;
//        }
//        if (!Objects.equals(this.productType, other.productType)) {
//            return false;
//        }
//        if (Double.doubleToLongBits(this.area) != Double.doubleToLongBits(other.area)) {
//            return false;
//        }
//        if (Double.doubleToLongBits(this.costPerSquareFoot) != Double.doubleToLongBits(other.costPerSquareFoot)) {
//            return false;
//        }
//        if (Double.doubleToLongBits(this.laborCostPerSquareFoot) != Double.doubleToLongBits(other.laborCostPerSquareFoot)) {
//            return false;
//        }
//        if (Double.doubleToLongBits(this.materialCost) != Double.doubleToLongBits(other.materialCost)) {
//            return false;
//        }
//        if (Double.doubleToLongBits(this.laborCost) != Double.doubleToLongBits(other.laborCost)) {
//            return false;
//        }
//        if (Double.doubleToLongBits(this.tax) != Double.doubleToLongBits(other.tax)) {
//            return false;
//        }
//        if (Double.doubleToLongBits(this.total) != Double.doubleToLongBits(other.total)) {
//            return false;
//        }
//        return true;
//    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.orderNumber;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.orderNumber != other.orderNumber) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Order Number: " + orderNumber + "\n\tCustomer Name: "
                + customerName + "\n\tState: " + state + "\n\tTax Rate: " + df.format(taxRate)
                + "%\n\tProduct Type: " + productType + "\n\tArea: " + df.format(area)
                + "sq. ft.\n\tCost Per Square Foot: $" + df.format(costPerSquareFoot) + "\n\tLabor Cost Per Square Foot: $"
                + df.format(laborCostPerSquareFoot) + "\n\tMaterial Cost: $"
                + df.format(materialCost) + "\n\tLabor Cost: $" + df.format(laborCost) + "\n\tTax: $"
                + df.format(tax) + "\n\tTotal: $" + df.format(total);
    }

}
