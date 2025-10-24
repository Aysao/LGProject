package ww.werewolf.core.UI.Component;

import org.joml.Vector2f;

import ww.werewolf.core.UI.shaders.Mesh.Mesh;
import ww.werewolf.core.UI.shaders.Shader2D;
import ww.werewolf.core.UI.shaders.Texture.TextureManager;
import ww.werewolf.core.enumCore.Atlas;

public class TextLabel {
    private final String text;
    private final Vector2f position;
    private final float scale;
    private final int glyphWidth = 32;
    private final int glyphHeight = 32;
    private final int sheetCols = 16;
    private final int sheetRows = 6;

    public TextLabel(String text, Vector2f position, float scale) {
        this.text = text;
        int i = text.length();
        int textWidth = (int) (glyphWidth * scale) * i;

        this.position = new Vector2f(position.x - textWidth/2, position.y);
        this.scale = scale;
    }

    public void render(Shader2D shader, Mesh mesh, TextureManager textureManager) {
        Vector2f cursor = new Vector2f(position);

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int ascii = (int) c;

            // On saute les caractères non valides
            if (ascii < 32 || ascii >= 32 + sheetCols * sheetRows) 
                continue;

            int index = ascii - 32;
            int col = index % sheetCols;
            int row = index / sheetCols;


            float texW = 1.0f / sheetCols;
            float texH = 1.0f / sheetRows;

            Vector2f uvMin = new Vector2f(col * texW, row * texH);
            Vector2f uvMax = new Vector2f((col + 1) * texW, (row + 1) * texH);

            Vector2f size = new Vector2f(glyphWidth * scale, glyphHeight * scale);
            textureManager.activeTexture(Atlas.FONTS.atlasName, shader);
            mesh.draw(shader, cursor, size, uvMin, uvMax);

            cursor.x += size.x;
        }
    }
}
