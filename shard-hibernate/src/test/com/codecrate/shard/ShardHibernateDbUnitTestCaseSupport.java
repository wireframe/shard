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
package com.codecrate.shard;

import java.io.FileInputStream;
import java.sql.Connection;

import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.codecrate.dbunit.HsqldbDataTypeFactory;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public abstract class ShardHibernateDbUnitTestCaseSupport extends DatabaseTestCase {
    private SessionFactory sessionFactory;
    private Session session;
    private Connection connection;
    private ClassPathXmlApplicationContext context;
    
    public ShardHibernateDbUnitTestCaseSupport(String name) throws Exception {
        super(name);
        String[] paths = {"/shard-hibernate-context.xml"}; 
        context = new ClassPathXmlApplicationContext(paths);
        sessionFactory = (SessionFactory) context.getBean("sessionFactory");
        session = sessionFactory.openSession();
        connection = session.connection();
        connection.setAutoCommit(true);
    }

    protected IDatabaseConnection getConnection() throws Exception {
        DatabaseConnection conn = new DatabaseConnection(connection);
        conn.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());
        return conn;
    }
    
    protected ApplicationContext getContext() {
        return context;
    }

    protected IDataSet getDataSet() throws Exception {
        return new XmlDataSet(new FileInputStream(getDataSetPath()));
    }
    
    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    protected abstract String getDataSetPath();
}