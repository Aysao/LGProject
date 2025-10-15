package ww.werewolf.core.UI.shaders.Mesh;

import org.joml.Vector2f;
import org.joml.Vector3f;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;  // Pour glGenBuffers, glBindBuffer, glBufferData, etc.
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;  // Pour glGenBuffers, glBindBuffer, glBufferData, etc.
import static org.lwjgl.opengl.GL15.glBindBuffer;  // Pour glGenBuffers, glBindBuffer, glBufferData, etc.
import static org.lwjgl.opengl.GL15.glBufferData;  // Pour glGenBuffers, glBindBuffer, glBufferData, etc.
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;  // Pour glEnableVertexAttribArray, glVertexAttribPointer
import org.lwjgl.opengl.GL20;  // Pour glEnableVertexAttribArray, glVertexAttribPointer
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;  // Pour glDrawElements, GL_TRIANGLES, etc.
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;  // Pour glDrawElements, GL_TRIANGLES, etc.
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;  // Pour glDrawElements, GL_TRIANGLES, etc.
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
import org.lwjgl.opengl.GL33;

import ww.werewolf.core.UI.shaders.Shader2D;


public abstract class Mesh {
    protected int vao, vbo, ebo;
    protected int vertexCount;

    public void init(float[] vertices, int[] indices) {
        this.vertexCount = indices.length;

        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

        ebo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);

        // Attributs de position (location 0)
        glVertexAttribPointer(0, 2, GL_FLOAT, false, 2 * Float.BYTES, 0);
        glEnableVertexAttribArray(0);

        glBindVertexArray(0);
    }

    public void bind() {
        glBindVertexArray(vao);
    }

    public void unbind() {
        glBindVertexArray(0);
    }

    public void drawNotTextured(Shader2D shader, Vector2f offset, Vector2f size, Vector3f color) {
        shader.setUniform("useTexture", false);
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
    
        glDrawElements(GL_TRIANGLES, vertexCount, GL_UNSIGNED_INT, 0);

        glBindVertexArray(0);
    }

    public void draw(Shader2D shader, Vector2f offset, Vector2f size, Vector2f uvMin, Vector2f uvMax) {
        shader.setUniform("useTexture", true);
        shader.setUniform("uvMin", uvMin);
        shader.setUniform("uvMax", uvMax);
    
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
    
        glDrawElements(GL_TRIANGLES, vertexCount, GL_UNSIGNED_INT, 0);

        glBindVertexArray(0);
    }

    public void cleanup() {
        glDeleteVertexArrays(vao);
        glDeleteBuffers(vbo);
        glDeleteBuffers(ebo);
    }
}