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
package com.codecrate.shard.feat;

import java.util.Collection;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface FeatDao {

    /**
     * get all feats.
     * @return
     */
    Collection<Feat> getFeats();
    
    /**
     * update a feat.
     * saves changes to a feat.
     * @param feat
     */
    void updateFeat(Feat feat);

    /**
     * delete a feat.
     * @param feat
     */
    void deleteFeat(Feat feat);

    /**
     * saves a new feat.
     * @param feat
     */
	Feat saveFeat(Feat feat);

	/**
	 * get feats with the given search query
	 * @param query
	 * @return
	 */
	Collection<Feat> searchFeats(String query);
}
