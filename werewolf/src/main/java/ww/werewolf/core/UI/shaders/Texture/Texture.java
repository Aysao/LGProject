package ww.werewolf.core.UI.shaders.Texture;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDeleteTextures;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import ww.werewolf.core.UI.shaders.Shader2D;

public class Texture {
    private final int textureID;

    public Texture(String path) {
        this.textureID = loadTexture(path);
    }

    private int loadTexture(String path) {
        // Chargement de l'image via stbImage (librairie qui permet de charger les images dans OpenGL)
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer width = stack.mallocInt(1);
            IntBuffer height = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            // Charger l'image en mémoire
            ByteBuffer image = STBImage.stbi_load(path, width, height, channels, 0);
            if (image == null) {
                throw new IllegalStateException("Failed to load texture file: " + path);
            }

            // Générer une texture OpenGL
            int textureID = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, textureID);

            // Spécifier les paramètres de la texture
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);

            // Charger la texture dans OpenGL
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width.get(0), height.get(0), 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
            glGenerateMipmap(GL_TEXTURE_2D);

            // Libérer l'image et retourner l'ID de la texture
            STBImage.stbi_image_free(image);
            return textureID;
        }
    }

    public void bind(int slot) {
        glActiveTexture(GL_TEXTURE0 + slot);  // Active l'unité de texture spécifiée
        glBindTexture(GL_TEXTURE_2D, textureID);
    }

    public void sendToShader(int textureUnit, Shader2D shader, String uniformName) {
        // Active l'unité de texture
        bind(textureUnit);

        // Passer l'unité de texture au shader en tant qu'uniforme
        shader.setUniform(uniformName, textureUnit);
    }

    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public void cleanUp() {
        glDeleteTextures(textureID);  // Supprimer la texture d'OpenGL
    }
}