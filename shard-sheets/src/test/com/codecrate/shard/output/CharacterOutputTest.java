package com.codecrate.shard.output;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import junit.framework.TestCase;

import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;

import com.codecrate.shard.ability.DefaultAbility;
import com.codecrate.shard.ability.DefaultAbilityScore;
import com.codecrate.shard.ability.DefaultAbilityScoreContainer;
import com.codecrate.shard.character.CharacterProgression;
import com.codecrate.shard.character.DefaultAlignment;
import com.codecrate.shard.character.DefaultCharacterProgression;
import com.codecrate.shard.character.DefaultGender;
import com.codecrate.shard.character.DefaultPlayerCharacter;
import com.codecrate.shard.race.DefaultRace;

public class CharacterOutputTest extends TestCase {

	public void testMerge() throws Exception {
		VelocityEngine engine = new VelocityEngine();
		Properties p = new Properties();
	    p.setProperty("file.resource.loader.path", "/home/rsonnek/Projects/shard/shard-sheets/src/conf/templates/html");
	    engine.init(p);
		Template template = engine.getTemplate("default.vm");

		Map scores = new HashMap();
		scores.put(DefaultAbility.STRENGTH, new DefaultAbilityScore(DefaultAbility.STRENGTH, 10));
		DefaultAbilityScoreContainer abilities = new DefaultAbilityScoreContainer(scores);
		CharacterProgression progression = new DefaultCharacterProgression(new ArrayList());
		DefaultPlayerCharacter character = new DefaultPlayerCharacter("test", DefaultRace.HUMAN, DefaultGender.MALE, DefaultAlignment.LAWFUL_GOOD, abilities, null, null, null, null, progression, null, null, 0, null, null);
		
		CharacterOutput output = new CharacterOutput(character, template);
		System.out.println(output.render());
	}
}
