package ww.werewolf.core.UI.Component;

import org.joml.Vector2f;

import ww.werewolf.core.UI.shaders.Mesh.BoxMesh;
import ww.werewolf.core.UI.shaders.Shader2D;
import ww.werewolf.core.UI.shaders.Texture.TextureManager;
import ww.werewolf.core.enumCore.AssetsPath;

public class UIBackground {


    public UIBackground(){

    }

    public void renderBackground(Shader2D shader, BoxMesh backgroundMesh ,TextureManager textureManager, int winw, int winh) {
    textureManager.activeTexture(AssetsPath.BACKGROUND.atlasName, shader);


    Vector2f screenSize = new Vector2f(winw, winh); // taille fenêtre actuelle

    // Le BoxMesh va de -0.5 à +0.5 → on le scale à la taille de la fenêtre
    Vector2f size = new Vector2f(screenSize.x, screenSize.y);
    Vector2f offset = new Vector2f(screenSize.x / 2f, screenSize.y / 2f);

    Vector2f uvMin = new Vector2f(0, 0);
    Vector2f uvMax = new Vector2f(1, 1);

    backgroundMesh.draw(shader, offset, size, uvMin, uvMax);
}
}
