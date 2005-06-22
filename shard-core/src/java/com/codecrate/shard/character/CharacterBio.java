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

import java.awt.Color;
import java.awt.Image;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface CharacterBio {
    /**
     * @return Returns the backstory.
     */
    String getBackstory();
    /**
     * @return Returns the eyeColor.
     */
    Color getEyeColor();
    /**
     * @return Returns the gender.
     */
    Gender getGender();
    /**
     * @return Returns the hairColor.
     */
    Color getHairColor();
    /**
     * @return Returns the height.
     */
    String getHeight();
    /**
     * @return Returns the image.
     */
    Image getImage();
    /**
     * @return Returns the name.
     */
    String getName();
    /**
     * @return Returns the weight.
     */
    String getWeight();
}
