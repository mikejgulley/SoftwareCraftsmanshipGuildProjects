package com.swcguild.vendingmachinemvc.dao;

import com.swcguild.vendingmachinemvc.dto.Item;
import java.util.List;

/**
 *
 * @author Michael J. Gulley
 */
public interface VendingMachineDao {
    public void addItem(Item item);
    public void removeItem(String itemId);
    public void updateItem(Item item);
    public Item getItemById(String id);
    public List<Item> getAvailableItems();
    public List<Item> getAllItems();
    public void loadInventory();
    public void writeInventory();
}
