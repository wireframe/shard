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
package com.codecrate.shard.race;


/**
 * Defines a language spoken by races.
 * 
 * @see http://www.d20srd.org/srd/skills/speakLanguage.htm
 */
public class DefaultLanguage implements Language {
	public static final DefaultLanguage ABYSSAL = new DefaultLanguage("Abyssal", "Infernal");
	public static final DefaultLanguage AQUAN = new DefaultLanguage("Aquan", "Elven");
	public static final DefaultLanguage AURAN = new DefaultLanguage("Auran", "Draconic");
	public static final DefaultLanguage CELESTIAL = new DefaultLanguage("Celestial", "Celestial");
	public static final DefaultLanguage COMMON = new DefaultLanguage("Common", "Common");
	public static final DefaultLanguage DRACONIC = new DefaultLanguage("Draconic", "Draconic");
	public static final DefaultLanguage DRUIDIC = new DefaultLanguage("Druidic", "Druidic");
	public static final DefaultLanguage DWARVEN = new DefaultLanguage("Dwarven", "Dwarven");
	public static final DefaultLanguage ELVEN = new DefaultLanguage("Elven", "Elven");
	public static final DefaultLanguage GIANT = new DefaultLanguage("Giant", "Dwarven");
	public static final DefaultLanguage GNOME = new DefaultLanguage("Gnome", "Dwarven");
	public static final DefaultLanguage GOBLIN = new DefaultLanguage("Goblin", "Dwarven");
	public static final DefaultLanguage GNOLL = new DefaultLanguage("Gnoll", "Common");
	public static final DefaultLanguage HALFLING = new DefaultLanguage("Halfling", "Common");
	public static final DefaultLanguage IGNAN = new DefaultLanguage("Ignan", "Draconic");
	public static final DefaultLanguage INFERNAL = new DefaultLanguage("Infernal", "Infernal");
	public static final DefaultLanguage ORC = new DefaultLanguage("Orc", "Dwarven");
	public static final DefaultLanguage SYLVAN = new DefaultLanguage("Sylvan", "Elven");
	public static final DefaultLanguage TERRAN = new DefaultLanguage("Terran", "Dwarven");
	public static final DefaultLanguage UNDERCOMMON = new DefaultLanguage("Undercommon", "Elven");
    
	private String name;
	private String alphabet;

	public DefaultLanguage(String name, String alphabet) {
		this.name = name;
		this.alphabet = alphabet;
	}

	public String toString() {
		return name;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAlphabet() {
		return alphabet;
	}
	
	public void setAlphabet(String alphabet) {
	    this.alphabet = alphabet;
	}
}
