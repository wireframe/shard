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
package com.codecrate.shard.character;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.ImageIcon;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.codecrate.shard.ability.AbilityScoreContainer;
import com.codecrate.shard.armorclass.ArmorClass;
import com.codecrate.shard.armorclass.DexterityArmorClass;
import com.codecrate.shard.character.bio.Age;
import com.codecrate.shard.character.bio.CharacterBio;
import com.codecrate.shard.character.bio.DefaultGender;
import com.codecrate.shard.character.bio.Gender;
import com.codecrate.shard.divine.Deity;
import com.codecrate.shard.equipment.ItemEntryContainer;
import com.codecrate.shard.feat.FeatContainer;
import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.kit.ClassLevel;
import com.codecrate.shard.movement.Encumberance;
import com.codecrate.shard.race.Race;
import com.codecrate.shard.save.SavingThrowEntryContainer;
import com.codecrate.shard.skill.CharacterProgressionSkillEntryContainer;
import com.codecrate.shard.skill.SkillEntryContainer;

/**
 * Default character.
 *
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultPlayerCharacter implements PlayerCharacter, Comparable {
	private static final Log LOG = LogFactory.getLog(DefaultPlayerCharacter.class);

    private Deity deity;
    private CharacterBio bio;
    private BigDecimal challengeRating;
    private Race race;
    private Alignment alignment;
    private AbilityScoreContainer abilities;
    private CharacterProgression progression;
    private ItemEntryContainer inventory;

    private String id;
    private SkillEntryContainer skills;
	private FeatContainer feats;
	private Encumberance encumberance;
	private DexterityArmorClass armorClass;
	private HitPoints hitPoints;
	private SavingThrowEntryContainer savingThrows;
	private Initiative initiative;

    //bio
    private Age age;
    private String name;
	private File portraitFile;
    private Gender gender;
    private String backstory;
    private String height;
    private String weight;
    private Color hairColor;
    private Color eyeColor;

    //progression
	private int experience;
    private SortedSet levels = new TreeSet();

	/**
	 * hibernate constructor.
	 */
	private DefaultPlayerCharacter() {
        this.bio = new DefaultCharacterBio();
        this.progression = new DefaultCharacterProgression();
	}

    /**
     * default constructor.
     * @param encumberance TODO
     * @param skills skills for the character.
     * @param challengeRating challengeRating for the character.
     */
    public DefaultPlayerCharacter(String name, AbilityScoreContainer abilities,
    		Race race,
    		ItemEntryContainer inventory, Encumberance encumberance,
    		Alignment alignment, Deity deity) {
        this.race = race;
        this.abilities = abilities;
        this.deity = deity;
        this.inventory = inventory;
        this.alignment = alignment;
        this.bio = new DefaultCharacterBio(name, DefaultGender.MALE);
        this.progression = new DefaultCharacterProgression();
        this.encumberance = encumberance;

        this.challengeRating = new BigDecimal(0);
        this.feats = new FeatContainer(new ArrayList());
        this.savingThrows = new SavingThrowEntryContainer();
        this.initiative = new Initiative(abilities);
        this.armorClass = new DexterityArmorClass(abilities, encumberance);
        this.skills = new CharacterProgressionSkillEntryContainer(progression);
        this.hitPoints = new CharacterProgressionHitPoints(progression);
    }

    public String toString() {
        return bio.toString();
    }
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof DefaultPlayerCharacter)) {
            return false;
        }
        DefaultPlayerCharacter target = (DefaultPlayerCharacter) object;
        return new EqualsBuilder()
            .append(name, target.name)
            .isEquals();
    }
    public int hashCode() {
        return new HashCodeBuilder(3, 7)
        .append(name)
        .toHashCode();
    }
    public int compareTo(Object object) {
        DefaultPlayerCharacter target = (DefaultPlayerCharacter) object;
        return name.compareTo(target.name);
    }

    public CharacterBio getBio() {
        return bio;
    }

    public BigDecimal getChallengeRating() {
        return challengeRating;
    }

    public int getEffectiveCharacterLevel() {
    	return progression.getCharacterLevel() + race.getLevelAdjustment();
    }

	public AbilityScoreContainer getAbilities() {
		return abilities;
	}

	public int getBaseAttackBonus() {
		int value = 0;
		Iterator it = progression.getClasses().iterator();
		while (it.hasNext()) {
			CharacterClass kit = (CharacterClass) it.next();
			value += progression.getClassLevel(kit).getBaseAttackBonus();
		}
		value += race.getSize().getBaseAttackBonusModifier();
		return value;
	}

	public Alignment getAlignment() {
		return alignment;
	}

	public HitPoints getHitPoints() {
		return hitPoints;
	}

	public Encumberance getEncumberance() {
		return encumberance;
	}
	public Race getRace() {
		return race;
	}

    public ArmorClass getArmorClass() {
        return armorClass;
    }

    public CharacterProgression getCharacterProgression() {
    	return progression;
    }
    public SavingThrowEntryContainer getSavingThrows() {
        return savingThrows;
    }

    public ItemEntryContainer getInventory() {
        return inventory;
    }

    public SkillEntryContainer getSkills() {
    	return skills;
    }

    public Initiative getInitiative() {
        return initiative;
    }
    public FeatContainer getFeats() {
        return feats;
    }
    public Deity getDeity() {
        return deity;
    }

    public void setRace(Race race) {
    	this.race = race;
    }

    public void setAlignment(Alignment alignment) {
    	this.alignment = alignment;
    }

    class DefaultCharacterBio implements CharacterBio {
        /**
         * private hibernate constructor.
         */
        private DefaultCharacterBio() {
        }

        public DefaultCharacterBio(String name2, Gender gender2) {
            name = name2;
            gender = gender2;
        }

        public String toString() {
            return name;
        }

        public File getPortraitFile() {
        	return portraitFile;
        }

        public void setPortraitFile(File file) {
        	portraitFile = file;
        }
        /**
         * @return Returns the backstory.
         */
        public String getBackstory() {
            return backstory;
        }
        /**
         * @param backstory The backstory to set.
         */
        public void setBackstory(String backstory2) {
            backstory = backstory2;
        }
        /**
         * @return Returns the eyeColor.
         */
        public Color getEyeColor() {
            return eyeColor;
        }
        /**
         * @param eyeColor The eyeColor to set.
         */
        public void setEyeColor(Color eyeColor2) {
            eyeColor = eyeColor2;
        }
        /**
         * @return Returns the gender.
         */
        public Gender getGender() {
            return gender;
        }
        /**
         * @param gender The gender to set.
         */
        public void setGender(Gender gender2) {
            gender = gender2;
        }
        /**
         * @return Returns the hairColor.
         */
        public Color getHairColor() {
            return hairColor;
        }
        /**
         * @param hairColor The hairColor to set.
         */
        public void setHairColor(Color hairColor2) {
            hairColor = hairColor2;
        }
        /**
         * @return Returns the height.
         */
        public String getHeight() {
            return height;
        }
        /**
         * @param height The height to set.
         */
        public void setHeight(String height2) {
            height = height2;
        }
        /**
         * @return Returns the image.
         */
        public Image getPortraitImage() {
        	if (null != portraitFile && portraitFile.exists()) {
        		try {
					return new ImageIcon(portraitFile.toURL()).getImage();
				} catch (MalformedURLException e) {
					LOG.warn("Unable to find image using: " + portraitFile);
				}
        	}
            return null;
        }
        /**
         * @return Returns the name.
         */
        public String getName() {
            return name;
        }
        /**
         * @param name The name to set.
         */
        public void setName(String name2) {
            name = name2;
        }
        /**
         * @return Returns the weight.
         */
        public String getWeight() {
            return weight;
        }
        /**
         * @param weight The weight to set.
         */
        public void setWeight(String weight2) {
            weight = weight2;
        }

        public Age getAge() {
            return age;
        }
    }

    class DefaultCharacterProgression implements CharacterProgression {
        /**
         * hibernate constructor
         */
        private DefaultCharacterProgression() {
        }

    	public Collection getClasses() {
    		Collection classes = new ArrayList();
    		Iterator it = levels.iterator();
    		while (it.hasNext()) {
    			CharacterLevel level = (CharacterLevel) it.next();
    			CharacterClass characterClass = level.getCharacterClass();
    			if (!classes.contains(characterClass)) {
    				classes.add(characterClass);
    			}
    		}
    		return classes;
    	}

    	public int getCharacterLevel() {
    		return levels.size();
    	}

    	public ClassLevel getClassLevel(CharacterClass kit) {
    		int level = 0;
    		Iterator it = levels.iterator();
    		while (it.hasNext()) {
    			CharacterLevel characterLevel = (CharacterLevel) it.next();
    			if (kit.equals(characterLevel.getCharacterClass())) {
    				level++;
    			}
    		}
    		return kit.getClassProgression().getClassLevel(level);
    	}

    	public String getDescription() {
    		StringBuffer result = new StringBuffer();
    		Iterator classes = getClasses().iterator();
    		while (classes.hasNext()) {
    			CharacterClass kit = (CharacterClass) classes.next();
    			ClassLevel maxLevel = getClassLevel(kit);
    			result.append(kit.getAbbreviation() + " " + maxLevel.getLevel());

    			if (classes.hasNext()) {
    				result.append(" / ");
    			}
    		}
    		return result.toString();
    	}

        public void addLevel(CharacterLevel level) {
            levels.add(level);
        }

    	public String toString() {
    		return getDescription();
    	}

        public Collection getCharacterLevels() {
            return levels;
        }

        public CharacterLevel getCharacterLevel(int level) {
    	    CharacterLevel kit = null;
    	    Iterator it = levels.iterator();
    	    while (it.hasNext()) {
    	        CharacterLevel object = (CharacterLevel) it.next();
    	        if (level == object.getLevel()) {
    	            return object;
    	        }
    	    }
    	    return kit;
        }

        public BigDecimal getMulticlassExperiencePenalty() {

            return null;
        }

    	public int getExperience() {
    		return experience;
    	}

    	public int getNextCharacterLevel() {
    		return getCharacterLevel() + 1;
    	}
    }
}
