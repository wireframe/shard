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

import java.util.ArrayList;

import com.codecrate.shard.ShardHibernateTestCaseSupport;
import com.codecrate.shard.dice.RandomDice;
import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.kit.CharacterClassDao;
import com.codecrate.shard.kit.CharacterClassFactory;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateCharacterDaoTest extends ShardHibernateTestCaseSupport {

	private CharacterDao characterDao;
	private CharacterFactory characterFactory;
    private CharacterClassDao characterClassDao;
    private CharacterClassFactory characterClassFactory;

	public void setCharacterClassDao(CharacterClassDao characterClassDao) {
        this.characterClassDao = characterClassDao;
    }

    public void setCharacterClassFactory(CharacterClassFactory characterClassFactory) {
        this.characterClassFactory = characterClassFactory;
    }

    public void setCharacterDao(CharacterDao characterDao) {
		this.characterDao = characterDao;
	}

	public void setCharacterFactory(CharacterFactory factory) {
		this.characterFactory = factory;
	}

    protected void onSetUpInTransaction() throws Exception {
        super.onSetUpInTransaction();

        CharacterClass kit = characterClassFactory.createClass("MegaFighter", "FTR", new RandomDice(4), null);
        kit.getClassProgression().addLevel(2, 3, 4, 5);
        characterClassDao.saveClass(kit);

        PlayerCharacter character = characterFactory.createCharacter("Bob");
        character.getCharacterProgression().addLevel(kit, 1, new ArrayList());
        characterDao.saveCharacter(character);
    }

	public void testSaveNewCharacter() throws Exception {
        PlayerCharacter character = characterFactory.createCharacter("test");
        character = characterDao.saveCharacter(character);
        assertNotNull(character);
    }
}