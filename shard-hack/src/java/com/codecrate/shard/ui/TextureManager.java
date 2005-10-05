package com.codecrate.shard.ui;

import com.jme.image.Texture;

public class TextureManager {

    private final ClassLoader classLoader;

    public TextureManager() {
        this.classLoader = TextureManager.class.getClassLoader();
    }

    public Texture loadTexture(String name) {
        return com.jme.util.TextureManager.loadTexture(
                classLoader.getResource(name),
                Texture.MM_LINEAR,
                Texture.FM_LINEAR);
    }
}
