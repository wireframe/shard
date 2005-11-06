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
package com.codecrate.shard.dice;

/**
 * Definition of a Dice object.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface Dice {

    /**
     * gets the max value that can be attained for the roll.
     * @return int value
     */
    int getMaxValue();
    
    /**
     * gets the min value that can be attained for the roll.
     * @return min value.
     */
    int getMinValue();
    
    /**
     * rolls the dice.
     * @return a number between the min and max value.
     */
    int roll();
}
