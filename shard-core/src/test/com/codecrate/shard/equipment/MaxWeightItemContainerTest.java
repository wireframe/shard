package com.codecrate.shard.equipment;

import java.math.BigDecimal;

import junit.framework.TestCase;

import org.easymock.MockControl;

public class MaxWeightItemContainerTest extends TestCase {

	public void testCanNotAddItemIfOverWeight() {
		MockControl mockItem = MockControl.createControl(Item.class);
		Item item = (Item) mockItem.getMock();
		item.getWeight();
		mockItem.setReturnValue(new BigDecimal(2));
		item.getWeight();
		mockItem.setReturnValue(new BigDecimal(2));
		mockItem.replay();
		
		MaxWeightItemContainer container = new MaxWeightItemContainer(1);
		assertFalse(container.canAdd(item));
		
		container.add(item);
		assertEquals(0, container.getItems().size());
	}
}
