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

import com.codecrate.shard.DefaultModifierType;
import com.codecrate.shard.ModifierType;

public class DefaultSkillModifierType extends DefaultModifierType implements ModifierType {
	public static final ModifierType RACE = new DefaultSkillModifierType("race", false);
	public static final ModifierType RANK = new DefaultSkillModifierType("rank", true);
	public static final ModifierType ABILITY = new DefaultSkillModifierType("ability", false);
    public static final ModifierType CLASS = new DefaultSkillModifierType("class", false);
    public static final ModifierType SIZE = new DefaultSkillModifierType("size", false);
	
	
	public DefaultSkillModifierType(String name, boolean isStackable) {
	    super(name, isStackable);
	}
}
