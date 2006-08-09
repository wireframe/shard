package com.codecrate.shard.transfer.pcgen.tag;

import java.util.StringTokenizer;


public class ConcatTagValueAggregator implements TagValueAggregator {

    private final String seperator;

    public ConcatTagValueAggregator(String seperator) {
        this.seperator = seperator;
    }

    public String aggregateValue(String oldValue, String tagValue) {
        return oldValue + seperator + tagValue;
    }
    
    public StringTokenizer parseAggregatedValue(String value) {
    	return new StringTokenizer(value, seperator);
    }

}
