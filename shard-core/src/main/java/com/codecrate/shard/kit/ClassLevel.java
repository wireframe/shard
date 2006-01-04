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
package com.codecrate.shard.kit;

/**
 * represents a single level for a character class.
 * ex: Barabarian lvl 1 has +1 Base attack bonus and +1 to saving throws.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface ClassLevel {
	CharacterClass getCharacterClass();

	int getLevel();

	int getBaseAttackBonus();

	int getFortitudeSaveBonus();

	int getReflexSaveBonus();

	int getWillpowerSaveBonus();
}