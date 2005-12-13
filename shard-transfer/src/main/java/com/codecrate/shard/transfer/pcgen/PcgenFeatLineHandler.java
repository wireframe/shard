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

import com.codecrate.shard.feat.Feat;
import com.codecrate.shard.feat.FeatDao;
import com.codecrate.shard.feat.FeatFactory;
import com.codecrate.shard.source.Source;

public class PcgenFeatLineHandler extends AbstractPcgenLineHandler {
	private static final String DESCRIPTION_TAG_NAME = "DESC";

    private final FeatFactory featFactory;
    private final FeatDao featDao;

    public PcgenFeatLineHandler(FeatFactory featFactory, FeatDao featDao) {
        this.featFactory = featFactory;
        this.featDao = featDao;
    }

    public Object handleParsedLine(String name, Map tags, Source source) {
    	String description = getStringTagValue(DESCRIPTION_TAG_NAME, tags);

        Feat feat = featFactory.createFeat(name, description);
        return featDao.saveFeat(feat);
    }
}
