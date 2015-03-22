package com.swcguild.vendingmachinemvc.dao;

import com.swcguild.vendingmachinemvc.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachineDaoInMemImpl implements VendingMachineDao {

    Map<String, Item> inventoryMap = new HashMap<>();

    public VendingMachineDaoInMemImpl() {
        Item item1 = new Item();
        item1.setId("A1");
        item1.setName("Donuts");
        item1.setPrice(1.25);
//        item1.setPrice(BigDecimal.valueOf(1.25));
        item1.setQuantity(0);
        item1.setImgSrc("./img/donuts2.jpg");

        Item item2 = new Item();
        item2.setId("A2");
        item2.setName("M&M's");
        item2.setPrice(0.76);
//        item2.setPrice(BigDecimal.valueOf(0.76));
        item2.setQuantity(2);
        item2.setImgSrc("./img/m&ms.jpg");

        Item item3 = new Item();
        item3.setId("B1");
        item3.setName("Snickers");
        item3.setPrice(0.75);
//        item3.setPrice(BigDecimal.valueOf(0.75));
        item3.setQuantity(2);
        item3.setImgSrc("./img/snickers.jpg");

        Item item4 = new Item();
        item4.setId("B2");
        item4.setName("Doritos");
        item4.setPrice(1.15);
//        item4.setPrice(BigDecimal.valueOf(1.15));
        item4.setQuantity(3);
        item4.setImgSrc("./img/doritos-nacho-cheese.png");

        Item item5 = new Item();
        item5.setId("C1");
        item5.setName("PB Twix");
        item5.setPrice(0.85);
//        item5.setPrice(BigDecimal.valueOf(0.85));
        item5.setQuantity(1);
        item5.setImgSrc("./img/pb_twix.jpg");

        Item item6 = new Item();
        item6.setId("C2");
        item6.setName("Oero's");
        item6.setPrice(1.73);
//        item6.setPrice(BigDecimal.valueOf(1.73));
        item6.setQuantity(3);
        item6.setImgSrc("./img/oreos.jpg");

        Item item7 = new Item();
        item7.setId("D1");
        item7.setName("Gum");
        item7.setPrice(0.65);
//        item7.setPrice(BigDecimal.valueOf(0.65));
        item7.setQuantity(4);
        item7.setImgSrc("./img/gum_doubleMint.jpg");

        Item item8 = new Item();
        item8.setId("D2");
        item8.setName("Mints");
        item8.setPrice(0.40);
        item8.setQuantity(6);
        item8.setImgSrc("./img/mints.png");

        inventoryMap.put(item1.getId(), item1);
        inventoryMap.put(item2.getId(), item2);
        inventoryMap.put(item3.getId(), item3);
        inventoryMap.put(item4.getId(), item4);
        inventoryMap.put(item5.getId(), item5);
        inventoryMap.put(item6.getId(), item6);
        inventoryMap.put(item7.getId(), item7);
        inventoryMap.put(item8.getId(), item8);
    }

    @Override
    public void addItem(Item item) {
        inventoryMap.put(item.getId(), item);
    }

    @Override
    public void removeItem(String itemId) {
        inventoryMap.remove(itemId);
    }

    @Override
    public void updateItem(Item item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item getItemById(String id) {
        return inventoryMap.get(id);
    }

    @Override
    public List<Item> getAvailableItems() {
        List<Item> availableItemsList = new ArrayList<>();
        for (Item currentItem : inventoryMap.values()) {
            if (currentItem.getQuantity() > 0) {
                availableItemsList.add(currentItem);
            }
        }
        return availableItemsList;
    }

    @Override
    public List<Item> getAllItems() {
        return new ArrayList<>(inventoryMap.values());
    }

    @Override
    public void loadInventory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeInventory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
