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
package com.codecrate.shard.character;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;

import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

import com.codecrate.shard.race.HibernateRace;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateAgeCategoryDaoTest extends DatabaseTestCase {
    private SessionFactory sessionFactory;
    private Session session;
    private Connection connection;
    
    public HibernateAgeCategoryDaoTest(String name) throws Exception {
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
        return new XmlDataSet(new FileInputStream("src/data/SHA_RACE_AGE-data.xml"));
    }
    
    public void testLookupOfAgeCategory() throws Exception {
        session = sessionFactory.openSession();
        HibernateAgeCategoryDao dao = new HibernateAgeCategoryDao(session);
        AgeCategory ageCategory = dao.getAgeCategory(15, HibernateRace.HUMAN);
        assertEquals(CummulativeAgeCategory.ADULT, ageCategory);
    }
}