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

import java.util.StringTokenizer;

/**
 * An Alignment defines a characters moral and ethical preconditions.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public enum Alignment {
	LAWFUL_GOOD (AlignmentComponent.POSITIVE, AlignmentComponent.POSITIVE, "Lawful Good"),
	LAWFUL_NEUTRAL (AlignmentComponent.POSITIVE, AlignmentComponent.NEUTRAL, "Lawful Neutral"),
	LAWFUL_EVIL (AlignmentComponent.POSITIVE, AlignmentComponent.NEGATIVE, "Lawful Evil"),
	NEUTRAL_GOOD (AlignmentComponent.NEUTRAL, AlignmentComponent.POSITIVE, "Neutral Good"),
	NEUTRAL_NEUTRAL (AlignmentComponent.NEUTRAL, AlignmentComponent.NEUTRAL, "Neutral"),
	NEUTRAL_EVIL (AlignmentComponent.NEUTRAL, AlignmentComponent.NEGATIVE, "Neutral Evil"),
	CHAOTIC_GOOD (AlignmentComponent.NEGATIVE, AlignmentComponent.POSITIVE, "Chaotic Good"),
	CHAOTIC_NEUTRAL (AlignmentComponent.NEGATIVE, AlignmentComponent.NEUTRAL, "Chaotic Neutral"),
	CHAOTIC_EVIL (AlignmentComponent.NEGATIVE, AlignmentComponent.NEGATIVE, "Chaotic Evil");

	private String name;
	private AlignmentComponent ethicalAlignment;
	private AlignmentComponent moralAlignment;

	Alignment(AlignmentComponent ethicalAlignment, AlignmentComponent moralAlignment, String name) {
		this.ethicalAlignment = ethicalAlignment;
		this.moralAlignment = moralAlignment;
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public boolean isTrueNeutral() {
		if (isEthicalNeutral() && isMoralNeutral()) {
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
		return moralAlignment.isNegative();
	}

	public boolean isEthicalNeutral() {
		return ethicalAlignment.isNeutral();
	}

	public boolean isMoralNeutral() {
		return moralAlignment.isNeutral();
	}

	public String getName() {
		return name;
	}

	public String getAbbreviation() {
		String abbreviation = "";
		StringTokenizer tokens = new StringTokenizer(name, " ");
		while (tokens.hasMoreElements()) {
			String token = (String) tokens.nextElement();
			abbreviation += "" + token.charAt(0);
		}
		return abbreviation;
	}
	

	/**
	 * Helper class for defining an alignment.
	 * Alignments can be broken up into two components, (law to chaos) and
	 * (good to evil).  both of these components have a positive, neutral 
	 * and negative element.
	 * 
	 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
	 */
	private enum AlignmentComponent {
	    POSITIVE (Boolean.TRUE),
	    NEGATIVE (Boolean.FALSE),
	    NEUTRAL (null);

	    private Boolean alignment;

	    AlignmentComponent(Boolean alignment) {
	        this.alignment = alignment;
	    }
	   
	    public boolean isPositive() {
	        if (null != alignment) {
	            return alignment.booleanValue();
	        }
	        return false;
	    }

	    public boolean isNegative() {
	        if (null != alignment) {
	            return !alignment.booleanValue();
	        }
	        return false;
	    }
	    
	    public boolean isNeutral() {
	        return alignment == null;
	    }
	}
}
