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
package com.codecrate.shard.movement;

import junit.framework.TestCase;

import org.easymock.MockControl;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class InventoryWeightEncumberanceTest extends TestCase {

    public void testEncumberanceLookedupWithDao() {
        MockControl mockDao = MockControl.createControl(EncumberanceDao.class);
        EncumberanceDao dao = (EncumberanceDao) mockDao.getMock();
        dao.getEncumberance(null, null, null);
        mockDao.setReturnValue(DefaultEncumberance.LIGHT);
        mockDao.replay();
        
        InventoryWeightEncumberance encumberance = new InventoryWeightEncumberance(null, null, null, dao);
        assertEquals(DefaultEncumberance.LIGHT.getArmorCheckModifier(), encumberance.getArmorCheckModifier());
        assertEquals(DefaultEncumberance.LIGHT.getMaxDexterityModifier(), encumberance.getMaxDexterityModifier());
        assertEquals(DefaultEncumberance.LIGHT.getName(), encumberance.getName());
    }
}
