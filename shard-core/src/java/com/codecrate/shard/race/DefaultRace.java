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

import com.codecrate.shard.ability.DefaultAbility;
import com.codecrate.shard.dice.RandomDice;
import com.codecrate.shard.dice.Dice;
import com.codecrate.shard.dice.MultipleDice;
import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.kit.DefaultCharacterClass;
import com.codecrate.shard.modifier.DefaultKeyedModifier;
import com.codecrate.shard.modifier.DefaultModifierType;
import com.codecrate.shard.modifier.KeyedModifier;
import com.codecrate.shard.modifier.ModifierType;
import com.codecrate.shard.movement.DefaultMovement;
import com.codecrate.shard.movement.Movement;
import com.codecrate.shard.skill.DefaultSkill;

/**
 * <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultRace implements Race {
    private static final ModifierType RACE = new DefaultModifierType("race", false);
    
	public static final Race HUMAN = new DefaultRace("Human", DefaultRacialSize.MEDIUM,
			new DefaultMovement(30), new ArrayList(), new ArrayList(), 
			0, 
			Arrays.asList(new DefaultLanguage[] {DefaultLanguage.COMMON}), new ArrayList(),
			DefaultVision.NORMAL, null, new MultipleDice(RandomDice.d20, 2), 1);

	public static final Race HALF_ELF = new DefaultRace("Half-Elf",
			DefaultRacialSize.MEDIUM, new DefaultMovement(30), new ArrayList(), 
			Arrays.asList(
					new KeyedModifier[] { 
					        new DefaultKeyedModifier(DefaultSkill.LISTEN, DefaultSkill.TYPE_RACE, 2),
					        new DefaultKeyedModifier(DefaultSkill.SPOT, DefaultSkill.TYPE_RACE, 1),
					        new DefaultKeyedModifier(DefaultSkill.SEARCH, DefaultSkill.TYPE_RACE, 1)}), 
					0, 
			Arrays.asList(new DefaultLanguage[] {DefaultLanguage.COMMON, DefaultLanguage.ELVEN}), new ArrayList(),
			DefaultVision.LOW_LIGHT_VISION, null, new MultipleDice(RandomDice.d20, 3), 0);

	public static final Race HALF_ORC = new DefaultRace("Half-Orc",
			DefaultRacialSize.MEDIUM, new DefaultMovement(30), Arrays
					.asList(new KeyedModifier[] {
							new DefaultKeyedModifier(DefaultAbility.STRENGTH, RACE, 2),
							new DefaultKeyedModifier(DefaultAbility.INTELLIGENCE, RACE, -2),
							new DefaultKeyedModifier(DefaultAbility.CHARISMA, RACE, -2) }), 
									new ArrayList(), 0, 
			Arrays.asList(new DefaultLanguage[] {DefaultLanguage.COMMON, DefaultLanguage.ORC}), 
			Arrays.asList(new DefaultLanguage[] {DefaultLanguage.DRACONIC, DefaultLanguage.GIANT, DefaultLanguage.GNOLL, 
					DefaultLanguage.GOBLIN, DefaultLanguage.ABYSSAL}),
					DefaultVision.DARKVISION, DefaultCharacterClass.BARBARIAN, new MultipleDice(RandomDice.d10, 2), 0);

	public static final Race ELF = new DefaultRace("Elf", DefaultRacialSize.MEDIUM,
			new DefaultMovement(30), Arrays.asList(new KeyedModifier[] {
					new DefaultKeyedModifier(DefaultAbility.DEXTERITY, RACE, 2),
					new DefaultKeyedModifier(DefaultAbility.CONSTITUTION, RACE, -2) }), 
					Arrays.asList(
							new DefaultKeyedModifier[] { 
						        new DefaultKeyedModifier(DefaultSkill.LISTEN, DefaultSkill.TYPE_RACE, 2),
								new DefaultKeyedModifier(DefaultSkill.SPOT, DefaultSkill.TYPE_RACE, 2),
								new DefaultKeyedModifier(DefaultSkill.SEARCH, DefaultSkill.TYPE_RACE, 2)}),
							0, 
							Arrays.asList(new DefaultLanguage[] {DefaultLanguage.COMMON, DefaultLanguage.ELVEN}), 
							Arrays.asList(new DefaultLanguage[] {DefaultLanguage.DRACONIC, DefaultLanguage.GNOLL, 
									DefaultLanguage.GNOME, DefaultLanguage.GOBLIN, DefaultLanguage.ORC, DefaultLanguage.SYLVAN}),
									DefaultVision.LOW_LIGHT_VISION, DefaultCharacterClass.WIZARD, new MultipleDice(RandomDice.d100, 4), 0);

	public static final Race DWARF = new DefaultRace("Dwarf", DefaultRacialSize.MEDIUM,
			new DefaultMovement(20), Arrays.asList(new KeyedModifier[] {
					new DefaultKeyedModifier(DefaultAbility.CONSTITUTION, RACE, 2),
					new DefaultKeyedModifier(DefaultAbility.CHARISMA, RACE, -2) }), 
							new ArrayList(), 0, 
							Arrays.asList(new DefaultLanguage[] {DefaultLanguage.COMMON, DefaultLanguage.DWARVEN}), 
							Arrays.asList(new DefaultLanguage[] {DefaultLanguage.GIANT, DefaultLanguage.GNOME, 
									DefaultLanguage.GOBLIN, DefaultLanguage.ORC, DefaultLanguage.TERRAN, DefaultLanguage.UNDERCOMMON}),
									DefaultVision.DARKVISION, DefaultCharacterClass.FIGHTER, new MultipleDice(RandomDice.d100, 2), 0);

	public static final Race GNOME = new DefaultRace("Gnome", DefaultRacialSize.SMALL,
			new DefaultMovement(20), Arrays.asList(new KeyedModifier[] {
					new DefaultKeyedModifier(DefaultAbility.CONSTITUTION, RACE, 2),
					new DefaultKeyedModifier(DefaultAbility.STRENGTH, RACE,-2) }), 
					Arrays.asList(new DefaultKeyedModifier[] {
						new DefaultKeyedModifier(DefaultSkill.LISTEN, DefaultSkill.TYPE_RACE, 2)	
					}),
							0, 
							Arrays.asList(new DefaultLanguage[] {DefaultLanguage.COMMON, DefaultLanguage.GNOME}), 
							Arrays.asList(new DefaultLanguage[] {DefaultLanguage.DRACONIC, DefaultLanguage.DWARVEN, 
									DefaultLanguage.ELVEN, DefaultLanguage.GIANT, DefaultLanguage.GOBLIN, DefaultLanguage.ORC}),
									DefaultVision.LOW_LIGHT_VISION, DefaultCharacterClass.BARD, new MultipleDice(RandomDice.d100, 3), 0);
	
	public static final Race HALFLING = new DefaultRace("Halfling", 
			DefaultRacialSize.SMALL, new DefaultMovement(20), Arrays
					.asList(new KeyedModifier[] {
							new DefaultKeyedModifier(DefaultAbility.DEXTERITY, RACE, 2),
							new DefaultKeyedModifier(DefaultAbility.STRENGTH, RACE, -2) }), 
									Arrays.asList(new DefaultKeyedModifier[] {
										new DefaultKeyedModifier(DefaultSkill.LISTEN, DefaultSkill.TYPE_RACE, 2),
										new DefaultKeyedModifier(DefaultSkill.CLIMB, DefaultSkill.TYPE_RACE, 2),
										new DefaultKeyedModifier(DefaultSkill.JUMP, DefaultSkill.TYPE_RACE, 2),
										new DefaultKeyedModifier(DefaultSkill.MOVE_SILENTLY, DefaultSkill.TYPE_RACE, 2)
									}), 0,
									Arrays.asList(new DefaultLanguage[] {DefaultLanguage.COMMON, DefaultLanguage.HALFLING}), 
									Arrays.asList(new DefaultLanguage[] {DefaultLanguage.DWARVEN, DefaultLanguage.ELVEN, 
											DefaultLanguage.GNOME, DefaultLanguage.GOBLIN, DefaultLanguage.ORC}),
											DefaultVision.NORMAL, DefaultCharacterClass.ROUGE, new MultipleDice(RandomDice.d20, 5), 0);
	
	private String name;
	private RacialSize size;
	private Movement movement;
	private Collection abilityModifiers;
	private int levelAdjustment;
	private Collection bonusLanguages;
	private Collection automaticLanguages;
	private Vision vision;
	private Collection skillModifiers;
	private CharacterClass favoredClass;
    private Dice maxAgeDice;
    private int baseSkillPointsPerLevel;
	
	public DefaultRace(String name, RacialSize size, Movement movement, 
			Collection abilityModifiers, Collection skillModifiers, 
			int levelAdjustment, 
			Collection grantedLanguages, Collection availableLanguages,
			Vision vision, CharacterClass favoredClass, Dice maxAgeDice, 
			int baseSkillPointsPerLevel) {
		this.name = name;
		this.size = size;
		this.movement = movement;
		this.abilityModifiers = abilityModifiers;
		this.skillModifiers = skillModifiers;
		this.levelAdjustment = levelAdjustment;
		this.automaticLanguages = grantedLanguages;
		this.bonusLanguages = availableLanguages;
		this.vision = vision;
		this.favoredClass = favoredClass;
        this.maxAgeDice = maxAgeDice;
        this.baseSkillPointsPerLevel = baseSkillPointsPerLevel;
	}

	public String toString() {
	    return name;
	}
	
	public boolean isSame(Race race) {
	    return equals(race);
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
	
	public Collection getAutomaticLanguages() {
		return automaticLanguages;
	}
	
	public Collection getBonusLanguages() {
		return bonusLanguages;
	}
	
	public Vision getVision() {
		return vision;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
	    this.name = name;
	}
	
	public Collection getSkillModifiers() {
		return skillModifiers;
	}
	
	public CharacterClass getFavoredClass() {
		return favoredClass;
	}
	
	public Dice getMaxAgeDice() {
	    return maxAgeDice;
	}
	
	public int getBaseSkillPointsPerLevel() {
	    return baseSkillPointsPerLevel;
	}
}