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
		context.put("renderTool", new RenderTool());
		this.template = template;
	}
	
	public StringWriter render() throws ResourceNotFoundException, ParseErrorException, MethodInvocationException, Exception {
		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		return writer;
	}
}
