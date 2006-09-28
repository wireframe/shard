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
import java.util.Arrays;
import java.util.Collection;

import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.easymock.MockControl;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import com.codecrate.shard.ability.AbilityScoreContainer;
import com.codecrate.shard.ability.AbilityScoreDao;
import com.codecrate.shard.ability.DefaultAbilityScoreContainer;
import com.codecrate.shard.character.Alignment;
import com.codecrate.shard.character.DefaultAlignment;
import com.codecrate.shard.character.DefaultCharacterLevel;
import com.codecrate.shard.character.DefaultPlayerCharacter;
import com.codecrate.shard.character.prereq.NullPrerequisite;
import com.codecrate.shard.dice.RandomDice;
import com.codecrate.shard.divine.Deity;
import com.codecrate.shard.equipment.Coin;
import com.codecrate.shard.equipment.DefaultItemEntryContainer;
import com.codecrate.shard.equipment.ItemEntry;
import com.codecrate.shard.equipment.ItemEntryContainer;
import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.kit.DefaultCharacterClass;
import com.codecrate.shard.modifier.Modifier;
import com.codecrate.shard.movement.DefaultEncumberance;
import com.codecrate.shard.movement.Encumberance;
import com.codecrate.shard.movement.EncumberanceDao;
import com.codecrate.shard.movement.InventoryWeightEncumberance;
import com.codecrate.shard.race.DefaultRace;
import com.codecrate.shard.race.DefaultVision;
import com.codecrate.shard.race.Race;
import com.codecrate.shard.race.RacialSize;

public class PrintCharacterActionTest extends AbstractDependencyInjectionSpringContextTests {

	private VelocityEngine engine;

	protected final String[] getConfigLocations() {
		return new String[] {"/shard-sheets-context.xml"};
	}

	public void setEngine(VelocityEngine engine) {
		this.engine = engine;
	}

	public void testMerge() throws Exception {
		Template template = engine.getTemplate("default.vm");

		MockControl mockAbilityScoreDao = MockControl.createControl(AbilityScoreDao.class);
		AbilityScoreDao abilityScoreDao = (AbilityScoreDao) mockAbilityScoreDao.getMock();
		abilityScoreDao.getPointCost(10);
		mockAbilityScoreDao.setReturnValue(1);
		abilityScoreDao.getPointCost(10);
		mockAbilityScoreDao.setReturnValue(1);
		abilityScoreDao.getPointCost(10);
		mockAbilityScoreDao.setReturnValue(1);
		abilityScoreDao.getPointCost(10);
		mockAbilityScoreDao.setReturnValue(1);
		abilityScoreDao.getPointCost(10);
		mockAbilityScoreDao.setReturnValue(1);
		abilityScoreDao.getPointCost(10);
		mockAbilityScoreDao.setReturnValue(1);
		mockAbilityScoreDao.replay();
		AbilityScoreContainer abilities = DefaultAbilityScoreContainer.averageScores(abilityScoreDao);

		ItemEntryContainer itemContainer = new DefaultItemEntryContainer(Arrays.asList(new ItemEntry[] {new ItemEntry(Coin.GOLD_PIECE, 100)}));

		MockControl mockEncumberanceDao = MockControl.createControl(EncumberanceDao.class);
		EncumberanceDao encumberanceDao = (EncumberanceDao) mockEncumberanceDao.getMock();
		encumberanceDao.getEncumberance(abilities, itemContainer, null);
		mockEncumberanceDao.setReturnValue(DefaultEncumberance.LIGHT);
		mockEncumberanceDao.replay();

		Encumberance encumberance = new InventoryWeightEncumberance(abilities, itemContainer, null, encumberanceDao);

		MockControl mockDeity = MockControl.createControl(Deity.class);
		Deity deity = (Deity) mockDeity.getMock();
		deity.getName();
		mockDeity.setReturnValue("Bob the Almighty");
		mockDeity.replay();

        Alignment alignment = DefaultAlignment.LAWFUL_GOOD;

        RacialSize medium = new RacialSize() {

            public int getBaseAttackBonusModifier() {
                // TODO Auto-generated method stub
                return 0;
            }

            public Modifier getArmorClassModifier() {
                // TODO Auto-generated method stub
                return null;
            }

            public String getName() {
                return "medium";
            }

            public BigDecimal getSpace() {
                // TODO Auto-generated method stub
                return null;
            }

            public int getReach() {
                // TODO Auto-generated method stub
                return 0;
            }

            public Collection getSkillModifiers() {
                // TODO Auto-generated method stub
                return null;
            }

            public BigDecimal getEncumberanceMultiplier() {
                // TODO Auto-generated method stub
                return null;
            }};

        Race human = new DefaultRace("human", medium, null, 1, DefaultVision.NORMAL, null, null, 2, null);

        CharacterClass fighter = new DefaultCharacterClass("fighter", "ftr", new RandomDice(8), 4, new NullPrerequisite(), null);
        fighter.getClassProgression().addLevel(1, 2, 3, 4);

		DefaultPlayerCharacter character = new DefaultPlayerCharacter("Gunthor the Terrible", abilities, human, itemContainer, encumberance, alignment, deity);
		character.getCharacterProgression().addLevel(new DefaultCharacterLevel(character, fighter, 1));

        character.getBaseAttackBonus();

		PrintCharacterAction output = new PrintCharacterAction(character, template);
		String text = output.render().toString();
        int index = text.indexOf("$");
		assertEquals("Not all velocity macros were expanded:  " + text, -1, index);
	}
}
