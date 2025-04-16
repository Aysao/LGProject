package ww.werewolf.UI.shaders.Mesh;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;  // Pour glGenBuffers, glBindBuffer, glBufferData, etc.
import static org.lwjgl.opengl.GL15.glBufferData;  // Pour glGenBuffers, glBindBuffer, glBufferData, etc.
import static org.lwjgl.opengl.GL15.glDeleteBuffers;  // Pour glGenBuffers, glBindBuffer, glBufferData, etc.
import static org.lwjgl.opengl.GL15.glGenBuffers;  // Pour glGenBuffers, glBindBuffer, glBufferData, etc.
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;  // Pour glEnableVertexAttribArray, glVertexAttribPointer
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;  // Pour glEnableVertexAttribArray, glVertexAttribPointer
import static org.lwjgl.opengl.GL30.glBindVertexArray;  // Pour glDrawElements, GL_TRIANGLES, etc.
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;  // Pour glDrawElements, GL_TRIANGLES, etc.
import static org.lwjgl.opengl.GL30.glGenVertexArrays;  // Pour glDrawElements, GL_TRIANGLES, etc.


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

    public void draw() {
        glDrawElements(GL_TRIANGLES, vertexCount, GL_UNSIGNED_INT, 0);
    }

    public void cleanup() {
        glDeleteVertexArrays(vao);
        glDeleteBuffers(vbo);
        glDeleteBuffers(ebo);
    }
}