package com.codecrate.shard.magic;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Spell {
    private String id;
    private String name;
    private String summary;

    /**
     * hibernate constructor.
     */
    private Spell() {
    }

    public Spell(String name, String summary) {
        this.name = name;
        this.summary = summary;
    }

    public String toString() {
        return name;
    }

    public int hashCode() {
        return new HashCodeBuilder(3, 7).append(name).toHashCode();
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Spell)) {
            return false;
        }
        Spell target = (Spell) object;
        return new EqualsBuilder()
            .append(name, target.name)
            .isEquals();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
