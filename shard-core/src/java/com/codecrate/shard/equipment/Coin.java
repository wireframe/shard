package com.codecrate.shard.equipment;

public class Coin implements Item {
    public static final Coin COPPER_PIECE = new Coin(1, 1, DefaultCurrency.COPPER);
    public static final Coin SILVER_PIECE = new Coin(1, 1, DefaultCurrency.SILVER);
    public static final Coin GOLD_PIECE = new Coin(1, 1, DefaultCurrency.GOLD);
    
    private final int weight;
    private final Money money;

    public Coin(int weight, int cost, Currency currency) {
        this.weight = weight;
        this.money = new Money(cost, currency);
    }
    
    public int getWeight() {
        return weight;
    }

    public Money getCost() {
        return money;
    }
}
