package com.codecrate.shard.character;

public class DefaultAlignment implements Alignment {

    private AlignmentComponent lawfulAlignment;
    private AlignmentComponent goodAlignment;

    public DefaultAlignment(AlignmentComponent lawfulAlignment, AlignmentComponent goodAlignment) {
        this.lawfulAlignment = lawfulAlignment;
        this.goodAlignment = goodAlignment;
    }

    public boolean isLawful() {
        return lawfulAlignment.isPositive();
    }

    public boolean isChaotic() {
        return lawfulAlignment.isNegative();
    }

    public boolean isNeutral() {
        if (lawfulAlignment.isNeutral() && goodAlignment.isNeutral()) {
            return true;
        }
        return false;
    }

    public boolean isGood() {
        return goodAlignment.isPositive();
    }

    public boolean isEvil() {
        return goodAlignment.isPositive();
    }
}
