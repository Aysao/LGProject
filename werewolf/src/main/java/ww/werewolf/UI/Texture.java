package ww.werewolf.UI;

import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import javax.swing.plaf.synth.SynthStyleFactory;

import org.lwjgl.BufferUtils;
import static org.lwjgl.stb.STBImage.*;
import org.lwjgl.stb.*;

public class Texture {
    private int texID;

    public Texture(int WIDTH, int HEIGHT, String path) {

        ByteBuffer imageBuffer = loadImageFromFile(path);
        texID = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texID);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, WIDTH, HEIGHT, 0, GL_RGBA, GL_UNSIGNED_BYTE, imageBuffer);
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    private static ByteBuffer loadImageFromFile(String path) {
        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1);
        System.out.println("lancement de récupération d'image stbi");
        ByteBuffer image = stbi_load(path, width, height, channels, 4);
        if (image == null) {
            throw new RuntimeException("Failed to load image: " + STBImage.stbi_failure_reason());
        }
        int imageWidth = width.get(0);
        int imageHeight = height.get(0);

        // Retourne les données de l'image dans un ByteBuffer
        ByteBuffer textureData = BufferUtils.createByteBuffer(imageWidth * imageHeight * 4);
        System.out.println("buffer texture crée");
        textureData.put(image);
        textureData.flip();
        STBImage.stbi_image_free(image);
        System.out.println("Texture récupéré");
        return textureData;

    }

    public void setTexID(int texID) {
        this.texID = texID;
    }

    public int getTexID() {
        return texID;
    }

}
