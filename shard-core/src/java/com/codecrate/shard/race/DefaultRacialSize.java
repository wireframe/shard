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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.codecrate.shard.DefaultModifier;
import com.codecrate.shard.Modifier;
import com.codecrate.shard.armorclass.DefaultArmorClass;
import com.codecrate.shard.skill.DefaultSkill;
import com.codecrate.shard.skill.DefaultSkillModifier;
import com.codecrate.shard.skill.DefaultSkillModifierType;
import com.codecrate.shard.skill.SkillModifier;

/**
 * Note: Large or larger creatures using reach weapons can strike up to double their natural reach 
 * but can�t strike at their natural reach or less.
 *  
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultRacialSize implements RacialSize {
    public static final RacialSize FINE = new DefaultRacialSize(
			"Fine", 8,
			new DefaultModifier(DefaultArmorClass.SIZE, 8),
			new BigDecimal(".5"), 0, new ArrayList(), new BigDecimal(".125"));

	public static final RacialSize DIMINUTIVE = new DefaultRacialSize(
			"Diminutive", 4, new DefaultModifier(
					DefaultArmorClass.SIZE, 4), new BigDecimal("1"), 
					0, new ArrayList(), 
					new BigDecimal(".25"));

	public static final RacialSize TINY = new DefaultRacialSize(
			"Tiny", 2,
			new DefaultModifier(DefaultArmorClass.SIZE, 2),
			new BigDecimal("2.5"), 0, new ArrayList(), new BigDecimal(".5"));

	public static final RacialSize SMALL = new DefaultRacialSize(
			"Small", 1,
			new DefaultModifier(DefaultArmorClass.SIZE, 1),
			new BigDecimal("5"), 5, Arrays.asList(new SkillModifier[] {
					new DefaultSkillModifier(DefaultSkillModifierType.SIZE, 4, DefaultSkill.HIDE)}),
					new BigDecimal(".75"));

	public static final RacialSize MEDIUM = new DefaultRacialSize(
			"Medium", 0,
			new DefaultModifier(DefaultArmorClass.SIZE, 0),
			new BigDecimal("5"), 5, new ArrayList(), new BigDecimal("1"));

	public static final RacialSize LARGE_TALL = new DefaultRacialSize(
			"Large (Tall)", -1, new DefaultModifier(
					DefaultArmorClass.SIZE, -1), new BigDecimal("10"), 
					10, new ArrayList(),
					new BigDecimal("2"));

	public static final RacialSize LARGE_LONG = new DefaultRacialSize(
			"Large (Long)", -1, new DefaultModifier(
					DefaultArmorClass.SIZE, -1), new BigDecimal("10"), 
					5, new ArrayList(), 
					new BigDecimal("2"));

	public static final RacialSize HUGE_TALL = new DefaultRacialSize(
			"Huge (Tall)", -2, new DefaultModifier(
					DefaultArmorClass.SIZE, -2), new BigDecimal("15"), 
					15, new ArrayList(),
					new BigDecimal("4"));

	public static final RacialSize HUGE_LONG = new DefaultRacialSize(
			"Huge (Long)", -2, new DefaultModifier(
					DefaultArmorClass.SIZE, -2), new BigDecimal("15"), 
					10, new ArrayList(), 
					new BigDecimal("4"));

	public static final RacialSize GARGANTUAN_TALL = new DefaultRacialSize(
			"Gargantuan (Tall)", -4, new DefaultModifier(
					DefaultArmorClass.SIZE, -4), new BigDecimal("20"), 
					20, new ArrayList(), 
					new BigDecimal("8"));

	public static final RacialSize GARGANTUAN_LONG = new DefaultRacialSize(
			"Gargantuan (Long)", -4, new DefaultModifier(
					DefaultArmorClass.SIZE, -4), new BigDecimal("20"), 
					15, new ArrayList(), 
					new BigDecimal("8"));

	public static final RacialSize COLOSSAL_TALL = new DefaultRacialSize(
			"Colossal (Tall)", -8, new DefaultModifier(
					DefaultArmorClass.SIZE, -8), new BigDecimal("30"), 
					30, new ArrayList(), 
					new BigDecimal("16"));

	public static final RacialSize COLOSSAL_LONG = new DefaultRacialSize(
			"Colossal (Long)", -8, new DefaultModifier(
					DefaultArmorClass.SIZE, -8), new BigDecimal("30"), 
					20, new ArrayList(),
					new BigDecimal("16"));

    
    private final String name;
    private final Modifier armorClassModifier;
    private final int baseAttackBonusModifier;
    private final BigDecimal space;
    private final int reach;
	private final Collection skillModifiers;
    private final BigDecimal encumberanceMultiplier;

    public DefaultRacialSize(String name, int baseAttackBonusModifier, 
    		Modifier armorClassModifier, BigDecimal space, int reach,
			Collection skillModifiers, BigDecimal encumberanceMultiplier) {
    	this.name = name;
        this.baseAttackBonusModifier = baseAttackBonusModifier;
        this.armorClassModifier = armorClassModifier;
        this.space = space;
        this.reach = reach;
		this.skillModifiers = skillModifiers;
        this.encumberanceMultiplier = encumberanceMultiplier;
    }
    
    public String toString() {
    	return name;
    }
    
    public String getName() {
    	return name;
    }
    
    public int getBaseAttackBonusModifier() {
        return baseAttackBonusModifier;
    }

    public Modifier getArmorClassModifier() {
		return armorClassModifier;
	}
	
	public BigDecimal getSpace() {
		return space;
	}
	
	public int getReach() {
		return reach;
	}
	
	public Collection getSkillModifiers() {
		return skillModifiers;
	}
	
	public BigDecimal getEncumberanceMultiplier() {
	    return encumberanceMultiplier;
	}
}
