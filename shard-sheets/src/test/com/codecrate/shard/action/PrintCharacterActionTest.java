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
import org.apache.velocity.tools.generic.log.CommonsLogLogSystem;
import org.easymock.MockControl;

import com.codecrate.shard.DefaultKeyedModifier;
import com.codecrate.shard.KeyedModifier;
import com.codecrate.shard.ability.AbilityScoreDao;
import com.codecrate.shard.ability.DefaultAbility;
import com.codecrate.shard.ability.DefaultAbilityScore;
import com.codecrate.shard.ability.DefaultAbilityScoreContainer;
import com.codecrate.shard.armorclass.ArmorClass;
import com.codecrate.shard.armorclass.DexterityArmorClass;
import com.codecrate.shard.character.Age;
import com.codecrate.shard.character.DefaultAgeCategoryDao;
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
import com.codecrate.shard.equipment.DefaultItemEntryContainer;
import com.codecrate.shard.equipment.ItemEntry;
import com.codecrate.shard.equipment.ItemEntryContainer;
import com.codecrate.shard.kit.DefaultCharacterClass;
import com.codecrate.shard.movement.DefaultEncumberance;
import com.codecrate.shard.movement.Encumberance;
import com.codecrate.shard.movement.EncumberanceDao;
import com.codecrate.shard.movement.InventoryWeightEncumberance;
import com.codecrate.shard.race.DefaultRace;
import com.codecrate.shard.save.SavingThrowEntryContainer;
import com.codecrate.shard.skill.CharacterProgressionSkillEntryContainer;
import com.codecrate.shard.skill.DefaultSkill;
import com.codecrate.shard.skill.FeatContainer;
import com.codecrate.shard.skill.SkillEntryContainer;

public class PrintCharacterActionTest extends TestCase {

	public void testMerge() throws Exception {
		VelocityEngine engine = new VelocityEngine();
		Properties p = new Properties();
	    p.setProperty("file.resource.loader.path", "/home/rsonnek/Projects/shard/shard-sheets/src/conf/templates/html");
	    p.setProperty("runtime.log.logsystem.class", CommonsLogLogSystem.class.getName());
	    engine.init(p);
		Template template = engine.getTemplate("default.vm");

		MockControl mockDao = MockControl.createControl(AbilityScoreDao.class);
		AbilityScoreDao dao = (AbilityScoreDao) mockDao.getMock();
		dao.getPointCost(10);
		mockDao.setReturnValue(1);
		dao.getPointCost(18);
		mockDao.setReturnValue(14);
		mockDao.replay();
		
		Map scores = new HashMap();
		scores.put(DefaultAbility.STRENGTH, new DefaultAbilityScore(DefaultAbility.STRENGTH, 10, dao));
		scores.put(DefaultAbility.DEXTERITY, new DefaultAbilityScore(DefaultAbility.DEXTERITY, 18, dao));
		DefaultAbilityScoreContainer abilities = new DefaultAbilityScoreContainer(scores);
		
		Collection levels = new ArrayList();
		levels.add(new DefaultCharacterLevel(1, 1,
                DefaultCharacterClass.BARBARIAN.getClassProgression().getClassLevel(1), 
                Arrays.asList(new KeyedModifier[] { 
                                new DefaultKeyedModifier(DefaultSkill.SWIM, DefaultSkill.TYPE_RANK, 1), 
                                new DefaultKeyedModifier(DefaultSkill.INTIMIDATE, DefaultSkill.TYPE_RANK, 1)})));
		levels.add(new DefaultCharacterLevel(2, 1, 
		        DefaultCharacterClass.FIGHTER.getClassProgression().getClassLevel(1), 
                Arrays.asList(new KeyedModifier[] { 
                        new DefaultKeyedModifier(DefaultSkill.SWIM, DefaultSkill.TYPE_RANK, 1) })));
		CharacterProgression progression = new DefaultCharacterProgression(levels);
		
		Age age = new RacialCategorizedAge(20, DefaultRace.HUMAN, new DefaultAgeCategoryDao(), 100);
		
		HitPoints hitPoints = new HitPoints(12, 20, 5);
		
		SavingThrowEntryContainer savingThrows = new SavingThrowEntryContainer();

		ItemEntryContainer itemContainer = new DefaultItemEntryContainer(Arrays.asList(new ItemEntry[] {new ItemEntry(Coin.GOLD_PIECE, 100)}));
		
		SkillEntryContainer skills = new CharacterProgressionSkillEntryContainer(progression);
		
		MockControl mockEncumberanceDao = MockControl.createControl(EncumberanceDao.class);
		EncumberanceDao encumberanceDao = (EncumberanceDao) mockEncumberanceDao.getMock();
		encumberanceDao.getEncumberance(abilities, itemContainer, DefaultRace.HUMAN.getSize());
		mockEncumberanceDao.setReturnValue(DefaultEncumberance.LIGHT);
		mockEncumberanceDao.replay();
		
		Encumberance encumberance = new InventoryWeightEncumberance(abilities, itemContainer, DefaultRace.HUMAN.getSize(), encumberanceDao);
		
		ArmorClass armorClass = new DexterityArmorClass(abilities, encumberance);
		
		Initiative initiative = new Initiative(abilities);
		
		FeatContainer feats = new FeatContainer(new ArrayList());
		DefaultPlayerCharacter character = new DefaultPlayerCharacter("Gunthor the Terrible",
				DefaultRace.HUMAN, DefaultGender.MALE,
				DefaultAlignment.LAWFUL_GOOD, abilities, hitPoints, armorClass,
				encumberance, age, progression, savingThrows, itemContainer, 0, skills, new BigDecimal(20),
				initiative, feats);
		
		PrintCharacterAction output = new PrintCharacterAction(character, template);
		System.out.println(output.render());
	}
}
