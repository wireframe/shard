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
public class DefaultCharacterBio implements CharacterBio {
    private String name;
    private Gender gender;
    private String backstory;
    private Image image;
    private String height;
    private String weight;
    private Color hairColor;
    private Color eyeColor;
    
    /**
     * @return Returns the backstory.
     */
    public String getBackstory() {
        return backstory;
    }
    /**
     * @param backstory The backstory to set.
     */
    public void setBackstory(String backstory) {
        this.backstory = backstory;
    }
    /**
     * @return Returns the eyeColor.
     */
    public Color getEyeColor() {
        return eyeColor;
    }
    /**
     * @param eyeColor The eyeColor to set.
     */
    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }
    /**
     * @return Returns the gender.
     */
    public Gender getGender() {
        return gender;
    }
    /**
     * @param gender The gender to set.
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    /**
     * @return Returns the hairColor.
     */
    public Color getHairColor() {
        return hairColor;
    }
    /**
     * @param hairColor The hairColor to set.
     */
    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }
    /**
     * @return Returns the height.
     */
    public String getHeight() {
        return height;
    }
    /**
     * @param height The height to set.
     */
    public void setHeight(String height) {
        this.height = height;
    }
    /**
     * @return Returns the image.
     */
    public Image getImage() {
        return image;
    }
    /**
     * @param image The image to set.
     */
    public void setImage(Image image) {
        this.image = image;
    }
    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return Returns the weight.
     */
    public String getWeight() {
        return weight;
    }
    /**
     * @param weight The weight to set.
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }
}
