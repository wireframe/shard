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
package com.codecrate.shard.equipment;

import junit.framework.TestCase;

import org.easymock.MockControl;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class EquipmentContainerTest extends TestCase {

    public void testCannotEquipIfItemNotInInventory() {
        MockControl mockArmor = MockControl.createControl(Equipment.class);
        Equipment armor = (Equipment) mockArmor.getMock();
        mockArmor.replay();
        
        MockControl mockItemContainer = MockControl.createControl(ItemEntryContainer.class);
        ItemEntryContainer itemContainer = (ItemEntryContainer) mockItemContainer.getMock();
        itemContainer.hasItem(armor);
        mockItemContainer.setReturnValue(false);
        mockItemContainer.replay();

        MockControl mockLocation = MockControl.createControl(EquipmentLocation.class);
        EquipmentLocation location = (EquipmentLocation) mockLocation.getMock();
        mockLocation.replay();
        
        EquipmentContainer equipment = new EquipmentContainer(itemContainer);
        try {
			equipment.equip(location, armor);
        } catch (IllegalArgumentException expectec) { }
    }
    
    public void testEquipItemIsPlacesInLocation() {
        MockControl mockArmor = MockControl.createControl(Equipment.class);
        Equipment armor = (Equipment) mockArmor.getMock();
        mockArmor.replay();
        
        MockControl mockItemContainer = MockControl.createControl(ItemEntryContainer.class);
        ItemEntryContainer itemContainer = (ItemEntryContainer) mockItemContainer.getMock();
        itemContainer.hasItem(armor);
        mockItemContainer.setReturnValue(true);
        mockItemContainer.replay();

        MockControl mockLocation = MockControl.createControl(EquipmentLocation.class);
        EquipmentLocation location = (EquipmentLocation) mockLocation.getMock();
        mockLocation.replay();
        
        EquipmentContainer equipment = new EquipmentContainer(itemContainer);
        equipment.equip(location, armor);
        
        assertEquals(armor, equipment.getEquipment(location));
    }
}