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

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class EquipmentContainer {
    
	private Map locations = new HashMap();
    private final ItemEntryContainer items;
    
    public EquipmentContainer(ItemEntryContainer items) {
        this.items = items;
    }
    
    /**
     * equip an item to a location.
     * @param location location to equip the item to.
     * @param equipment item to equip.
     */
    public void equip(EquipmentLocation location, Equipment equipment) {
        if (!items.hasItem(equipment)) {
            throw new IllegalArgumentException("Equipment not found in inventory.");
        }
        locations.put(location, equipment);
    }
    
    public Equipment getEquipment(EquipmentLocation location) {
        return (Equipment) locations.get(location);
    }
}
