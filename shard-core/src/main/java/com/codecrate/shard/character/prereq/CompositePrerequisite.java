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

import com.codecrate.shard.character.PlayerCharacter;

public class CompositePrerequisite implements CharacterPrerequisite {

	private final CharacterPrerequisite[] prereqs;

	public CompositePrerequisite(CharacterPrerequisite[] prereqs) {
		this.prereqs = prereqs;
	}
	
	public boolean hasMetPrerequisite(PlayerCharacter character) {
		for (int x = 0; x < prereqs.length; x++) {
			CharacterPrerequisite prereq = prereqs[x];
			if (!prereq.hasMetPrerequisite(character)) {
				return false;
			}
		}
		return true;
	}
}
