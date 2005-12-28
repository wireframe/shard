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

import com.codecrate.shard.character.prereq.CharacterPrerequisite;
import com.codecrate.shard.source.Source;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface Feat {

    String getName();
    
    /**
     * get the type of feat.
     * ex: General, Item Creation, Meta Magic
     * @return
     */
    String getType();

    /**
     * get a brief summary of the feat.
     * ex: Add +2 bonus to Spot and Listen checks. 
     * @return
     */
    String getSummary();
    
    /**
     * get any prerequisites for this feat.
     * @return
     */
    CharacterPrerequisite getPrerequisite();

    Source getSource();
}
