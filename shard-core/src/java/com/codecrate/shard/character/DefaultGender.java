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

public class DefaultGender implements Gender {
	public static final Gender MALE = new DefaultGender("Male");
	public static final Gender FEMALE = new DefaultGender("Female");
	
	
	private String name;
	
	public DefaultGender(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
