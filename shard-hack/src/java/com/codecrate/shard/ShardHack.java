package com.codecrate.shard;

import com.codecrate.shard.ui.TextureManager;
import com.jme.app.SimpleGame;
import com.jme.bounding.BoundingSphere;
import com.jme.image.Texture;
import com.jme.math.Vector3f;
import com.jme.scene.shape.Sphere;
import com.jme.scene.state.TextureState;

public class ShardHack extends SimpleGame {

    private final TextureManager textureManager;

    public ShardHack() {
        super();
        this.textureManager = new TextureManager();

        setDialogBehaviour(NEVER_SHOW_PROPS_DIALOG);
}

    public static void main(String[] args) {
        new ShardHack().start();
    }

    protected void simpleInitGame() {
        display.setTitle("Shard Hack");

        Texture texture = textureManager.loadTexture("Monkey.jpg");

        TextureState textureState = display.getRenderer().createTextureState();
        textureState.setEnabled(true);
        textureState.setTexture(texture);

        Sphere sphere = new Sphere("Sphere", 30, 30, 25);
        sphere.setLocalTranslation(new Vector3f(0, 0, -40));
        sphere.setModelBound(new BoundingSphere());
        sphere.updateModelBound();
        sphere.setRenderState(textureState);

        rootNode.attachChild(sphere);
    }
}
