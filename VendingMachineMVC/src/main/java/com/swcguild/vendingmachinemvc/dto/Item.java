package com.swcguild.vendingmachinemvc.dto;

import java.text.DecimalFormat;
import java.util.Objects;

/**
 *
 * @author Michael J. Gulley
 */
public class Item implements Comparable<Item> {

    DecimalFormat df = new DecimalFormat("#0.00");

    private String id;
    private String name;
    private double price;
//    private BigDecimal price;
    private int quantity;
    private String imgSrc;

    public Item() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.df);
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 97 * hash + this.quantity;
        hash = 97 * hash + Objects.hashCode(this.imgSrc);
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
        final Item other = (Item) obj;
        if (!Objects.equals(this.df, other.df)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        if (!Objects.equals(this.imgSrc, other.imgSrc)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Item{" + "df=" + df + ", id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", imgSrc=" + imgSrc + '}';
    }

    @Override
    public int compareTo(Item o) {
        return id.compareTo(o.id);
    }

}
