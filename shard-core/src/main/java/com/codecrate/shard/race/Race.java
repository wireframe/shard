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
package com.codecrate.shard.race;

import java.util.Collection;

import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.movement.Movement;
import com.codecrate.shard.source.Source;

/**
 * Defines a Race (ex: Human, Elf).
 *
 * <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface Race {
	/**
	 * gets the name of the race.
	 * @return
	 */
	String getName();

	/**
	 * gets the default racial size.
	 * @return
	 */
    RacialSize getSize();

    /**
     * gets the ability modifiers for this race.
     * ex: elf has +2 Dex, -2 Con.
     * @return
     */
    Collection getAbilityModifiers();

    /**
     * gets the default movement rate for the race.
     * @return
     */
    Movement getMovement();

    /**
     * used for calculating the effective character level.
     * ex: Minotaur has level adjustment of 2, so a new player character
     * starts out effectively as level 2.
     * @return
     */
    int getLevelAdjustment();

    /**
     * gets the languages automatically granted to each race.
     * @return
     */
    Collection getAutomaticLanguages();

    /**
     * gets the additional languages available to the race.
     * character's intelligence modifier can be used to buy
     * each language.
     * @return
     */
    Collection getBonusLanguages();

	/**
	 * gets the vision for the race.
	 * @return
	 */
	Vision getVision();

	/**
	 * gets the skill modifiers for the race.
	 * ex: Elves have +2 bonus to listen.
	 * @return
	 */
	Collection getSkillModifiers();

	/**
	 * gets the favored class for this race.
	 * the favored class is used to determine whether or not an EXP penaly
	 * applies to multiclassed characters.
	 * @return the favored class or null if no favored class.
	 */
	CharacterClass getFavoredClass();

    /**
     * get information relating to how this race ages.
     * @return
     */
    AgeCategorization getAgeCategorization();

    /**
     * gets the number of extra skill points grated per level.
     * ex: humans get +4 skill points at level 1 and +1 each other level.
     */
    int getBaseSkillPointsPerLevel();

    Source getSource();
}
