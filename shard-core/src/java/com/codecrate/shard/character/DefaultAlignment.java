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

/**
 * Default alignment uses AlignmentComponents.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultAlignment implements Alignment {
    public static final Alignment LAWFUL_GOOD = new DefaultAlignment(AlignmentComponent.POSITIVE, 
        AlignmentComponent.POSITIVE, "Lawful Good", "LG");
    public static final Alignment LAWFUL_NEUTRAL = new DefaultAlignment(AlignmentComponent.POSITIVE,
        AlignmentComponent.NEUTRAL, "Lawful Neutral", "LN");
    public static final Alignment LAWFUL_EVIL = new DefaultAlignment(AlignmentComponent.POSITIVE,
        AlignmentComponent.NEGATIVE, "Lawful Evil", "LE");
    public static final Alignment NEUTRAL_GOOD = new DefaultAlignment(AlignmentComponent.NEUTRAL, 
        AlignmentComponent.POSITIVE, "Neutral Good", "NG");
    public static final Alignment NEUTRAL_NEUTRAL = new DefaultAlignment(AlignmentComponent.NEUTRAL,
        AlignmentComponent.NEUTRAL, "True Neutral", "N");
    public static final Alignment NEUTRAL_EVIL = new DefaultAlignment(AlignmentComponent.NEUTRAL,
        AlignmentComponent.NEGATIVE, "Neutral Evil", "NE");
    public static final Alignment CHAOTIC_GOOD = new DefaultAlignment(AlignmentComponent.NEGATIVE,
        AlignmentComponent.POSITIVE, "Chaotic Good", "CG");
    public static final Alignment CHAOTIC_NEUTRAL = new DefaultAlignment(AlignmentComponent.NEGATIVE,
        AlignmentComponent.NEUTRAL, "Chaotic Neutral", "CN");
    public static final Alignment CHAOTIC_EVIL = new DefaultAlignment(AlignmentComponent.NEGATIVE,
        AlignmentComponent.NEGATIVE, "Chaotic Evil", "CE");


    private String name;
    private String abbreviation;
    private AlignmentComponent ethicalAlignment;
    private AlignmentComponent moralAlignment;

    public DefaultAlignment(AlignmentComponent ethicalAlignment, AlignmentComponent moralAlignment, String name, String abbreviation) {
        this.ethicalAlignment = ethicalAlignment;
        this.moralAlignment = moralAlignment;
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public String toString() {
    	return name;
    }
    
    public boolean isSame(Alignment alignment) {
        if (isLawful() && !alignment.isLawful()) {
            return false;
        }
        if (isChaotic() && !alignment.isChaotic()) {
            return false;
        }
        if (isGood() && !alignment.isGood()) {
            return false;
        }
        if (isEvil() && !alignment.isEvil()) {
            return false;
        }
        if (isNeutral() && !alignment.isNeutral()) {
            return false;
        }
        return true;
    }

    public boolean isNeutral() {
        if (ethicalAlignment.isNeutral() && moralAlignment.isNeutral()) {
            return true;
        }
        return false;
    }
    
    public boolean isLawful() {
        return ethicalAlignment.isPositive();
    }

    public boolean isChaotic() {
        return ethicalAlignment.isNegative();
    }

    public boolean isGood() {
        return moralAlignment.isPositive();
    }

    public boolean isEvil() {
        return moralAlignment.isPositive();
    }
    
	public String getAbbreviation() {
		return abbreviation;
	}
	
	public String getName() {
		return name;
	}
}
