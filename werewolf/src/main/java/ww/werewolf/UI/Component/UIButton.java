package ww.werewolf.UI.Component;

import org.joml.Vector2f;
import org.joml.Vector3f;

import ww.werewolf.UI.Simple2DShader;
import ww.werewolf.UI.shaders.Mesh.ButtonMesh;

public class UIButton {
    private Vector2f offset;
    private Vector2f size;
    private Vector3f color;
    private Runnable onClick;

    public UIButton(Vector2f offset, Vector2f size, Vector3f color, Runnable onClick) {
        this.offset = offset;
        this.size = size;
        this.color = color;
        this.onClick = onClick;
    }

    public void draw(Simple2DShader shader, ButtonMesh buttonMesh) {
        buttonMesh.drawButton(shader, offset, size, color);
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