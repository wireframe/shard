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

import com.codecrate.shard.ability.AbilityScoreContainer;
import com.codecrate.shard.equipment.ItemContainer;
import com.codecrate.shard.race.RacialSize;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class InventoryWeightEncumberance implements Encumberance {

    private Encumberance encumberance;

    public InventoryWeightEncumberance(AbilityScoreContainer abilities, ItemContainer inventory, RacialSize size, EncumberanceDao dao) {
        encumberance = dao.getEncumberance(abilities, inventory, size);
    }
    /**
     * @return
     */
    public int getArmorCheckModifier() {
        return encumberance.getArmorCheckModifier();
    }
    /**
     * @return
     */
    public int getMaxDexterityModifier() {
        return encumberance.getMaxDexterityModifier();
    }
    /**
     * @return
     */
    public String getName() {
        return encumberance.getName();
    }
}
