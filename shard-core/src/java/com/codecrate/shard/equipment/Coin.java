package com.codecrate.shard.equipment;

import java.math.BigDecimal;

public class Coin implements Item {
    public static final Coin COPPER_PIECE = new Coin(new BigDecimal(".1"), 1, DefaultCurrency.COPPER);
    public static final Coin SILVER_PIECE = new Coin(new BigDecimal(".1"), 1, DefaultCurrency.SILVER);
    public static final Coin GOLD_PIECE = new Coin(new BigDecimal(".1"), 1, DefaultCurrency.GOLD);
    
    private final BigDecimal weight;
    private final Money money;

    public Coin(BigDecimal weight, int cost, Currency currency) {
        this.weight = weight;
        this.money = new Money(cost, currency);
    }
    
    public BigDecimal getWeight() {
        return weight;
    }

    public Money getCost() {
        return money;
    }
}
