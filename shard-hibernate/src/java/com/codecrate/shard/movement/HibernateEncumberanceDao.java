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

import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.codecrate.shard.ability.AbilityScore;
import com.codecrate.shard.ability.AbilityScoreContainer;
import com.codecrate.shard.ability.DefaultAbility;
import com.codecrate.shard.equipment.ItemEntryContainer;
import com.codecrate.shard.race.RacialSize;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateEncumberanceDao implements EncumberanceDao {
    private static final Log LOG = LogFactory.getLog(HibernateEncumberanceDao.class);
    
    private final Session session;
    
    public HibernateEncumberanceDao(Session session) {
        this.session = session;
    }
    
    public Encumberance getEncumberance(AbilityScoreContainer abilities, ItemEntryContainer inventory, RacialSize size) {
        if (!abilities.hasAbilityScore(DefaultAbility.STRENGTH)) {
            LOG.warn("No Strength ability score found.  Encumberance can't be calculated.");
            return null;
        }
        AbilityScore strength = abilities.getAbilityScore(DefaultAbility.STRENGTH);
        int score = strength.getModifiedValue();
        int effectiveWeight = inventory.getTotalWeight().divide(size.getEncumberanceMultiplier(), 0).intValue();
        try {
            Query query = session.createQuery("from EncumberanceEntry value where value.id = :abilityScore");
            query.setInteger("abilityScore", score);
            List values = query.list();
            if (1 == values.size()) {
                EncumberanceEntry value = (EncumberanceEntry) values.iterator().next();
                
                if (effectiveWeight <= value.getWeightLight()) {
                    return DefaultEncumberance.LIGHT;
                }
                if (effectiveWeight <= value.getWeightMedium()) {
                    return DefaultEncumberance.MEDIUM;
                }
                if (effectiveWeight <= value.getWeightHeavy()) {
                    return DefaultEncumberance.HEAVY;
                }
            }
        } catch (HibernateException e) {
            LOG.error("Unable to lookup encumberance entry.", e);
        }
        LOG.info("Unable to find encumberance entry for abilities " + abilities);
        return null;
    }

}
