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
package com.codecrate.shard.character.prereq;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.codecrate.shard.character.PlayerCharacter;
import com.codecrate.shard.race.Race;

public class RacePrerequisite implements CharacterPrerequisite {
	private static final Log LOG = LogFactory.getLog(RacePrerequisite.class);
	
	private final Race[] races;

	public RacePrerequisite(Race race) {
	    this(new Race[]{race});
	}
	
	public RacePrerequisite(Race[] races) {
	    this.races = races;
	}
	
	public boolean hasMetPrerequisite(PlayerCharacter character) {
	    for (int x = 0; x < races.length; x++) {
	        Race race = races[x];
	        if (race.isSame(character.getRace())) {
	            return true;
	        }
	    }
	    
		LOG.warn("Character " + character + " does not meet prereq for acceptable races " + races);
		return false;
	}
}
