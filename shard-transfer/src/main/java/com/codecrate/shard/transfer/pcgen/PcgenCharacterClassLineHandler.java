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

import com.codecrate.shard.dice.Dice;
import com.codecrate.shard.dice.RandomDice;
import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.kit.CharacterClassDao;
import com.codecrate.shard.kit.CharacterClassFactory;
import com.codecrate.shard.source.Source;
import com.codecrate.shard.transfer.pcgen.tag.PcgenDefaultTagParser;

public class PcgenCharacterClassLineHandler extends AbstractPcgenLineHandler {
    private static final String NAME = "CLASS";
    private static final String HIT_DICE = "HD";
	private static final String ABBREVIATION = "ABB";

	private final CharacterClassFactory kitFactory;
    private final CharacterClassDao kitDao;

    public PcgenCharacterClassLineHandler(CharacterClassFactory kitFactory, CharacterClassDao kitDao) {
        this.kitFactory = kitFactory;
        this.kitDao = kitDao;
    }

    public Object handleLine(String line, Source source) {
    	Map tags = new PcgenDefaultTagParser().parseTags(line);
    	String name = getStringTagValue(NAME, tags);

    	return handleParsedLine(name, tags, source);
    }

    public Object handleParsedLine(String name, Map tags, Source source) {
    	Dice hitDice = new RandomDice(getIntTagValue(HIT_DICE, tags));
    	String abbreviation = getStringTagValue(ABBREVIATION, tags);

        CharacterClass kit = kitFactory.createClass(name, abbreviation, hitDice);
        return kitDao.saveClass(kit);
    }
}
