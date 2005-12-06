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
package com.codecrate.shard.transfer.pcgen;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * helper class for parsing PCGen LST files.
 */
public abstract class AbstractPcgenLineHandler implements PcgenObjectImporter.PcgenLineHandler {
    private static final Log LOG = LogFactory.getLog(AbstractPcgenLineHandler.class);

    private static final String TRUE_TAG_VALUE = "YES";

    protected String getStringTagValue(String tagName, Map tags) {
        String value = (String) tags.get(tagName);
        if (null == value) {
            LOG.info("No value found for tag " + tagName);
        }
        return value;
    }

    protected boolean getBooleanTagValue(String tagName, Map tags, boolean defaultValue) {
        String value = (String) tags.get(tagName);
        if (null == value) {
            LOG.info("No value found for tag " + tagName + " defaulting to " + defaultValue);
            return defaultValue;
        }
        return (TRUE_TAG_VALUE.equals(value));
    }
}
