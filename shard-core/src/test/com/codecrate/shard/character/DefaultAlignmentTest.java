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

    public void testEqualsMatchesLawfulGoodCharacters() {
        DefaultAlignment alignment = new DefaultAlignment(AlignmentComponent.POSITIVE, AlignmentComponent.POSITIVE, "Test Lawful Good", "TLG");
        assertTrue(alignment.isSame(DefaultAlignment.LAWFUL_GOOD));
        assertFalse(alignment.isSame(DefaultAlignment.LAWFUL_NEUTRAL));
        assertFalse(alignment.isSame(DefaultAlignment.LAWFUL_EVIL));
        assertFalse(alignment.isSame(DefaultAlignment.NEUTRAL_GOOD));
        assertFalse(alignment.isSame(DefaultAlignment.NEUTRAL_NEUTRAL));
        assertFalse(alignment.isSame(DefaultAlignment.NEUTRAL_EVIL));
    }

    public void testEqualsMatchesChaoticEvilCharacters() {
        DefaultAlignment alignment = new DefaultAlignment(AlignmentComponent.NEGATIVE, AlignmentComponent.NEGATIVE, "Test Chaotic Evil", "TCE");
        assertTrue(alignment.isSame(DefaultAlignment.CHAOTIC_EVIL));
        assertFalse(alignment.isSame(DefaultAlignment.CHAOTIC_GOOD));
        assertFalse(alignment.isSame(DefaultAlignment.CHAOTIC_NEUTRAL));
    }

    public void testEqualsMatchesNeutralCharacters() {
        DefaultAlignment alignment = new DefaultAlignment(AlignmentComponent.NEUTRAL, AlignmentComponent.NEUTRAL, "Test True Neutral", "TN");
        assertTrue(alignment.isSame(DefaultAlignment.NEUTRAL_NEUTRAL));
        assertFalse(alignment.isSame(DefaultAlignment.NEUTRAL_GOOD));
        assertFalse(alignment.isSame(DefaultAlignment.NEUTRAL_EVIL));
    }
}
