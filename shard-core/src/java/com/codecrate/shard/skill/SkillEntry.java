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
package com.codecrate.shard.skill;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class SkillEntry {

	private final Skill skill;
	private Collection modifiers = new ArrayList();

	public SkillEntry(Skill skill) {
		this.skill = skill;
	}
	
	public Skill getSkill() {
		return skill;
	}
	
	public int getValue() {
		int value = 0;
		Iterator it = modifiers.iterator();
		while (it.hasNext()) {
			SkillModifier modifier = (SkillModifier) it.next();
			value += modifier.getValue();
		}
		return value;
	}
	
	public void addModifier(SkillModifier modifier) {
		modifiers.add(modifier);
	}
	
	public void removeModifier(SkillModifier modifier) {
		modifiers.remove(modifier);
	}
	
	public Collection getModifiers() {
		return modifiers;
	}
}
