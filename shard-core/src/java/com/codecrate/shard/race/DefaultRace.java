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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.codecrate.shard.ability.AbilityModifier;
import com.codecrate.shard.ability.DefaultAbilityContainer;
import com.codecrate.shard.ability.DefaultAbilityModifier;
import com.codecrate.shard.movement.DefaultMovement;
import com.codecrate.shard.movement.Movement;

/**
 * <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultRace implements Race {
	public static final Race HUMAN = new DefaultRace(DefaultRacialSize.MEDIUM,
			new DefaultMovement(30), new ArrayList(), 0);

	public static final Race HALF_ELF = new DefaultRace(
			DefaultRacialSize.MEDIUM, new DefaultMovement(30), new ArrayList(), 0);

	public static final Race HALF_ORC = new DefaultRace(
			DefaultRacialSize.MEDIUM, new DefaultMovement(30), Arrays
					.asList(new AbilityModifier[] {
							new DefaultAbilityModifier(
									DefaultAbilityContainer.STRENGTH, 2),
							new DefaultAbilityModifier(
									DefaultAbilityContainer.INTELLIGENCE, -2),
							new DefaultAbilityModifier(
									DefaultAbilityContainer.CHARISMA, -2) }), 0);

	public static final Race ELF = new DefaultRace(DefaultRacialSize.MEDIUM,
			new DefaultMovement(30), Arrays.asList(new AbilityModifier[] {
					new DefaultAbilityModifier(
							DefaultAbilityContainer.DEXTERITY, 2),
					new DefaultAbilityModifier(
							DefaultAbilityContainer.CONSTITUTION, -2) }), 0);

	public static final Race DWARF = new DefaultRace(DefaultRacialSize.MEDIUM,
			new DefaultMovement(20), Arrays.asList(new AbilityModifier[] {
					new DefaultAbilityModifier(
							DefaultAbilityContainer.CONSTITUTION, 2),
					new DefaultAbilityModifier(
							DefaultAbilityContainer.CHARISMA, -2) }), 0);

	public static final Race GNOME = new DefaultRace(DefaultRacialSize.SMALL,
			new DefaultMovement(20), Arrays.asList(new AbilityModifier[] {
					new DefaultAbilityModifier(
							DefaultAbilityContainer.CONSTITUTION, 2),
					new DefaultAbilityModifier(
							DefaultAbilityContainer.STRENGTH, -2) }), 0);

	public static final Race HALFLING = new DefaultRace(
			DefaultRacialSize.SMALL, new DefaultMovement(20), Arrays
					.asList(new AbilityModifier[] {
							new DefaultAbilityModifier(
									DefaultAbilityContainer.DEXTERITY, 2),
							new DefaultAbilityModifier(
									DefaultAbilityContainer.STRENGTH, -2) }), 0);

	private RacialSize size;
	private Movement movement;
	private Collection abilityModifiers;
	private int levelAdjustment;

	public DefaultRace(RacialSize size, Movement movement, Collection abilityModifiers, int levelAdjustment) {
		this.size = size;
		this.movement = movement;
		this.abilityModifiers = abilityModifiers;
		this.levelAdjustment = levelAdjustment;
	}

	public RacialSize getSize() {
		return size;
	}

	public Movement getMovement() {
		return movement;
	}

	public Collection getAbilityModifiers() {
		return abilityModifiers;
	}
	
	public int getLevelAdjustment() {
		return levelAdjustment;
	}
}