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
package com.codecrate.shard.transfer.pcgen;

import java.util.Map;

import com.codecrate.shard.magic.Spell;
import com.codecrate.shard.magic.SpellDao;
import com.codecrate.shard.magic.SpellFactory;
import com.codecrate.shard.source.Source;

public class PcgenSpellLineHandler extends AbstractPcgenLineHandler {
	private static final String DESCRIPTION_TAG_NAME = "DESC";
    private static final String SCHOOL_TAG_NAME = "SCHOOL";
    private static final String SPELL_TYPE_TAG_NAME = "TYPE";
    private static final String ARCANE = "Arcane";
    private static final String DIVINE = "Divine";

    private final SpellFactory spellFactory;
    private final SpellDao spellDao;

    public PcgenSpellLineHandler(SpellFactory spellFactory, SpellDao spellDao) {
        this.spellFactory = spellFactory;
        this.spellDao = spellDao;
    }

    public Object handleParsedLine(String name, Map tags, Source source) {
    	String description = getStringTagValue(DESCRIPTION_TAG_NAME, tags);
    	String school = getStringTagValue(SCHOOL_TAG_NAME, tags);
    	String type = getStringTagValue(SPELL_TYPE_TAG_NAME, tags);
        boolean isArcane = ARCANE.indexOf(type) != -1;
        boolean isDivine = DIVINE.indexOf(type) != -1;

        Spell spell = spellFactory.createSpell(name, description, school, isArcane, isDivine, source);
        return spellDao.saveSpell(spell);
    }
}
