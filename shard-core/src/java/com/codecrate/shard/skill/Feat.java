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

import com.codecrate.shard.character.prereq.CharacterPrerequisite;
import com.codecrate.shard.character.prereq.FeatPrerequisite;
import com.codecrate.shard.character.prereq.NullPrerequisite;

public class Feat {
    public static final Feat ARMOR_PROFICIENCY_LIGHT= new Feat("Armor Proficiency (Light)",
            "General", 
            new NullPrerequisite());
    public static final Feat ARMOR_PROFICIENCY_MEDIUM = new Feat("Armor Proficiency (Medium)", 
            "General", 
            new FeatPrerequisite(ARMOR_PROFICIENCY_LIGHT));
    public static final Feat ARMOR_PROFICIENCY_HEAVY = new Feat("Armor Proficiency (Heavy)", 
            "General", 
            new FeatPrerequisite(ARMOR_PROFICIENCY_MEDIUM));
    public static final Feat SHIELD_PROFICIENCY = new Feat("Shield Proficiency", 
            "General", 
            new NullPrerequisite());
    public static final Feat TOWER_SHIELD_PROFICIENCY = new Feat("Tower Shield Proficiency", 
            "General", 
            new FeatPrerequisite(SHIELD_PROFICIENCY));
    
    
    private final String name;
    private final CharacterPrerequisite prerequisite;
    private final String type;
    
    public Feat(String name, String type, CharacterPrerequisite prerequisite) {
        this.name = name;
        this.type = type;
        this.prerequisite = prerequisite;
    }
    
    String getName() {
        return name;
    }
    
    String getType() {
        return type;
    }
    
    CharacterPrerequisite getPrerequisite() {
        return prerequisite;
    }
}
