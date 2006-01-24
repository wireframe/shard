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

import org.springframework.richclient.application.ApplicationLauncher;

/**
 *
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class ShardPhoenix {

    private static final String[] CONTEXT_FILE_NAMES = new String[] {
                        "/shard-phoenix-context.xml"
    					, "/shard-transfer-context.xml"
                        , "/shard-hibernate-context.xml"
                        , "/shard-datasource.xml"
                };
    private static final String STARTUP_CONTEXT_FILE_NAME = "/shard-phoenix-startup-context.xml";

    public static void main(String[] args) {
        try {
            new ApplicationLauncher(STARTUP_CONTEXT_FILE_NAME, CONTEXT_FILE_NAMES);
        } catch (Exception e) {
        	System.out.println("Error launching application: " + e);
            e.printStackTrace();
            System.exit(1);
        }
    }
}
