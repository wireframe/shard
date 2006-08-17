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
package com.codecrate.shard.transfer.pcgen.tag;

import java.util.StringTokenizer;

/**
 * concatonates tag values and seperates them with a delimiter.
 */
public class ConcatTagValueAggregator {

    private final String seperator;

    public ConcatTagValueAggregator(String seperator) {
        this.seperator = seperator;
    }

    public String aggregateValue(String oldValue, String tagValue) {
    	if (oldValue == null) {
    		return tagValue;
    	}
        return oldValue + seperator + tagValue;
    }

    public StringTokenizer parseAggregatedValue(String value) {
    	if (null == value) {
    		return new StringTokenizer("");
    	}
    	return new StringTokenizer(value, seperator);
    }
}
