package ww.werewolf.UI.shaders;

import org.joml.Vector2f;
import org.joml.Vector3f;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import org.lwjgl.opengl.GL20;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import org.lwjgl.opengl.GL33;

import ww.werewolf.UI.Simple2DShader;

public class ButtonMesh extends Mesh {
    private float[] vertices;
    private int[] indices;

    public ButtonMesh() {
        float[] vertices = {
            // Position (x, y)
            -0.5f,  0.5f, // top-left
            -0.5f, -0.5f, // bottom-left
             0.5f, -0.5f, // bottom-right
             0.5f,  0.5f  // top-right
        };
        
        int[] indices = {
            0, 1, 2,
            2, 3, 0
        };
    
        init(vertices, indices);
    }
    public void drawButton(Simple2DShader shader, Vector2f offset, Vector2f size, Vector3f color) {
        System.out.println("Drawing at offset: " + offset + ", size: " + size);
        shader.setUniform("color", color);
    
        glBindVertexArray(vao);
    
        // On s'assure d'activer les emplacements d'attributs
        GL20.glEnableVertexAttribArray(0); // in_position
        glDisableVertexAttribArray(1); // désactive pour dire "valeur constante"
        glDisableVertexAttribArray(2);
        // Pas besoin d'activer 1 et 2 car on ne passe pas un buffer, mais une valeur constante
    
        // Spécifie une valeur constante pour chaque vertex du draw
        GL20.glVertexAttrib2f(1, offset.x, offset.y); // offset
        GL20.glVertexAttrib2f(2, size.x, size.y);     // size
    
        // Important : désactive le divisor au cas où tu avais tenté l’instancing
        GL33.glVertexAttribDivisor(1, 0);
        GL33.glVertexAttribDivisor(2, 0);
    
        glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0);
    
        glBindVertexArray(0);
    }

    
}
