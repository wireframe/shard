package com.codecrate.shard.character;

public class AlignmentComponent {

    private Boolean alignment;
    
    public boolean isPositive() {
        if (null != alignment) {
            return alignment.booleanValue();
        }
        return false;
    }

    public boolean isNegative() {
        return !isPositive();
    }
    
    public boolean isNeutral() {
        if (null != alignment) {
            return false;
        }
        return true;
    }
}
