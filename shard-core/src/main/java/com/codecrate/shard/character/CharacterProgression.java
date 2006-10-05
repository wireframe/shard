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
import java.util.Collection;

import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.kit.ClassLevel;

public interface CharacterProgression {

	int getExperience();

	/**
	 * gets the formatted text for this character's progression.
	 * ex: Fighter 7 / Wizard 5
	 * @return
	 */
	String getDescription();

    /**
     * gets all character levels.
     * @return
     */
    Collection getCharacterLevels();

	/**
	 * gets the number of levels the character has.
	 * @return
	 */
	int getCharacterLevel();

    /**
     * gets a specific character level.
     * @param level
     * @return
     */
    CharacterLevel getCharacterLevel(int level);

	/**
	 * gets the different classes that the character has.
	 * @return
	 */
	Collection getClasses();

	/**
	 * gets the max level of a class.
	 * @param kit
	 * @return
	 */
	ClassLevel getClassLevel(CharacterClass kit);

	/**
	 * gets the penalty this character is subjected to.
	 * this takes into account the character's racial favored class.
	 * @return
	 */
	BigDecimal getMulticlassExperiencePenalty();

    void addLevel(CharacterLevel level);

	int getNextCharacterLevel();
}