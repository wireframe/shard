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
package com.codecrate.shard.character;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.easymock.MockControl;

import com.codecrate.shard.ability.AbilityScore;
import com.codecrate.shard.ability.AbilityScoreContainer;
import com.codecrate.shard.ability.DefaultAbility;
import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.kit.ClassLevel;
import com.codecrate.shard.race.Race;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultCharacterLevelTest extends TestCase {

    public void testFirstLevelGetsFourTimesSkillPoints() {
        MockControl mockScore = MockControl.createControl(AbilityScore.class);
        AbilityScore score = (AbilityScore) mockScore.getMock();
        score.getModifier();
        mockScore.setReturnValue(2);
        mockScore.replay();
        
        MockControl mockAbilities = MockControl.createControl(AbilityScoreContainer.class);
        AbilityScoreContainer abilities = (AbilityScoreContainer) mockAbilities.getMock();
        abilities.hasAbilityScore(DefaultAbility.INTELLIGENCE);
        mockAbilities.setReturnValue(true);
        abilities.getIntelligence();
        mockAbilities.setReturnValue(score);
        mockAbilities.replay();
        
        MockControl mockRace = MockControl.createControl(Race.class);
        Race race = (Race) mockRace.getMock();
        race.getBaseSkillPointsPerLevel();
        mockRace.setReturnValue(0);
        mockRace.replay();
        
        MockControl mockCharacter = MockControl.createControl(PlayerCharacter.class);
        PlayerCharacter character = (PlayerCharacter) mockCharacter.getMock();
        character.getAbilities();
        mockCharacter.setReturnValue(abilities);
        character.getRace();
        mockCharacter.setReturnValue(race);
        mockCharacter.replay();

        MockControl mockKit = MockControl.createControl(CharacterClass.class);
        CharacterClass kit = (CharacterClass) mockKit.getMock();
        kit.getBaseSkillPointsPerLevel();
        mockKit.setReturnValue(0);
        mockKit.replay();
        
        MockControl mockClassLevel = MockControl.createControl(ClassLevel.class);
        ClassLevel classLevel = (ClassLevel) mockClassLevel.getMock();
        classLevel.getCharacterClass();
        mockClassLevel.setReturnValue(kit);
        mockClassLevel.replay();
        
        DefaultCharacterLevel level = new DefaultCharacterLevel(character, 1, 1, classLevel, new ArrayList());
        int skillPoints = level.getSkillPoints();
        assertEquals(8, skillPoints);
    }

    public void testCanCalculateWithoutIntelligenceModifier() {
        MockControl mockAbilities = MockControl.createControl(AbilityScoreContainer.class);
        AbilityScoreContainer abilities = (AbilityScoreContainer) mockAbilities.getMock();
        abilities.hasAbilityScore(DefaultAbility.INTELLIGENCE);
        mockAbilities.setReturnValue(false);
        mockAbilities.replay();
        
        MockControl mockRace = MockControl.createControl(Race.class);
        Race race = (Race) mockRace.getMock();
        race.getBaseSkillPointsPerLevel();
        mockRace.setReturnValue(0);
        mockRace.replay();
        
        MockControl mockCharacter = MockControl.createControl(PlayerCharacter.class);
        PlayerCharacter character = (PlayerCharacter) mockCharacter.getMock();
        character.getAbilities();
        mockCharacter.setReturnValue(abilities);
        character.getRace();
        mockCharacter.setReturnValue(race);
        mockCharacter.replay();

        MockControl mockKit = MockControl.createControl(CharacterClass.class);
        CharacterClass kit = (CharacterClass) mockKit.getMock();
        kit.getBaseSkillPointsPerLevel();
        mockKit.setReturnValue(1);
        mockKit.replay();
        
        MockControl mockClassLevel = MockControl.createControl(ClassLevel.class);
        ClassLevel classLevel = (ClassLevel) mockClassLevel.getMock();
        classLevel.getCharacterClass();
        mockClassLevel.setReturnValue(kit);
        mockClassLevel.replay();
        
        DefaultCharacterLevel level = new DefaultCharacterLevel(character, 2, 1, classLevel, new ArrayList());
        int skillPoints = level.getSkillPoints();
        assertEquals(1, skillPoints);
    }
    
    public void testRacialBonusPointsAddedToCalculation() {
        MockControl mockAbilities = MockControl.createControl(AbilityScoreContainer.class);
        AbilityScoreContainer abilities = (AbilityScoreContainer) mockAbilities.getMock();
        abilities.hasAbilityScore(DefaultAbility.INTELLIGENCE);
        mockAbilities.setReturnValue(false);
        mockAbilities.replay();
        
        MockControl mockRace = MockControl.createControl(Race.class);
        Race race = (Race) mockRace.getMock();
        race.getBaseSkillPointsPerLevel();
        mockRace.setReturnValue(1);
        mockRace.replay();
        
        MockControl mockCharacter = MockControl.createControl(PlayerCharacter.class);
        PlayerCharacter character = (PlayerCharacter) mockCharacter.getMock();
        character.getAbilities();
        mockCharacter.setReturnValue(abilities);
        character.getRace();
        mockCharacter.setReturnValue(race);
        mockCharacter.replay();

        MockControl mockKit = MockControl.createControl(CharacterClass.class);
        CharacterClass kit = (CharacterClass) mockKit.getMock();
        kit.getBaseSkillPointsPerLevel();
        mockKit.setReturnValue(0);
        mockKit.replay();
        
        MockControl mockClassLevel = MockControl.createControl(ClassLevel.class);
        ClassLevel classLevel = (ClassLevel) mockClassLevel.getMock();
        classLevel.getCharacterClass();
        mockClassLevel.setReturnValue(kit);
        mockClassLevel.replay();
        
        DefaultCharacterLevel level = new DefaultCharacterLevel(character, 2, 1, classLevel, new ArrayList());
        int skillPoints = level.getSkillPoints();
        assertEquals(1, skillPoints);
    }
}
