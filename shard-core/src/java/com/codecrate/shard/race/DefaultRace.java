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

import com.codecrate.shard.ability.AbilityScoreModifier;
import com.codecrate.shard.ability.DefaultAbility;
import com.codecrate.shard.ability.DefaultAbilityScoreModifier;
import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.kit.DefaultCharacterClass;
import com.codecrate.shard.movement.DefaultMovement;
import com.codecrate.shard.movement.Movement;
import com.codecrate.shard.skill.DefaultSkill;
import com.codecrate.shard.skill.DefaultSkillModifier;
import com.codecrate.shard.skill.SkillModifier;

/**
 * <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultRace implements Race {
	public static final Race HUMAN = new DefaultRace("Human", DefaultRacialSize.MEDIUM,
			new DefaultMovement(30), new ArrayList(), new ArrayList(), 
			0, 
			Arrays.asList(new Language[] {Language.COMMON}), new LanguageDao().getLanguages(),
			DefaultVision.NORMAL, null);

	public static final Race HALF_ELF = new DefaultRace("Half-Elf",
			DefaultRacialSize.MEDIUM, new DefaultMovement(30), new ArrayList(), 
			Arrays.asList(
					new SkillModifier[] { new DefaultSkillModifier(
					DefaultSkillModifier.TYPE_RACE, 2, DefaultSkill.LISTEN),
					new DefaultSkillModifier(DefaultSkillModifier.TYPE_RACE, 1, DefaultSkill.SPOT),
					new DefaultSkillModifier(DefaultSkillModifier.TYPE_RACE, 1, DefaultSkill.SEARCH)}), 
					0, 
			Arrays.asList(new Language[] {Language.COMMON, Language.ELVEN}), new LanguageDao().getLanguages(),
			DefaultVision.LOW_LIGHT_VISION, null);

	public static final Race HALF_ORC = new DefaultRace("Half-Orc",
			DefaultRacialSize.MEDIUM, new DefaultMovement(30), Arrays
					.asList(new AbilityScoreModifier[] {
							new DefaultAbilityScoreModifier(
									DefaultAbility.STRENGTH, 2),
							new DefaultAbilityScoreModifier(
									DefaultAbility.INTELLIGENCE, -2),
							new DefaultAbilityScoreModifier(
									DefaultAbility.CHARISMA, -2) }), 
									new ArrayList(), 0, 
			Arrays.asList(new Language[] {Language.COMMON, Language.ORC}), 
			Arrays.asList(new Language[] {Language.DRACONIC, Language.GIANT, Language.GNOLL, 
					Language.GOBLIN, Language.ABYSSAL}),
					DefaultVision.DARKVISION, DefaultCharacterClass.BARBARIAN);

	public static final Race ELF = new DefaultRace("Elf", DefaultRacialSize.MEDIUM,
			new DefaultMovement(30), Arrays.asList(new AbilityScoreModifier[] {
					new DefaultAbilityScoreModifier(
							DefaultAbility.DEXTERITY, 2),
					new DefaultAbilityScoreModifier(
							DefaultAbility.CONSTITUTION, -2) }), 
					Arrays.asList(
							new SkillModifier[] { new DefaultSkillModifier(
							DefaultSkillModifier.TYPE_RACE, 2, DefaultSkill.LISTEN),
							new DefaultSkillModifier(DefaultSkillModifier.TYPE_RACE, 2, DefaultSkill.SPOT),
							new DefaultSkillModifier(DefaultSkillModifier.TYPE_RACE, 2, DefaultSkill.SEARCH)}),
							0, 
							Arrays.asList(new Language[] {Language.COMMON, Language.ELVEN}), 
							Arrays.asList(new Language[] {Language.DRACONIC, Language.GNOLL, 
									Language.GNOME, Language.GOBLIN, Language.ORC, Language.SYLVAN}),
									DefaultVision.LOW_LIGHT_VISION, DefaultCharacterClass.WIZARD);

	public static final Race DWARF = new DefaultRace("Dwarf", DefaultRacialSize.MEDIUM,
			new DefaultMovement(20), Arrays.asList(new AbilityScoreModifier[] {
					new DefaultAbilityScoreModifier(
							DefaultAbility.CONSTITUTION, 2),
					new DefaultAbilityScoreModifier(
							DefaultAbility.CHARISMA, -2) }), 
							new ArrayList(), 0, 
							Arrays.asList(new Language[] {Language.COMMON, Language.DWARVEN}), 
							Arrays.asList(new Language[] {Language.GIANT, Language.GNOME, 
									Language.GOBLIN, Language.ORC, Language.TERRAN, Language.UNDERCOMMON}),
									DefaultVision.DARKVISION, DefaultCharacterClass.FIGHTER);

	public static final Race GNOME = new DefaultRace("Gnome", DefaultRacialSize.SMALL,
			new DefaultMovement(20), Arrays.asList(new AbilityScoreModifier[] {
					new DefaultAbilityScoreModifier(
							DefaultAbility.CONSTITUTION, 2),
					new DefaultAbilityScoreModifier(
							DefaultAbility.STRENGTH, -2) }), 
					Arrays.asList(new SkillModifier[] {
						new DefaultSkillModifier(DefaultSkillModifier.TYPE_RACE, 2, DefaultSkill.LISTEN)	
					}),
							0, 
							Arrays.asList(new Language[] {Language.COMMON, Language.GNOME}), 
							Arrays.asList(new Language[] {Language.DRACONIC, Language.DWARVEN, 
									Language.ELVEN, Language.GIANT, Language.GOBLIN, Language.ORC}),
									DefaultVision.LOW_LIGHT_VISION, DefaultCharacterClass.BARD);
	
	public static final Race HALFLING = new DefaultRace("Halfling", 
			DefaultRacialSize.SMALL, new DefaultMovement(20), Arrays
					.asList(new AbilityScoreModifier[] {
							new DefaultAbilityScoreModifier(
									DefaultAbility.DEXTERITY, 2),
							new DefaultAbilityScoreModifier(
									DefaultAbility.STRENGTH, -2) }), 
									Arrays.asList(new SkillModifier[] {
										new DefaultSkillModifier(DefaultSkillModifier.TYPE_RACE, 2, DefaultSkill.LISTEN),
										new DefaultSkillModifier(DefaultSkillModifier.TYPE_RACE, 2, DefaultSkill.CLIMB),
										new DefaultSkillModifier(DefaultSkillModifier.TYPE_RACE, 2, DefaultSkill.JUMP),
										new DefaultSkillModifier(DefaultSkillModifier.TYPE_RACE, 2, DefaultSkill.MOVE_SILENTLY)
									}), 0,
									Arrays.asList(new Language[] {Language.COMMON, Language.HALFLING}), 
									Arrays.asList(new Language[] {Language.DWARVEN, Language.ELVEN, 
											Language.GNOME, Language.GOBLIN, Language.ORC}),
											DefaultVision.NORMAL, DefaultCharacterClass.ROUGE);
	
	private final String name;
	private final RacialSize size;
	private final Movement movement;
	private final Collection abilityModifiers;
	private final int levelAdjustment;
	private final Collection bonusLanguages;
	private final Collection automaticLanguages;
	private final Vision vision;

	private final Collection skillModifiers;

	private final CharacterClass favoredClass;
	
	public DefaultRace(String name, RacialSize size, Movement movement, 
			Collection abilityModifiers, Collection skillModifiers, 
			int levelAdjustment, 
			Collection grantedLanguages, Collection availableLanguages,
			Vision vision, CharacterClass favoredClass) {
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
	
	public Collection getSkillModifiers() {
		return skillModifiers;
	}
	
	public CharacterClass getFavoredClass() {
		return favoredClass;
	}
}