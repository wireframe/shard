package com.codecrate.shard.ability;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

public class DefaultAbilityContainerTest extends TestCase {

	public void testGetAbilityReturnsSameObject() {
		Ability ability = new DefaultAbility(DefaultAbilityContainer.STRENGTH, 10);
		Map abilities = new HashMap();
		abilities.put(DefaultAbilityContainer.STRENGTH, ability);
		
		DefaultAbilityContainer container = new DefaultAbilityContainer(abilities);
		assertSame(ability, container.getStrength());
		assertSame(ability, container.getAbility(DefaultAbilityContainer.STRENGTH));
	}
}
