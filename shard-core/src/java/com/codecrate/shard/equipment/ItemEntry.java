package com.codecrate.shard.equipment;

import java.math.BigDecimal;

public class ItemEntry implements Item {
    private final Item item;
    private final int quantity;

    public ItemEntry(Item item, int quantity) {
        if (0 >= quantity) {
            throw new IllegalArgumentException("Cannot have a quantity less than one.");
        }
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public String getName() {
    	return item.getName();
    }
    
    public BigDecimal getWeight() {
        return item.getWeight().multiply(new BigDecimal(quantity));
    }

    public Money getCost() {
        return item.getCost().multiply(new BigDecimal(quantity));
    }
}
