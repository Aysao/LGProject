package ww.werewolf.core.UI.Component;

import org.joml.Vector2f;
import org.joml.Vector3f;

import ww.werewolf.core.UI.shaders.Mesh.BoxMesh;
import ww.werewolf.core.UI.shaders.Shader2D;
import ww.werewolf.core.UI.shaders.Texture.TextureManager;

public class UIButton {
    private Vector2f offset;
    private Vector2f size;
    private Vector3f color;
    private TextLabel textButtonLabel;
    private Runnable onClick;

    public UIButton(Vector2f offset, Vector2f size, Vector3f color, String text, Runnable onClick) {
        this.offset = offset;
        this.size = size;
        this.color = color;
        this.onClick = onClick;
        this.textButtonLabel = new TextLabel(text, offset,1.0f);
    }

    public void draw(Shader2D shader, BoxMesh buttonMesh, TextureManager textureManager) {
        buttonMesh.drawNotTextured(shader, offset, size, color);
        textButtonLabel.render(shader,buttonMesh, textureManager);
    }

    public void update(double mouseX, double mouseY, boolean mousePressed) {
        float left   = offset.x - size.x / 2f;
        float right  = offset.x + size.x / 2f;
        float top    = offset.y - size.y / 2f;
        float bottom = offset.y + size.y / 2f;

        if (mousePressed && mouseX >= left && mouseX <= right && mouseY >= top && mouseY <= bottom) {
            if (onClick != null) {
                onClick.run();
            }
        }
    }
}