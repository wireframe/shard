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

/**
 * defines an alignment.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface Alignment {
	
	/**
	 * gets the name of the alignment.
	 * ex: Lawful Good.
	 * @return
	 */
	String getName();
	
	/**
	 * gets the abbreviation for the alignment.
	 * ex: LG for Lawful Good.
	 * @return
	 */
	String getAbbreviation();
	
    boolean isLawful();
    boolean isChaotic();
    
    /**
     * returns if the alignment is neutral.
     * @return
     */
    boolean isNeutral();
    
    boolean isGood();
    boolean isEvil();
    
    boolean isSame(Alignment alignment);
}
