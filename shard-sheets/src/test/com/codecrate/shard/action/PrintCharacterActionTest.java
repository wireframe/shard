package com.codecrate.shard.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import junit.framework.TestCase;

import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;

import com.codecrate.shard.ability.DefaultAbility;
import com.codecrate.shard.ability.DefaultAbilityScore;
import com.codecrate.shard.ability.DefaultAbilityScoreContainer;
import com.codecrate.shard.armorclass.ArmorClass;
import com.codecrate.shard.armorclass.DefaultArmorClass;
import com.codecrate.shard.armorclass.DexterityArmorClass;
import com.codecrate.shard.character.Age;
import com.codecrate.shard.character.CharacterProgression;
import com.codecrate.shard.character.CumulativeAgeCategory;
import com.codecrate.shard.character.DefaultAlignment;
import com.codecrate.shard.character.DefaultCharacterLevel;
import com.codecrate.shard.character.DefaultCharacterProgression;
import com.codecrate.shard.character.DefaultGender;
import com.codecrate.shard.character.DefaultPlayerCharacter;
import com.codecrate.shard.character.HitPoints;
import com.codecrate.shard.equipment.Coin;
import com.codecrate.shard.equipment.ItemContainer;
import com.codecrate.shard.equipment.ItemEntry;
import com.codecrate.shard.equipment.MaxWeightItemContainer;
import com.codecrate.shard.kit.DefaultCharacterClass;
import com.codecrate.shard.movement.DefaultEncumberance;
import com.codecrate.shard.race.DefaultRace;
import com.codecrate.shard.save.SavingThrowContainer;
import com.codecrate.shard.skill.DefaultSkill;
import com.codecrate.shard.skill.SkillEntry;
import com.codecrate.shard.skill.SkillEntryContainer;

public class PrintCharacterActionTest extends TestCase {

	public void testMerge() throws Exception {
		VelocityEngine engine = new VelocityEngine();
		Properties p = new Properties();
	    p.setProperty("file.resource.loader.path", "/home/rsonnek/Projects/shard/shard-sheets/src/conf/templates/html");
	    p.setProperty("runtime.log.logsystem.class", "org.apache.velocity.tools.generic.log.CommonsLogLogSystem");
	    engine.init(p);
		Template template = engine.getTemplate("default.vm");

		Map scores = new HashMap();
		scores.put(DefaultAbility.STRENGTH, new DefaultAbilityScore(DefaultAbility.STRENGTH, 10));
		scores.put(DefaultAbility.DEXTERITY, new DefaultAbilityScore(DefaultAbility.DEXTERITY, 18));
		DefaultAbilityScoreContainer abilities = new DefaultAbilityScoreContainer(scores);
		
		Collection levels = new ArrayList();
		levels.add(new DefaultCharacterLevel(1, 1, DefaultCharacterClass.BARBARIAN.getClassProgression().getClassLevel(1), new ArrayList()));
		levels.add(new DefaultCharacterLevel(2, 1, DefaultCharacterClass.FIGHTER.getClassProgression().getClassLevel(1), new ArrayList()));
		CharacterProgression progression = new DefaultCharacterProgression(levels);
		
		Age age = new Age(20, 100, CumulativeAgeCategory.ADULT);
		
		HitPoints hitPoints = new HitPoints();
		
		ArmorClass armorClass = new DexterityArmorClass(abilities, DefaultEncumberance.LIGHT, new DefaultArmorClass());
		
		SavingThrowContainer savingThrows = new SavingThrowContainer();

		ItemContainer itemContainer = new MaxWeightItemContainer(9999);
		itemContainer.add(new ItemEntry(Coin.GOLD_PIECE, 100));
		
		Map skillMap = new HashMap();
		skillMap.put(DefaultSkill.SWIM, new SkillEntry(DefaultSkill.SWIM));
		SkillEntryContainer skills = new SkillEntryContainer(skillMap, 3);
		
		DefaultPlayerCharacter character = new DefaultPlayerCharacter("Gunthor the Terrible",
				DefaultRace.HUMAN, DefaultGender.MALE,
				DefaultAlignment.LAWFUL_GOOD, abilities, hitPoints, armorClass,
				DefaultEncumberance.LIGHT, age, progression, savingThrows, itemContainer, 0, skills, new BigDecimal(20));
		
		PrintCharacterAction output = new PrintCharacterAction(character, template);
		System.out.println(output.render());
	}
}
