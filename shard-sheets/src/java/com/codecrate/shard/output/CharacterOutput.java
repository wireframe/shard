package com.codecrate.shard.output;

import java.io.StringWriter;
import java.util.Date;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import com.codecrate.shard.ShardRuntime;
import com.codecrate.shard.character.PlayerCharacter;

/**
 * 
 * @see http://jakarta.apache.org/velocity/developer-guide.html
 */
public class CharacterOutput {
	private VelocityContext context = new VelocityContext();
	private Template template;
	
	public CharacterOutput(PlayerCharacter character, Template template) {
		context.put("character", character);
		context.put("runtime", new ShardRuntime());
		context.put("date", new Date());
		this.template = template;
	}
	
	public StringWriter render() throws ResourceNotFoundException, ParseErrorException, MethodInvocationException, Exception {
		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		return writer;
	}
}
