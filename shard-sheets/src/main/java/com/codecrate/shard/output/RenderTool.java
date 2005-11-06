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
package com.codecrate.shard.output;

/**
 * Helper tool for rendering velocity templates.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class RenderTool {
    
    public boolean isEven(int value) {
        return value % 2 == 0;
    }

    public boolean isOdd(int value) {
        return !isEven(value);
    }
    
    /**
     * gets the css class name for a table row.
     * this is heavily tied to what css is used for the output sheet.
     * @param value
     * @return
     * 
     * @TODO update to look at a property instead of being hardcoded.
     */
    public String getRowCssClassName(int value) {
    	if (isEven(value)) {
    		return "rowEven";
    	}
    	return "rowOdd";
    }
}
