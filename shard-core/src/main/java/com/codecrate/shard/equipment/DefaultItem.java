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
import java.text.ParseException;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.codecrate.shard.source.Source;

/**
 * Default implementation of an item.
 * adds id field to be persistable.
 *
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultItem implements Item, Comparable {
	private String id;
    private BigDecimal weight;
    private Money cost;
	private String name;
    private Source source;

	/**
	 * hibernate constructor.
	 */
	private DefaultItem() {
	}

	/**
	 * default constructor.
	 * @param name
	 * @param weight
	 * @param money
	 */
    public DefaultItem(String name, BigDecimal weight, Money money, Source source) {
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

    	if (!(object instanceof DefaultItem)) {
    		return false;
    	}
    	DefaultItem target = (DefaultItem) object;
    	return new EqualsBuilder()
	    	.append(name, target.name)
	    	.isEquals();
    }

    public int compareTo(Object object) {
        DefaultItem item = (DefaultItem) object;
        return name.compareTo(item.name);
    }

    public String getName() {
    	return name;
    }

    public BigDecimal getWeight() {
        return weight;
    }

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
