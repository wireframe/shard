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
package com.codecrate.shard.ability;

import java.io.File;
import java.io.FileInputStream;

import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateAbilityScoreDaoTest extends DatabaseTestCase {
    private SessionFactory sessionFactory;
    private Session session;
    
    public HibernateAbilityScoreDaoTest(String name) throws Exception {
        super(name);
        File file = new File("/home/rsonnek/Projects/shard/shard-core/target/generated-source/java/hibernate.cfg.xml");
        sessionFactory = new Configuration().configure(file).buildSessionFactory();
        session = sessionFactory.openSession();
    }

    protected IDatabaseConnection getConnection() throws Exception {
        return new DatabaseConnection(session.connection());
    }

    protected IDataSet getDataSet() throws Exception {
        return new XmlDataSet(new FileInputStream("/home/rsonnek/Projects/shard/shard-core/src/data/SHA_ABILITY_POINT_COST-data.xml"));
    }
//    
//    public void tearDown() throws Exception {
//        sessionFactory.close();
//    }
    
    public void testLookupOfValidScore() throws Exception {
        HibernateAbilityScoreDao dao = new HibernateAbilityScoreDao(session);
        int pointCost = dao.getPointCost(10);
        assertEquals(-8, pointCost);
    }
}