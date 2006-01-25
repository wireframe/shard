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

import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.kit.CharacterClassDao;
import com.codecrate.shard.race.Race;
import com.codecrate.shard.race.RaceDao;
import com.codecrate.shard.race.RaceFactory;
import com.codecrate.shard.source.Source;

public class PcgenRaceLineHandler extends AbstractPcgenLineHandler {
    private static final String TAG_NAME_FAVORED_CLASS = "FAVCLASS";
    private static final String ANY = "ANY";

    private final RaceFactory raceFactory;
    private final RaceDao raceDao;
    private final CharacterClassDao characterClassDao;

    public PcgenRaceLineHandler(RaceFactory raceFactory, RaceDao raceDao, CharacterClassDao characterClassDao) {
        this.raceFactory = raceFactory;
        this.raceDao = raceDao;
        this.characterClassDao = characterClassDao;
    }

    public Object handleParsedLine(String name, Map tags, Source source) {
        String favoredClassName = getStringTagValue(TAG_NAME_FAVORED_CLASS, tags);
        CharacterClass favoredClass = null;
        if (null != favoredClassName && !ANY.equals(favoredClassName)) {
            favoredClass = characterClassDao.getCharacterClass(favoredClassName);
        }

        Race race = raceFactory.createRace(name, favoredClass, source);
        return raceDao.saveRace(race);
    }
}
