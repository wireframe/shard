package com.codecrate.shard.transfer.pcgen.tag;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.codecrate.shard.transfer.pcgen.AbstractPcgenLineHandler;

public class PcgenTags {
    private static final Log LOG = LogFactory.getLog(AbstractPcgenLineHandler.class);
	private static final String TAG_SEPERATOR = "\t";
    private static final String TAG_NAME_VALUE_SEPERATOR = ":";
    private static final String TRUE_TAG_VALUE = "YES";

    private final TagValueAggregator tagValueAggregator;
    private final Map tags = new HashMap();

	public PcgenTags(String line) {
		this(line, new NoOpTagValueAggregator());
	}

	public PcgenTags(String line, TagValueAggregator aggregator) {
		this.tagValueAggregator = aggregator;

		parseTags(line);
	}

	private Map parseTags(String line) {
		StringTokenizer tokens = new StringTokenizer(line, TAG_SEPERATOR);
		while (tokens.hasMoreTokens()) {
			String token = tokens.nextToken();
			int seperatorIndex = token.indexOf(TAG_NAME_VALUE_SEPERATOR);
			if (-1 != seperatorIndex) {
				String tagName = token.substring(0, seperatorIndex).trim();
				String tagValue = token.substring(seperatorIndex + 1, token.length()).trim();

				addTagValue(tagName, tagValue);
			}
		}
		return tags;
	}

	public void addTagValue(String name, String value) {
		String oldValue = (String) tags.get(name);
        String newValue = tagValueAggregator.aggregateValue(oldValue, value);

		tags.put(name, newValue);

	}
	public String getStringTagValue(String tagName) {
        return getStringTagValue(tagName, null);
    }

    public String getStringTagValue(String tagName, String defaultValue) {
        String value = (String) tags.get(tagName);
        if (null == value) {
            LOG.debug("No value found for tag " + tagName);
            return defaultValue;
        }
        return value;
    }

    public int getIntTagValue(String tagName) {
        return getIntTagValue(tagName, 0);
    }

    public int getIntTagValue(String tagName, int defaultValue) {
        String value = (String) tags.get(tagName);
        if (null == value) {
            LOG.debug("No value found for tag " + tagName + " defaulting to " + defaultValue);
            return defaultValue;
        }
        return Integer.parseInt(value);
    }

    public boolean getBooleanTagValue(String tagName, boolean defaultValue) {
        String value = (String) tags.get(tagName);
        if (null == value) {
            LOG.debug("No value found for tag " + tagName + " defaulting to " + defaultValue);
            return defaultValue;
        }
        return (TRUE_TAG_VALUE.equals(value));
    }


	public boolean hasTag(String tagName) {
		return (null != tags.get(tagName));
	}

}
