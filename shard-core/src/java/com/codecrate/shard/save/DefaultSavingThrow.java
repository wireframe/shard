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
package com.codecrate.shard.save;

public class DefaultSavingThrow implements SavingThrow {
    public static final SavingThrow REFLEX = new DefaultSavingThrow("Reflex");
	public static final SavingThrow FORTITUDE = new DefaultSavingThrow("Fortitude");
	public static final SavingThrow WILLPOWER = new DefaultSavingThrow("Willpower");
    
    private final String name;
    
    public DefaultSavingThrow(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return name.toUpperCase().substring(0, 3);
    }
}
