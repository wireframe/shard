package com.codecrate.shard.character;

public class Age {

    private int currentAge;
    private int maxAge;
    private AgeCategory category;
    
    public Age(int currentAge, int maxAge, AgeCategory category) {
        this.currentAge = currentAge;
        this.maxAge = maxAge;
        this.category = category;
    }
    
    public String toString() {
        return Integer.toString(currentAge) + " (" + category + ")";
    }
    
    public AgeCategory getCategory() {
        return category;
    }
    
    public int getCurrentAge() {
        return currentAge;
    }
    
    public int getMaxAge() {
        return maxAge;
    }
}
