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

import java.math.BigDecimal;

import com.codecrate.shard.ability.AbilityScoreContainer;
import com.codecrate.shard.armorclass.ArmorClass;
import com.codecrate.shard.equipment.ItemContainer;
import com.codecrate.shard.movement.Encumberance;
import com.codecrate.shard.race.Race;
import com.codecrate.shard.save.SavingThrowContainer;
import com.codecrate.shard.skill.DefaultSkillEntryContainer;
import com.codecrate.shard.skill.SkillEntryContainer;

/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface PlayerCharacter {
    /**
     * gets the challenge rating for the character.
     * can range from 0.1 to any number.
     * @return
     */
	BigDecimal getChallengeRating();
	
	/**
	 * gets the name of the character.
	 * @return
	 */
	String getName();
	
	/**
	 * gets the ability scores for the character.
	 * @return
	 */
	AbilityScoreContainer getAbilities();
	
	/**
	 * gets the saving throws for this character.
	 * @return
	 */
	SavingThrowContainer getSavingThrows();
	
	/**
	 * gets the skills for the character.
	 * @return
	 */
	SkillEntryContainer getSkills();
	
	/**
	 * gets the effective character level of a character.
	 * A character�s ECL affects the experience the character earns, 
	 * the amount of experience the character must have before gaining a new level, 
	 * and the character�s starting equipment.
	 * this number is the sum of:
	 * * the creature�s total Hit Dice
	 * * class levels
	 * * racial level adjustment
	 * @return
	 */
	int getEffectiveCharacterLevel();
	
	/**
	 * gets the base attack bonus for this character.
	 * value should be the sum of the bonuses for all character classes.
	 * @return
	 */
	int getBaseAttackBonus();
	
	/**
	 * gets the gender for the character.
	 * @return
	 */
	Gender getGender();
	
	/**
	 * gets the race of the character.
	 * @return
	 */
	Race getRace();
	
	/**
	 * gets the alignment of the character.
	 * @return
	 */
	Alignment getAlignment();

	/**
	 * gets the hit points for the character.
	 * @return
	 */
	HitPoints getHitPoints();
	
	/**
	 * gets the armor class for the character.
	 * @return
	 */
	ArmorClass getArmorClass();
	
	/**
	 * gets the encumberance the character is under from equipment or armor.
	 * ex: Medium load or Medium armor cause Medium encumberance.
	 * @return
	 */
	Encumberance getEncumberance();
	
	/**
	 * gets the age of the character.
	 * @return
	 */
	Age getAge();
	
	/**
	 * gets the level progression of the character.
	 * @return
	 */
	CharacterProgression getCharacterProgression();

    /**
     * gets the items on the character.
     * @return
     */
    ItemContainer getInventory();
    
    /**
     * gets the current amount of experience the character has.
     * @return
     */
    int getExperience();
}