package com.codecrate.shard.magic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.codecrate.shard.source.Source;

/**
 * magic spell information
 */
@Entity
@Indexed
public class Spell implements Comparable<Spell> {
    @Id
    @DocumentId
    @GeneratedValue 
    private int sequenceId;
   
    @Field(index=Index.TOKENIZED, store=Store.NO)    
    private String name;
    @Field(index=Index.TOKENIZED, store=Store.NO)    
    private String summary;
    private String school;

    @ManyToOne
    private Source source;

    private boolean divine = false;
    private boolean arcane = true;

    /**
     * hibernate constructor.
     */
    private Spell() {
    }

    public Spell(String name, String summary, String school, Source source) {
        this.name = name;
        this.summary = summary;
        this.school = school;
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

    public int compareTo(Spell target) {
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

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public boolean isArcane() {
        return arcane;
    }

    public void setArcane(boolean arcane) {
        this.arcane = arcane;
    }

    public boolean isDivine() {
        return divine;
    }

    public void setDivine(boolean divine) {
        this.divine = divine;
    }
}
