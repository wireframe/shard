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
 * Helper class for defining an alignment.
 * Alignments can be broken up into two components, (law to chaos) and
 * (good to evil).  both of these components have a positive, neutral 
 * and negative element.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class AlignmentComponent {
    public static final AlignmentComponent POSITIVE = new AlignmentComponent(Boolean.TRUE);
    public static final AlignmentComponent NEGATIVE = new AlignmentComponent(Boolean.FALSE);
    public static final AlignmentComponent NEUTRAL = new AlignmentComponent(null);

    private Boolean alignment;

    public AlignmentComponent(Boolean alignment) {
        this.alignment = alignment;
    }
   
    public boolean isPositive() {
        if (null != alignment) {
            return alignment.booleanValue();
        }
        return false;
    }

    public boolean isNegative() {
        if (null != alignment) {
            return !alignment.booleanValue();
        }
        return false;
    }
    
    public boolean isNeutral() {
        if (null != alignment) {
            return false;
        }
        return true;
    }
}
