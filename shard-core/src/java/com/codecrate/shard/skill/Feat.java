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
