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

import junit.framework.TestCase;

public class DefaultAlignmentTest extends TestCase {

	public void testIsSameOnlyWorksOnSameAlignment() {
		Alignment[] alignments = new Alignment[] {
				DefaultAlignment.LAWFUL_GOOD,
				DefaultAlignment.LAWFUL_NEUTRAL,
				DefaultAlignment.LAWFUL_EVIL,
				DefaultAlignment.NEUTRAL_GOOD,
				DefaultAlignment.NEUTRAL_NEUTRAL,
				DefaultAlignment.NEUTRAL_EVIL,
				DefaultAlignment.CHAOTIC_GOOD,
				DefaultAlignment.CHAOTIC_NEUTRAL,
				DefaultAlignment.CHAOTIC_EVIL
		};
		
		for (int x = 0; x < alignments.length; x++) {
			Alignment currentAlignment = alignments[x];
			for (int y = 0; y < alignments.length; y++) {
				Alignment testAlignment = alignments[y];
				if (x == y) {
					assertTrue(currentAlignment + " == " + testAlignment, currentAlignment.isSame(testAlignment));
				} else {
					assertFalse(currentAlignment + " != " + testAlignment, currentAlignment.isSame(testAlignment));
				}
			}
		}
	}
}
