package com.codecrate.shard.character;

import junit.framework.TestCase;

public class DefaultAlignmentTest extends TestCase {

    public void testEqualsMatchesLawfulGoodCharacters() {
        DefaultAlignment alignment = new DefaultAlignment(AlignmentComponent.POSITIVE, AlignmentComponent.POSITIVE);
        assertEquals(DefaultAlignment.LAWFUL_GOOD, alignment);
        assertNotEquals(DefaultAlignment.LAWFUL_NEUTRAL, alignment);
        assertNotEquals(DefaultAlignment.LAWFUL_EVIL, alignment);
        assertTrue(alignment.equals(DefaultAlignment.LAWFUL_GOOD));
        assertFalse(alignment.equals(DefaultAlignment.LAWFUL_NEUTRAL));
        assertFalse(alignment.equals(DefaultAlignment.LAWFUL_EVIL));
    }

    public void testEqualsMatchesChaoticEvilCharacters() {
        DefaultAlignment alignment = new DefaultAlignment(AlignmentComponent.NEGATIVE, AlignmentComponent.NEGATIVE);
        assertTrue(alignment.equals(DefaultAlignment.CHAOTIC_EVIL));
        assertFalse(alignment.equals(DefaultAlignment.CHAOTIC_GOOD));
        assertFalse(alignment.equals(DefaultAlignment.CHAOTIC_NEUTRAL));
    }

    public void testEqualsMatchesNeutralCharacters() {
        DefaultAlignment alignment = new DefaultAlignment(AlignmentComponent.NEUTRAL, AlignmentComponent.NEUTRAL);
        assertTrue(alignment.equals(DefaultAlignment.NEUTRAL_NEUTRAL));
        assertFalse(alignment.equals(DefaultAlignment.NEUTRAL_GOOD));
        assertFalse(alignment.equals(DefaultAlignment.NEUTRAL_EVIL));
    }
}
