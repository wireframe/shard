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
public class Language {
	public static final Language ABYSSAL = new Language("Abyssal", "Infernal");
	public static final Language AQUAN = new Language("Aquan", "Elven");
	public static final Language AURAN = new Language("Auran", "Draconic");
	public static final Language CELESTIAL = new Language("Celestial", "Celestial");
	public static final Language COMMON = new Language("Common", "Common");
	public static final Language DRACONIC = new Language("Draconic", "Draconic");
	public static final Language DRUIDIC = new Language("Druidic", "Druidic");
	public static final Language DWARVEN = new Language("Dwarven", "Dwarven");
	public static final Language ELVEN = new Language("Elven", "Elven");
	public static final Language GIANT = new Language("Giant", "Dwarven");
	public static final Language GNOME = new Language("Gnome", "Dwarven");
	public static final Language GOBLIN = new Language("Goblin", "Dwarven");
	public static final Language GNOLL = new Language("Gnoll", "Common");
	public static final Language HALFLING = new Language("Halfling", "Common");
	public static final Language IGNAN = new Language("Ignan", "Draconic");
	public static final Language INFERNAL = new Language("Infernal", "Infernal");
	public static final Language ORC = new Language("Orc", "Dwarven");
	public static final Language SYLVAN = new Language("Sylvan", "Elven");
	public static final Language TERRAN = new Language("Terran", "Dwarven");
	public static final Language UNDERCOMMON = new Language("Undercommon", "Elven");
	
	private final String name;
	private final String alphabet;

	public Language(String name, String alphabet) {
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
}
