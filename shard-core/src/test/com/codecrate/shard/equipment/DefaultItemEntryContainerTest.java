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

import java.util.Arrays;

import junit.framework.TestCase;

public class DefaultItemEntryContainerTest extends TestCase {
    public void testTotalWeightCalculatedWithAllItems() {
        ItemEntry entry = new ItemEntry(Coin.COPPER_PIECE, 1);
        DefaultItemEntryContainer container = new DefaultItemEntryContainer(Arrays.asList(new ItemEntry[] {entry}));
        assertEquals(Coin.COPPER_PIECE.getWeight(), container.getTotalWeight());
    }
    
    public void testTotalCostCalculatedWithAllItems() {
        ItemEntry entry = new ItemEntry(Coin.COPPER_PIECE, 1);
        DefaultItemEntryContainer container = new DefaultItemEntryContainer(Arrays.asList(new ItemEntry[] {entry}));
        assertEquals(Coin.COPPER_PIECE.getCost(), container.getTotalCost());
    }
}
