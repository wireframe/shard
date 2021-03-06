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

import com.codecrate.shard.character.Alignment;
import com.codecrate.shard.character.PlayerCharacter;

public class AlignmentPrerequisite implements CharacterPrerequisite {
	private static final Log LOG = LogFactory.getLog(AlignmentPrerequisite.class);
	
	private final Alignment[] alignments;

	public AlignmentPrerequisite(Alignment alignment) {
	    this(new Alignment[]{alignment});
	}
	
	public AlignmentPrerequisite(Alignment[] alignments) {
	    this.alignments = alignments;
	}
	
	public boolean hasMetPrerequisite(PlayerCharacter character) {
	    for (int x = 0; x < alignments.length; x++) {
	        Alignment alignment = alignments[x];
	        if (alignment.equals(character.getAlignment())) {
	            return true;
	        }
	    }
	    
		LOG.warn("Character " + character + " does not meet prereq for acceptable alignments " + alignments);
		return false;
	}
}
