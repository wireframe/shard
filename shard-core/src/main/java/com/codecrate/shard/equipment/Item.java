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
package com.codecrate.shard.equipment;

import java.math.BigDecimal;

import com.codecrate.shard.source.Source;

/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface Item {
	/**
	 * gets the name of the item.
	 * @return
	 */
	String getName();
	
	/**
	 * gets the weight.
	 * TODO a Weight object should be created to encapsulate unit of measure 
	 * @return
	 */
    BigDecimal getWeight();

    /**
     * gets the cost of the item.
     * @return
     */
    Money getCost();

    Source getSource();
}
