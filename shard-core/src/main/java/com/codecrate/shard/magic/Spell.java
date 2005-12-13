package com.codecrate.shard.magic;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.codecrate.shard.source.Source;

public class Spell implements Comparable {
    private String id;
    private String name;
    private String summary;
    private Source source;

    /**
     * hibernate constructor.
     */
    private Spell() {
    }

    public Spell(String name, String summary, Source source) {
        this.name = name;
        this.summary = summary;
        this.source = source;
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

    public int compareTo(Object object) {
        Spell target = (Spell) object;
        return name.compareTo(target.name);
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

    public String getId() {
        return id;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}
