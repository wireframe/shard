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
import java.util.Collections;

import org.springframework.orm.hibernate.HibernateTemplate;

import com.codecrate.shard.ability.AbilityScoreContainer;
import com.codecrate.shard.ability.DefaultAbilityScoreContainer;
import com.codecrate.shard.divine.Deity;
import com.codecrate.shard.equipment.DefaultItemEntryContainer;
import com.codecrate.shard.equipment.ItemEntryContainer;
import com.codecrate.shard.race.DefaultRace;
import com.codecrate.shard.race.Race;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernatePlayerCharacterDao extends HibernateTemplate implements PlayerCharacterDao {
    
    public PlayerCharacter createCharacter() {
//        DefaultCharacterBio bio = new DefaultCharacterBio();
//        Age age = new DefaultAge(18, 100, CummulativeAgeCategory.ADULT);
//        Race race = DefaultRace.HUMAN;
//        AbilityScoreContainer abilities = new DefaultAbilityScoreContainer(Collections.EMPTY_MAP);
//        Deity deity = null; //DefaultDeity.NO_DEITY;
//        CharacterProgression characterProgression = new DefaultCharacterProgression(Collections.EMPTY_LIST);
//        BigDecimal challengeRating = new BigDecimal("0");
//        ItemEntryContainer inventory = new DefaultItemEntryContainer(Collections.EMPTY_LIST);
//        Alignment alignment = DefaultAlignment.LAWFUL_GOOD;
//        
//        return new DefaultPlayerCharacter(age, race, abilities, characterProgression, deity, challengeRating, inventory, alignment, bio);
        return null;
    }
}
