package com.codecrate.shard.ability;

public class DefaultAbility implements Ability {
    public static final Ability STRENGTH = new DefaultAbility("Strength");
	public static final Ability DEXTERITY = new DefaultAbility("dexterity");
	public static final Ability CONSTITUTION = new DefaultAbility("constitution");
	public static final Ability WISDOM = new DefaultAbility("wisdom");
	public static final Ability INTELLIGENCE = new DefaultAbility("intelligence");
	public static final Ability CHARISMA = new DefaultAbility("charisma");
    
    private final String name;
    
    public DefaultAbility(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return name.toUpperCase().substring(0, 3);
    }

}
