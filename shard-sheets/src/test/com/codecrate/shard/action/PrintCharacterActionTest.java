package com.codecrate.shard.action;

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
import com.codecrate.shard.character.Age;
import com.codecrate.shard.character.CharacterProgression;
import com.codecrate.shard.character.CumulativeAgeCategory;
import com.codecrate.shard.character.DefaultAlignment;
import com.codecrate.shard.character.DefaultCharacterLevel;
import com.codecrate.shard.character.DefaultCharacterProgression;
import com.codecrate.shard.character.DefaultGender;
import com.codecrate.shard.character.DefaultPlayerCharacter;
import com.codecrate.shard.kit.DefaultCharacterClass;
import com.codecrate.shard.race.DefaultRace;

public class PrintCharacterActionTest extends TestCase {

	public void testMerge() throws Exception {
		VelocityEngine engine = new VelocityEngine();
		Properties p = new Properties();
	    p.setProperty("file.resource.loader.path", "/home/rsonnek/Projects/shard/shard-sheets/src/conf/templates/html");
	    engine.init(p);
		Template template = engine.getTemplate("default.vm");

		Map scores = new HashMap();
		scores.put(DefaultAbility.STRENGTH, new DefaultAbilityScore(DefaultAbility.STRENGTH, 10));
		DefaultAbilityScoreContainer abilities = new DefaultAbilityScoreContainer(scores);
		
		Collection levels = new ArrayList();
		levels.add(new DefaultCharacterLevel(1, 1, DefaultCharacterClass.BARBARIAN.getClassProgression().getClassLevel(1), new ArrayList()));
		CharacterProgression progression = new DefaultCharacterProgression(levels);
		
		Age age = new Age(20, 100, CumulativeAgeCategory.ADULT);
		
		DefaultPlayerCharacter character = new DefaultPlayerCharacter("test", DefaultRace.HUMAN, DefaultGender.MALE, DefaultAlignment.LAWFUL_GOOD, abilities, null, null, null, age, progression, null, null, 0, null, null);
		
		PrintCharacterAction output = new PrintCharacterAction(character, template);
		System.out.println(output.render());
	}
}
