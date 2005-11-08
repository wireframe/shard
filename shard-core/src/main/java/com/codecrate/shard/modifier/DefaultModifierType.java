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
package com.codecrate.shard.modifier;

import java.util.Collection;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultModifierType implements ModifierType {

	public static final ModifierType ARMOR = new DefaultModifierType("armor", new HighestModifierCalculator());
	public static final ModifierType SHIELD = new DefaultModifierType("shield", new HighestModifierCalculator());
	public static final ModifierType NATURAL = new DefaultModifierType("natural", new HighestModifierCalculator());
	public static final ModifierType SIZE= new DefaultModifierType("size", new HighestModifierCalculator());
	public static final ModifierType ENHANCEMENT = new DefaultModifierType("enhancement", new HighestModifierCalculator());
	public static final ModifierType DEFLECTION = new DefaultModifierType("deflection", new HighestModifierCalculator());
	public static final ModifierType DODGE = new DefaultModifierType("dodge", new StackableModifierCalculator());
	public static final ModifierType AGE = new DefaultModifierType("age", new StackableModifierCalculator());
    public static final ModifierType RACE = new DefaultModifierType("race", new HighestModifierCalculator());
	public static final ModifierType RANK = new DefaultModifierType("rank", new StackableModifierCalculator());
	public static final ModifierType ABILITY = new DefaultModifierType("ability", new HighestModifierCalculator());
    public static final ModifierType CLASS = new DefaultModifierType("class", new HighestModifierCalculator());
    public static final ModifierType ENCUMBERANCE = new DefaultModifierType("encumberance", new HighestModifierCalculator());
    public static final ModifierType SYNERGY = new DefaultModifierType("skill synergy", new HighestModifierCalculator());

    private final String name;
    private final ModifierCalculator calculator;

    public DefaultModifierType(String name, ModifierCalculator calculator) {
        this.name = name;
        this.calculator = calculator;
    }

    public String toString() {
        return name;
    }
    
    public String getName() {
        return name;
    }
    
    public int calculateModifier(Collection modifiers) {
    	return calculator.calculateModifier(modifiers);
    }
}
