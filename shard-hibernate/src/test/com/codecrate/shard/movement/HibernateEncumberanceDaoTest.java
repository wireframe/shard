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

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Connection;

import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.easymock.MockControl;

import com.codecrate.shard.ability.AbilityScore;
import com.codecrate.shard.ability.AbilityScoreContainer;
import com.codecrate.shard.ability.DefaultAbility;
import com.codecrate.shard.ability.DefaultAbilityScore;
import com.codecrate.shard.equipment.ItemEntryContainer;
import com.codecrate.shard.race.RacialSize;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateEncumberanceDaoTest extends DatabaseTestCase {
    private SessionFactory sessionFactory;
    private Session session;
    private Connection connection;
    
    public HibernateEncumberanceDaoTest(String name) throws Exception {
        super(name);
        File file = new File("src/hibernate/hibernate.cfg.xml");
        sessionFactory = new Configuration().configure(file).buildSessionFactory();
        session = sessionFactory.openSession();
        connection = session.connection();
        connection.setAutoCommit(true);
    }

    protected IDatabaseConnection getConnection() throws Exception {
        return new DatabaseConnection(connection);
    }

    protected IDataSet getDataSet() throws Exception {
        return new XmlDataSet(new FileInputStream("src/data/SHA_ENCUMBERANCE_ENTRY-data.xml"));
    }
    
    public void testLookupOfEncumberanceEntry() throws Exception {
        AbilityScore score = new DefaultAbilityScore(DefaultAbility.STRENGTH, 10, null);
        
        MockControl mockAbilities = MockControl.createControl(AbilityScoreContainer.class);
        AbilityScoreContainer abilities = (AbilityScoreContainer) mockAbilities.getMock();
        abilities.hasAbilityScore(DefaultAbility.STRENGTH);
        mockAbilities.setReturnValue(true);
        abilities.getAbilityScore(DefaultAbility.STRENGTH);
        mockAbilities.setReturnValue(score);
        mockAbilities.replay();
        
        MockControl mockInventory = MockControl.createControl(ItemEntryContainer.class);
        ItemEntryContainer inventory = (ItemEntryContainer) mockInventory.getMock();
        inventory.getTotalWeight();
        mockInventory.setReturnValue(new BigDecimal(66));
        mockInventory.replay();
        
        MockControl mockSize = MockControl.createControl(RacialSize.class);
        RacialSize size = (RacialSize) mockSize.getMock();
        size.getEncumberanceMultiplier();
        mockSize.setReturnValue(new BigDecimal("2"));
        mockSize.replay();
        
        
        session = sessionFactory.openSession();
        HibernateEncumberanceDao dao = new HibernateEncumberanceDao(session);
        Encumberance entry = dao.getEncumberance(abilities, inventory, size);
        assertEquals(DefaultEncumberance.LIGHT.getName(), entry.getName());
    }
    
    public void testNoEncumberanceCalculatedWithoutStrength() throws Exception {
        MockControl mockAbilities = MockControl.createControl(AbilityScoreContainer.class);
        AbilityScoreContainer abilities = (AbilityScoreContainer) mockAbilities.getMock();
        abilities.hasAbilityScore(DefaultAbility.STRENGTH);
        mockAbilities.setReturnValue(false);
        mockAbilities.replay();
        
        MockControl mockInventory = MockControl.createControl(ItemEntryContainer.class);
        ItemEntryContainer inventory = (ItemEntryContainer) mockInventory.getMock();
        mockInventory.replay();
        
        MockControl mockSize = MockControl.createControl(RacialSize.class);
        RacialSize size = (RacialSize) mockSize.getMock();
        mockSize.replay();
        
        session = sessionFactory.openSession();
        HibernateEncumberanceDao dao = new HibernateEncumberanceDao(session);
        Encumberance entry = dao.getEncumberance(abilities, inventory, size);
        assertNull(entry);
    }
}