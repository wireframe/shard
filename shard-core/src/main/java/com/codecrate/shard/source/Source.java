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
package com.codecrate.shard.source;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Represents the source of a ruleset.
 * 
 * @author rsonnek
 */
@Entity
public class Source implements Comparable {
    public static final Source CUSTOM = new Source("Custom", "custom", "http://shard.codecrate.com");

    @Id
    @GeneratedValue 
    private int sequenceId;

    private String name;
    private String abbreviation;
    private String url;

    /**
     * hibernate constructor.
     */
    private Source() {
    }

    public Source(String name, String abbreviation, String url) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.url = url;
    }

    public String toString() {
        return abbreviation;
    }

    public int hashCode() {
        return new HashCodeBuilder(3, 7).append(name).toHashCode();
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Source)) {
            return false;
        }
        Source target = (Source) object;
        return new EqualsBuilder()
            .append(name, target.name)
            .isEquals();
    }

    public int compareTo(Object object) {
        Source target = (Source) object;
        return abbreviation.compareTo(target.abbreviation);
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
