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
package com.codecrate.shard.skill;

import java.util.Iterator;

import com.codecrate.shard.modifier.DefaultKeyedModifier;
import com.codecrate.shard.modifier.DefaultModifierType;
import com.codecrate.shard.modifier.KeyedModifier;
import com.codecrate.shard.modifier.ModifierListener;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class SkillRankSynergyListener implements ModifierListener {
    private static final int SYNERGY_THRESHOLD = 5;
    
    private int previousRank = 0;
    private final SkillEntry entry;
    private final SkillEntryContainer container;
    
    public SkillRankSynergyListener(SkillEntry entry, SkillEntryContainer container) {
        this.entry = entry;
        this.container = container;
        
        entry.addListener(this);
        previousRank = entry.getRank();
    }
    
    public void onModify() {
        int rank = entry.getRank();
        if (previousRank < SYNERGY_THRESHOLD && rank >= SYNERGY_THRESHOLD) {
            Iterator it = entry.getSkill().getChildSkillSynergies().iterator();
            while (it.hasNext()) {
                Skill skill = (Skill) it.next();
                KeyedModifier modifier = new DefaultKeyedModifier(skill, DefaultModifierType.SYNERGY, 2);
                container.addModifier(modifier);
            }
        }
    }
}
