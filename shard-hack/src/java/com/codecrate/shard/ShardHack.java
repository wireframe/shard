package com.codecrate.shard;

import com.codecrate.shard.ui.TextureManager;
import com.jme.app.SimpleGame;
import com.jme.bounding.BoundingBox;
import com.jme.image.Texture;
import com.jme.math.Vector3f;
import com.jme.scene.shape.Sphere;
import com.jme.scene.state.TextureState;

public class ShardHack extends SimpleGame {

    private final TextureManager textureManager;

    public ShardHack() {
        super();
        setDialogBehaviour(NEVER_SHOW_PROPS_DIALOG);
        this.textureManager = new TextureManager();
    }

    public static void main(String[] args) {
        new ShardHack().start();
    }

    protected void simpleInitGame() {
        display.setTitle("Shard Hack");

        Texture texture = textureManager.loadTexture("MetallicTile.jpg");

        TextureState ts = display.getRenderer().createTextureState();
        ts.setEnabled(true);
        ts.setTexture(texture);

        Sphere s = new Sphere("Sphere", 30, 30, 25);
        s.setLocalTranslation(new Vector3f(0, 0, -40));
        s.setModelBound(new BoundingBox());
        s.updateModelBound();
        s.setRenderState(ts);

        rootNode.attachChild(s);
    }
}
