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

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DBPointScoreTest extends DatabaseTestCase {
    private Connection jdbcConnection;

    public DBPointScoreTest(String name) {
        super(name);
    }

    protected IDatabaseConnection getConnection() throws Exception {
        Class.forName("org.hsqldb.jdbcDriver");
        jdbcConnection = DriverManager.getConnection(
                "jdbc:hsqldb:.", "sa", "");
        return new DatabaseConnection(jdbcConnection);
    }

    protected IDataSet getDataSet() throws Exception {
        return new XmlDataSet(new FileInputStream("/home/rsonnek/Projects/shard/shard-core/src/test/SHA_ABILITY_POINT_COST-data.xml"));
    }
    
    public void testSuccess() throws Exception {
        PreparedStatement statement = jdbcConnection.prepareStatement("select * from SHA_ABILITY_POINT_COST");
        ResultSet resultSet = statement.executeQuery();
    }
}