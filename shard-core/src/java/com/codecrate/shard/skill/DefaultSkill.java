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

import java.util.Collection;

import com.codecrate.shard.ability.Ability;
import com.codecrate.shard.ability.DefaultAbility;
import com.codecrate.shard.modifier.DefaultModifierType;
import com.codecrate.shard.modifier.ModifierType;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek </a>
 */
public class DefaultSkill implements Skill {
	public static final ModifierType TYPE_RACE = new DefaultModifierType("race", false);
	public static final ModifierType TYPE_RANK = new DefaultModifierType("rank", true);
	public static final ModifierType TYPE_ABILITY = new DefaultModifierType("ability", false);
    public static final ModifierType TYPE_CLASS = new DefaultModifierType("class", false);
    public static final ModifierType TYPE_SIZE = new DefaultModifierType("size", false);

    
    public static final Skill APPRAISE = new DefaultSkill("Appraise", true,
            DefaultAbility.INTELLIGENCE, false, new DefaultSkillDao());

    public static final Skill BALANCE = new DefaultSkill("Balance", true,
            DefaultAbility.DEXTERITY, false, new DefaultSkillDao());

    public static final Skill BLUFF = new DefaultSkill("Bluff", true,
            DefaultAbility.CHARISMA, false, new DefaultSkillDao());

    public static final Skill CLIMB = new DefaultSkill("Climb", true,
            DefaultAbility.STRENGTH, true, new DefaultSkillDao());

    public static final Skill CONCENTRATION = new DefaultSkill("Concentration",
            true, DefaultAbility.CONSTITUTION, false, new DefaultSkillDao());

    public static final Skill DECIPHER_SCRIPT = new DefaultSkill(
            "Decipher Script", false, DefaultAbility.INTELLIGENCE,
            false, new DefaultSkillDao());

    public static final Skill DIPLOMACY = new DefaultSkill("Diplomacy", true,
            DefaultAbility.CHARISMA, false, new DefaultSkillDao());

    public static final Skill DISABLE_DEVICE = new DefaultSkill(
            "Disable Device", false, DefaultAbility.INTELLIGENCE,
            false, new DefaultSkillDao());

    public static final Skill DISGUISE = new DefaultSkill("Disguise", true,
            DefaultAbility.CHARISMA, false, new DefaultSkillDao());

    public static final Skill ESCAPE_ARTIST = new DefaultSkill("Escape Artist",
            true, DefaultAbility.DEXTERITY, true, new DefaultSkillDao());

    public static final Skill FORGERY = new DefaultSkill("Forgery",
            true, DefaultAbility.INTELLIGENCE, false, new DefaultSkillDao());

    public static final Skill GATHER_INFORMATION = new DefaultSkill("Gather Information",
            true, DefaultAbility.CHARISMA, false, new DefaultSkillDao());

    public static final Skill HANDLE_ANIMAL = new DefaultSkill("Handle Animal",
            false, DefaultAbility.CHARISMA, false, new DefaultSkillDao());

    public static final Skill HEAL = new DefaultSkill("Heal",
            true, DefaultAbility.WISDOM, false, new DefaultSkillDao());

    public static final Skill HIDE = new DefaultSkill("Hide",
            true, DefaultAbility.DEXTERITY, true, new DefaultSkillDao());

    public static final Skill INTIMIDATE = new DefaultSkill("Intimidate",
            true, DefaultAbility.CHARISMA, false, new DefaultSkillDao());

    public static final Skill JUMP = new DefaultSkill("Jump",
            true, DefaultAbility.STRENGTH, true, new DefaultSkillDao());

    public static final Skill LISTEN = new DefaultSkill("Listen",
            true, DefaultAbility.WISDOM, false, new DefaultSkillDao());

    public static final Skill LITERACY = new DefaultSkill("Literacy",
            false, DefaultAbility.INTELLIGENCE, false, new DefaultSkillDao());

    public static final Skill MOVE_SILENTLY = new DefaultSkill("Move Silently",
            true, DefaultAbility.DEXTERITY, true, new DefaultSkillDao());

    public static final Skill OPEN_LOCK = new DefaultSkill("Open Lock",
            false, DefaultAbility.DEXTERITY, false, new DefaultSkillDao());

    public static final Skill RIDE = new DefaultSkill("Ride",
            true, DefaultAbility.DEXTERITY, false, new DefaultSkillDao());

    public static final Skill SEARCH = new DefaultSkill("Search",
            true, DefaultAbility.INTELLIGENCE, false, new DefaultSkillDao());

    public static final Skill SENSE_MOTIVE= new DefaultSkill("Sense Motive",
            true, DefaultAbility.WISDOM, false, new DefaultSkillDao());

    public static final Skill SLEIGHT_OF_HAND = new DefaultSkill("Slight of Hand",
            false, DefaultAbility.DEXTERITY, true, new DefaultSkillDao());

    public static final Skill SPELLCRAFT = new DefaultSkill("Spellcraft",
            false, DefaultAbility.INTELLIGENCE, false, new DefaultSkillDao());

    public static final Skill SPOT = new DefaultSkill("Spot",
            true, DefaultAbility.WISDOM, false, new DefaultSkillDao());

    public static final Skill SURVIVAL = new DefaultSkill("Survival",
            true, DefaultAbility.WISDOM, false, new DefaultSkillDao());

    public static final Skill SWIM = new DefaultSkill("Swim",
            true, DefaultAbility.STRENGTH, true, new DefaultSkillDao());

    public static final Skill TUMBLE = new DefaultSkill("Tumble",
            false, DefaultAbility.DEXTERITY, true, new DefaultSkillDao());

    public static final Skill USE_MAGIC_DEVICE= new DefaultSkill("Use Magic Device",
            false, DefaultAbility.CHARISMA, false, new DefaultSkillDao());

    public static final Skill USE_ROPE = new DefaultSkill("Use Rope",
            true, DefaultAbility.DEXTERITY, false, new DefaultSkillDao());


    private final String name;
    private final boolean usableUntrained;
    private Collection skillSynergies;
    private final Ability ability;
    private final boolean armorPenalty;

	private final SkillDao skillDao;

    public DefaultSkill(String name, boolean usableUntrained, Ability ability,
            boolean armorPenalty, SkillDao skillDao) {
        this.name = name;
        this.usableUntrained = usableUntrained;
        this.ability = ability;
        this.armorPenalty = armorPenalty;
		this.skillDao = skillDao;
    }

    public String toString() {
        return name;
    }
    
    public String getName() {
        return name;
    }

    public Ability getAbility() {
        return ability;
    }

    public boolean isUsableUntrained() {
        return usableUntrained;
    }

    public Collection getChildSkillSynergies() {
    	if (null == skillSynergies) {
            this.skillSynergies = skillDao.getSynergeticSkills(this);
    	}
        return skillSynergies;
    }

    public boolean hasArmorCheckPenalty() {
        return armorPenalty;
    }
}