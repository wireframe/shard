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

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

public class DefaultSavingThrowContainerTest extends TestCase {

	public void testGetSavingThrowReturnsSameObject() {
		SavingThrowEntry entry = new SavingThrowEntry(DefaultSavingThrow.FORTITUDE);
		Map saves = new HashMap();
		saves.put(DefaultSavingThrow.FORTITUDE, entry);
		
		SavingThrowContainer container = new SavingThrowContainer(saves);
		assertSame(entry, container.getFortitudeSavingThrow());
		assertSame(entry, container.getSavingThrowEntry(DefaultSavingThrow.FORTITUDE));
	}
}
