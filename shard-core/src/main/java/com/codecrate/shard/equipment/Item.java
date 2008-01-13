/*
 * Copyright 2004 codecrate consulting
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.codecrate.shard.equipment;

import java.math.BigDecimal;

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
 * Default implementation of an item.
 *
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
@Entity
@Indexed
public class Item implements Comparable {

    @Id
    @DocumentId
    @GeneratedValue 
    private int sequenceId;

    @Field(index=Index.TOKENIZED, store=Store.NO)    
	private String name;

    private BigDecimal weight;
    private transient Money cost;

    @ManyToOne
    private Source source;

	/**
	 * hibernate constructor.
	 */
	private Item() {
	}

	/**
	 * default constructor.
	 * @param name
	 * @param weight
	 * @param money
	 */
    public Item(String name, BigDecimal weight, Money money, Source source) {
        this.name = name;
		this.weight = weight;
        this.cost = money;
        this.source = source;
    }

    public String toString() {
    	return name;
    }

    public int hashCode() {
    	return new HashCodeBuilder(3, 7)
    	.append(name)
    	.toHashCode();
    }

    public boolean equals(Object object) {
    	if (this == object) {
    		return true;
    	}

    	if (!(object instanceof Item)) {
    		return false;
    	}
    	Item target = (Item) object;
    	return new EqualsBuilder()
	    	.append(name, target.name)
	    	.isEquals();
    }

    public int compareTo(Object object) {
        Item item = (Item) object;
        return name.compareTo(item.name);
    }

    /**
     * get the name of the item.
     */
    public String getName() {
    	return name;
    }

	/**
	 * gets the weight.
	 * TODO a Weight object should be created to encapsulate unit of measure 
	 */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * get the cost of the item.
     */
    public Money getCost() {
        return cost;
    }

	public void setCost(Money cost) {
		this.cost = cost;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}
