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
package com.codecrate.shard.race;

import com.codecrate.shard.Identifiable;


public class HibernateLanguage implements Language, Identifiable {
    
	private String name;
	private String alphabet;

	/**
	 * hibernate constructor.
	 */
	public HibernateLanguage() {
	}
	
	public HibernateLanguage(String name, String alphabet) {
		this.name = name;
		this.alphabet = alphabet;
	}

	public String toString() {
		return name;
	}
	
	public String getId() {
	    return name;
	}
	
	public void setId(String id) {
	    this.name = id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAlphabet() {
		return alphabet;
	}
	
	public void setAlphabet(String alphabet) {
	    this.alphabet = alphabet;
	}
}
