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
package com.codecrate.shard.character.prereq;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.codecrate.shard.character.Alignment;
import com.codecrate.shard.character.Deity;
import com.codecrate.shard.character.PlayerCharacter;

/**
 * Prerequisite to match Deity's alignment.
 * Used by cleric classes to verify that the character's alignment is 
 * within one of the deity's alignment.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DeityAlignmentPrerequisite implements CharacterPrerequisite {
    private static final Log LOG = LogFactory.getLog(DeityAlignmentPrerequisite.class);
    
    public boolean hasMetPrerequisite(PlayerCharacter character) {
        Deity deity = character.getDeity();
        if (null == deity) {
            LOG.info("Deity required to meet prerequisite.");
            return false;
        }
        
        Alignment deityAlignment = deity.getAlignment();
        Alignment characterAlignment = character.getAlignment();
        int deityEthicalAlignment = calculateEthicalCost(deityAlignment);
        int characterEthicalAlignment = calculateEthicalCost(characterAlignment);
        int ethicalResult = deityEthicalAlignment - characterEthicalAlignment;

        int deityMoralAlignment = calculateMoralCost(deityAlignment);
        int characterMoralAlign = calculateMoralCost(characterAlignment);
        int moralResult = deityMoralAlignment - characterMoralAlign;
        
        int diff = Math.abs(ethicalResult) + Math.abs(moralResult);
        if (1 < diff) {
            LOG.info("Character's alignment is not within 1 of deity's alignment.");
            return false;
        }
        
        return true;
    }

    private int calculateMoralCost(Alignment alignment) {
        if (alignment.isGood()) {
            return 1;
        }
        if (alignment.isMoralNeutral()) {
            return 2;
        }
        return 3;
    }

    private int calculateEthicalCost(Alignment alignment) {
        if (alignment.isLawful()) {
            return 1;
        }
        if (alignment.isEthicalNeutral()) {
            return 2;
        }
        return 3;
    }

}
