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
package com.codecrate.shard.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import junit.framework.TestCase;

import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;

import com.codecrate.shard.ability.DefaultAbility;
import com.codecrate.shard.ability.DefaultAbilityScore;
import com.codecrate.shard.ability.DefaultAbilityScoreContainer;
import com.codecrate.shard.armorclass.ArmorClass;
import com.codecrate.shard.armorclass.DefaultArmorClass;
import com.codecrate.shard.armorclass.DexterityArmorClass;
import com.codecrate.shard.character.Age;
import com.codecrate.shard.character.AgeCategoryDao;
import com.codecrate.shard.character.CharacterProgression;
import com.codecrate.shard.character.DefaultAlignment;
import com.codecrate.shard.character.DefaultCharacterLevel;
import com.codecrate.shard.character.DefaultCharacterProgression;
import com.codecrate.shard.character.DefaultGender;
import com.codecrate.shard.character.DefaultPlayerCharacter;
import com.codecrate.shard.character.HitPoints;
import com.codecrate.shard.character.Initiative;
import com.codecrate.shard.character.RacialCategorizedAge;
import com.codecrate.shard.equipment.Coin;
import com.codecrate.shard.equipment.ItemContainer;
import com.codecrate.shard.equipment.ItemEntry;
import com.codecrate.shard.equipment.MaxWeightItemContainer;
import com.codecrate.shard.kit.DefaultCharacterClass;
import com.codecrate.shard.movement.DefaultEncumberance;
import com.codecrate.shard.movement.Encumberance;
import com.codecrate.shard.movement.EncumberanceDao;
import com.codecrate.shard.movement.InventoryWeightEncumberance;
import com.codecrate.shard.race.DefaultRace;
import com.codecrate.shard.save.SavingThrowContainer;
import com.codecrate.shard.skill.CharacterProgressionSkillEntryContainer;
import com.codecrate.shard.skill.DefaultSkill;
import com.codecrate.shard.skill.DefaultSkillModifier;
import com.codecrate.shard.skill.DefaultSkillModifierType;
import com.codecrate.shard.skill.SkillEntryContainer;
import com.codecrate.shard.skill.SkillModifier;

public class PrintCharacterActionTest extends TestCase {

	public void testMerge() throws Exception {
		VelocityEngine engine = new VelocityEngine();
		Properties p = new Properties();
	    p.setProperty("file.resource.loader.path", "/home/rsonnek/Projects/shard/shard-sheets/src/conf/templates/html");
	    p.setProperty("runtime.log.logsystem.class", "org.apache.velocity.tools.generic.log.CommonsLogLogSystem");
	    engine.init(p);
		Template template = engine.getTemplate("default.vm");

		Map scores = new HashMap();
		scores.put(DefaultAbility.STRENGTH, new DefaultAbilityScore(DefaultAbility.STRENGTH, 10));
		scores.put(DefaultAbility.DEXTERITY, new DefaultAbilityScore(DefaultAbility.DEXTERITY, 18));
		DefaultAbilityScoreContainer abilities = new DefaultAbilityScoreContainer(scores);
		
		Collection levels = new ArrayList();
		levels.add(new DefaultCharacterLevel(1, 1,
                DefaultCharacterClass.BARBARIAN.getClassProgression().getClassLevel(1), 
                Arrays.asList(new SkillModifier[] { 
                                new DefaultSkillModifier(DefaultSkillModifierType.RANK, 1, DefaultSkill.SWIM), 
                                new DefaultSkillModifier(DefaultSkillModifierType.RANK, 1, DefaultSkill.INTIMIDATE)})));
		levels.add(new DefaultCharacterLevel(2, 1, 
		        DefaultCharacterClass.FIGHTER.getClassProgression().getClassLevel(1), 
                Arrays.asList(new SkillModifier[] { 
                        new DefaultSkillModifier(DefaultSkillModifierType.RANK, 1, DefaultSkill.SWIM) })));
		CharacterProgression progression = new DefaultCharacterProgression(levels);
		
		Age age = new RacialCategorizedAge(20, DefaultRace.HUMAN, new AgeCategoryDao(), 100);
		
		HitPoints hitPoints = new HitPoints();
		
		ArmorClass armorClass = new DexterityArmorClass(abilities, DefaultEncumberance.LIGHT, new DefaultArmorClass());
		
		SavingThrowContainer savingThrows = new SavingThrowContainer();

		ItemContainer itemContainer = new MaxWeightItemContainer(9999);
		itemContainer.add(new ItemEntry(Coin.GOLD_PIECE, 100));
		
		SkillEntryContainer skills = new CharacterProgressionSkillEntryContainer(progression);
		
		Encumberance encumberance = new InventoryWeightEncumberance(abilities, itemContainer, DefaultRace.HUMAN.getSize(), new EncumberanceDao());
		
		Initiative initiative = new Initiative();
		DefaultPlayerCharacter character = new DefaultPlayerCharacter("Gunthor the Terrible",
				DefaultRace.HUMAN, DefaultGender.MALE,
				DefaultAlignment.LAWFUL_GOOD, abilities, hitPoints, armorClass,
				encumberance, age, progression, savingThrows, itemContainer, 0, skills, new BigDecimal(20),
				initiative);
		
		PrintCharacterAction output = new PrintCharacterAction(character, template);
		System.out.println(output.render());
	}
}
