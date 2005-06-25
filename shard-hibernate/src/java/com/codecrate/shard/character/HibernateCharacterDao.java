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

import java.util.Collections;

import org.springframework.orm.hibernate.support.HibernateDaoSupport;

import com.codecrate.shard.ability.AbilityScoreContainer;
import com.codecrate.shard.ability.AbilityScoreDao;
import com.codecrate.shard.ability.DefaultAbilityScoreContainer;
import com.codecrate.shard.divine.Deity;
import com.codecrate.shard.equipment.DefaultItemEntryContainer;
import com.codecrate.shard.equipment.ItemEntryContainer;
import com.codecrate.shard.movement.DefaultEncumberance;
import com.codecrate.shard.movement.Encumberance;
import com.codecrate.shard.race.DefaultRace;
import com.codecrate.shard.race.Race;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateCharacterDao extends HibernateDaoSupport implements CharacterDao, CharacterFactory {
	
	private AbilityScoreDao abilityScoreDao;
	
	public PlayerCharacter createCharacter(String name) {
		DefaultCharacterBio bio = new DefaultCharacterBio(name);
		Age age = new DefaultAge(18, 100, CummulativeAgeCategory.ADULT);
		Race race = DefaultRace.HUMAN;
		AbilityScoreContainer abilities = DefaultAbilityScoreContainer.averageScores(abilityScoreDao);
		CharacterProgression characterProgression = new DefaultCharacterProgression(Collections.EMPTY_LIST);
		ItemEntryContainer inventory = new DefaultItemEntryContainer(Collections.EMPTY_LIST);
		Alignment alignment = DefaultAlignment.LAWFUL_GOOD;
		Encumberance encumberance = DefaultEncumberance.LIGHT;
		Deity deity = null; 
		
		return new DefaultPlayerCharacter(abilities, race, characterProgression, inventory, encumberance, alignment, age, bio, deity);
	}
	
	public PlayerCharacter saveCharacter(PlayerCharacter character) {
        String id = (String) getHibernateTemplate().save(character);
        return (PlayerCharacter) getHibernateTemplate().load(DefaultPlayerCharacter.class, id);
	}

	public void setAbilityScoreDao(AbilityScoreDao abilityScoreDao) {
		this.abilityScoreDao = abilityScoreDao;
	}
}
