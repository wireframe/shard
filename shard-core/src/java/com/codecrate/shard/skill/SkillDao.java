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

import com.codecrate.shard.ability.DefaultAbility;
import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.kit.DefaultCharacterClass;
import com.codecrate.shard.race.Language;
import com.codecrate.shard.race.LanguageDao;

public class SkillDao {
    public Collection getClassSkills(CharacterClass kit) {
        Collection skills = new ArrayList();
        if (DefaultCharacterClass.BARBARIAN.equals(kit)) {
            skills.add(DefaultSkill.CLIMB);
            skills.add(DefaultSkill.HANDLE_ANIMAL);
            skills.add(DefaultSkill.INTIMIDATE);
            skills.add(DefaultSkill.JUMP);
            skills.add(DefaultSkill.RIDE);
            skills.add(DefaultSkill.SURVIVAL);
            skills.add(DefaultSkill.SWIM);
        } else if (DefaultCharacterClass.BARD.equals(kit)) {
            skills.add(DefaultSkill.APPRAISE);
            skills.add(DefaultSkill.BALANCE);
            skills.add(DefaultSkill.BLUFF);
            skills.add(DefaultSkill.CLIMB);
            skills.add(DefaultSkill.CONCENTRATION);
            skills.add(DefaultSkill.DECIPHER_SCRIPT);
            skills.add(DefaultSkill.DIPLOMACY);
            skills.add(DefaultSkill.DISGUISE);
            skills.add(DefaultSkill.ESCAPE_ARTIST);
            skills.add(DefaultSkill.GATHER_INFORMATION);
            skills.add(DefaultSkill.HIDE);
            skills.add(DefaultSkill.JUMP);
            skills.add(DefaultSkill.LISTEN);
            skills.add(DefaultSkill.MOVE_SILENTLY);
            skills.add(DefaultSkill.SENSE_MOTIVE);
            skills.add(DefaultSkill.SLEIGHT_OF_HAND);
            skills.add(DefaultSkill.SPELLCRAFT);
            skills.add(DefaultSkill.SWIM);
            skills.add(DefaultSkill.TUMBLE);
            skills.add(DefaultSkill.USE_MAGIC_DEVICE);
        }
        return skills;
    }
    
    public Collection getSynergeticSkills(Skill skill) {
        Collection skills = new ArrayList();
        
        if (DefaultSkill.TUMBLE.equals(skill)) {
            skills.add(DefaultSkill.BALANCE);
            skills.add(DefaultSkill.JUMP);
        } else if (DefaultSkill.JUMP.equals(skill)) {
            skills.add(DefaultSkill.TUMBLE);
        }
        
        return skills;
    }
    
    public Collection getUntrainedSkills() {
        Collection skills = new ArrayList();
        Iterator it = getSkills().iterator();
        while (it.hasNext()) {
            Skill skill = (Skill) it.next();
            if (skill.isUsableUntrained()) {
                skills.add(skill);
            }
        }
        return skills;
    }
    
    public Collection getSkills() {
        Collection skills = new ArrayList();
        skills.add(DefaultSkill.APPRAISE);
        skills.add(DefaultSkill.BALANCE);
        skills.add(DefaultSkill.BLUFF);
        skills.add(DefaultSkill.CLIMB);
        skills.add(DefaultSkill.CONCENTRATION);
        skills.add(DefaultSkill.DECIPHER_SCRIPT);
        skills.add(DefaultSkill.DIPLOMACY);
        skills.add(DefaultSkill.DISABLE_DEVICE);
        skills.add(DefaultSkill.DISGUISE);
        skills.add(DefaultSkill.ESCAPE_ARTIST);
        skills.add(DefaultSkill.FORGERY);
        skills.add(DefaultSkill.GATHER_INFORMATION);
        skills.add(DefaultSkill.HANDLE_ANIMAL);
        skills.add(DefaultSkill.HEAL);
        skills.add(DefaultSkill.HIDE);
        skills.add(DefaultSkill.INTIMIDATE);
        skills.add(DefaultSkill.JUMP);
        skills.add(DefaultSkill.LISTEN);
        skills.add(DefaultSkill.LITERACY);
        skills.add(DefaultSkill.MOVE_SILENTLY);
        skills.add(DefaultSkill.OPEN_LOCK);
        skills.add(DefaultSkill.RIDE);
        skills.add(DefaultSkill.SEARCH);
        skills.add(DefaultSkill.SENSE_MOTIVE);
        skills.add(DefaultSkill.SLEIGHT_OF_HAND);
        skills.add(DefaultSkill.SPELLCRAFT);
        skills.add(DefaultSkill.SPOT);
        skills.add(DefaultSkill.SURVIVAL);
        skills.add(DefaultSkill.SWIM);
        skills.add(DefaultSkill.TUMBLE);
        skills.add(DefaultSkill.USE_MAGIC_DEVICE);
        skills.add(DefaultSkill.USE_ROPE);
        
        skills.addAll(getLanguageSkills());
        return skills;
    }
    
    private Collection getLanguageSkills() {
        Collection skills = new ArrayList();
        LanguageDao languageDao = new LanguageDao();
        Iterator languages = languageDao.getLanguages().iterator();
        while (languages.hasNext()) {
            Language language = (Language) languages.next();
            Skill languageSkill = new DefaultSkill("Speak Language ("
                    + language.getName() + ")", false,
                    DefaultAbility.INTELLIGENCE, false, this);
            skills.add(languageSkill);
        }
        return skills;
    }
}
