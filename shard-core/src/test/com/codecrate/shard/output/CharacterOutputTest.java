package com.codecrate.shard.output;

import java.util.Properties;

import junit.framework.TestCase;

import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;

import com.codecrate.shard.character.DefaultPlayerCharacter;

public class CharacterOutputTest extends TestCase {

	public void testMerge() throws Exception {
		VelocityEngine engine = new VelocityEngine();
		Properties p = new Properties();
	    p.setProperty("file.resource.loader.path", "/shard/shard-core/src/conf/templates/html");
	    engine.init(p);
		Template template = engine.getTemplate("default.vm");
		DefaultPlayerCharacter character = new DefaultPlayerCharacter("test", null, null, null, null, null, null, null, null, null, null, null, 0);
		CharacterOutput output = new CharacterOutput(character, template);
		System.out.println(output.render());
	}
}
