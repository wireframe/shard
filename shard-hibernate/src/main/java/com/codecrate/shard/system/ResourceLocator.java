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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.codecrate.shard.io.AutoCreateDirectoriesFile;

public class ResourceLocator {
    private static final Log LOG = LogFactory.getLog(ResourceLocator.class);

    private static final String ENV_USER_HOME= "user.home";
    private static final String APPLICATION_WORKING_DIRECTORY = ".shard";

    private final File applicationWorkingDirectory;

    public ResourceLocator() {
        File userHome = new File(System.getProperty(ENV_USER_HOME));
        applicationWorkingDirectory = new AutoCreateDirectoriesFile(userHome, APPLICATION_WORKING_DIRECTORY);
        LOG.info("Using " + applicationWorkingDirectory + " as application working directory.");
    }

    public File getApplicationWorkingDirectory() {
        return applicationWorkingDirectory;
    }
}
