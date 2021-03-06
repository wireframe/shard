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
package com.codecrate.shard.ui;


/**
 *
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class ShardPhoenix {

    private static final String[] CONTEXT_FILE_NAMES = new String[] {
    	"/shard-gui-core-context.xml"
    	, "/shard-phoenix-context.xml"
    	, "/shard-hibernate-context.xml"
    	, "/shard-transfer-pcgen-context.xml"
    	, "/shard-datasource.xml"
    };
    private static final String STARTUP_CONTEXT_FILE_NAME = "/shard-phoenix-startup-context.xml";

    public static void main(String[] args) {
    	new ShardApplicationLauncher(STARTUP_CONTEXT_FILE_NAME, CONTEXT_FILE_NAMES);
    }
}
