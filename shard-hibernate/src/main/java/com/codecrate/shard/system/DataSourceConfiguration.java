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
package com.codecrate.shard.system;

import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.codecrate.shard.io.AutoCreateDirectoriesFile;

public class DataSourceConfiguration {
    private static final Log LOG = LogFactory.getLog(DataSourceConfiguration.class);
    private static final String DATASOURCE_PREFIX = "jdbc:hsqldb:";

    private final String dataSourceUrl;

    public DataSourceConfiguration(File applicationWorkingDirectory) throws IOException {
        File databaseDirectory = new AutoCreateDirectoriesFile(applicationWorkingDirectory, "data");

        dataSourceUrl = DATASOURCE_PREFIX + databaseDirectory.getCanonicalPath() + "/" + "db";
        LOG.info("Using datasource: " + dataSourceUrl);
    }

    public String getDataSourceUrl() {
        return dataSourceUrl;
    }
}
