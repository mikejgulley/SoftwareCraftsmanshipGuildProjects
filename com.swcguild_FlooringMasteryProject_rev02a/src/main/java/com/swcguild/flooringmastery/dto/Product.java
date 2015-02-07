package com.swcguild.flooringmastery.dto;

import java.util.Objects;

/**
 *
 * @author Michael J. Gulley & Zach Sullivan
 */
public class Product {
    private String productType;
    private double productCostPerSquareFoot;
    private double laborCostPerSquareFoot;

    public Product() {
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getProductCostPerSquareFoot() {
        return productCostPerSquareFoot;
    }

    public void setProductCostPerSquareFoot(double productCostPerSquareFoot) {
        this.productCostPerSquareFoot = productCostPerSquareFoot;
    }

    public double getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(double laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.productType);
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.productCostPerSquareFoot) ^ (Double.doubleToLongBits(this.productCostPerSquareFoot) >>> 32));
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.laborCostPerSquareFoot) ^ (Double.doubleToLongBits(this.laborCostPerSquareFoot) >>> 32));
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
        final Product other = (Product) obj;
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (Double.doubleToLongBits(this.productCostPerSquareFoot) != Double.doubleToLongBits(other.productCostPerSquareFoot)) {
            return false;
        }
        if (Double.doubleToLongBits(this.laborCostPerSquareFoot) != Double.doubleToLongBits(other.laborCostPerSquareFoot)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product{" + "productType=" + productType + ", productCostPerSquareFoot=" + productCostPerSquareFoot + ", laborCostPerSquareFoot=" + laborCostPerSquareFoot + '}';
    }
    
}
