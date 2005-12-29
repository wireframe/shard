package com.codecrate.shard.transfer.pcgen;

import com.codecrate.shard.transfer.pcgen.tag.TagValueAggregator;

public class ConcatTagValueAggregator implements TagValueAggregator {

    private final String seperator;

    public ConcatTagValueAggregator(String seperator) {
        this.seperator = seperator;
    }

    public String aggregateValue(String oldValue, String tagValue) {
        return oldValue + seperator + tagValue;
    }

}
