package com.codecrate.shard.character.race;

import com.codecrate.shard.character.armorclass.ArmorClassModifier;
import com.codecrate.shard.character.armorclass.DefaultArmorClass;

public class DefaultRacialSize implements RacialSize {
    public static final RacialSize FINE = new DefaultRacialSize(0, 8);
    public static final RacialSize DIMINUTIVE = new DefaultRacialSize(0, 4);
    public static final RacialSize TINY = new DefaultRacialSize(0, 2);
    public static final RacialSize SMALL = new DefaultRacialSize(1, 1);
    public static final RacialSize MEDUIM = new DefaultRacialSize(0, 0);
    public static final RacialSize LARGE = new DefaultRacialSize(0, -1);
    public static final RacialSize HUGE = new DefaultRacialSize(0, -2);
    public static final RacialSize GARGANTUAN = new DefaultRacialSize(0, -4);
    public static final RacialSize COLOSSAL = new DefaultRacialSize(0, -8);

    
    private ArmorClassModifier armorClassModifier;
    private int baseAttackBonusModifier;

    public DefaultRacialSize(int armorClassModifier, int baseAttackBonusModifier) {
        this.armorClassModifier = new RacialSizeArmorClassModifier(armorClassModifier);
        this.baseAttackBonusModifier = baseAttackBonusModifier;
    }
    
    public int getBaseAttackBonusModifier() {
        return baseAttackBonusModifier;
    }
    
	public String getModifierType() {
		return armorClassModifier.getModifierType();
	}

	public int getModifier() {
		return armorClassModifier.getModifier();
	}

	
    private static class RacialSizeArmorClassModifier implements ArmorClassModifier {

    	private int modifier;
    	
    	public RacialSizeArmorClassModifier(int modifier) {
    		this.modifier = modifier;
    	}
    	
		public String getModifierType() {
			return DefaultArmorClass.TYPE_SIZE;
		}

		public int getModifier() {
			return modifier;
		}
    	
    }
}
