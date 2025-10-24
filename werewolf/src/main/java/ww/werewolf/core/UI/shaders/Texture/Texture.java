package ww.werewolf.core.UI.shaders.Texture;

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

import ww.werewolf.core.UI.shaders.Shader2D;
import ww.werewolf.core.UI.shaders.Texture.ImageData.PositionUV;

public class Texture {
    private final int textureID;
    private final ImageData textureImage;
    private int textureSlot;

    public Texture(ImageData image) {
        textureImage = image;
        this.textureID = glGenTextures();
    }

    public void loadTexture(int slot, Shader2D shader) {

        glActiveTexture(GL_TEXTURE0 + slot);  // Active l'unité de texture spécifiée
        glBindTexture(GL_TEXTURE_2D, this.textureID);
        

        // Spécifier les paramètres de la texture
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);

        // Charger la texture dans OpenGL
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, textureImage.getWidth(), textureImage.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, textureImage.getPixels());
        glGenerateMipmap(GL_TEXTURE_2D);

        bind(slot, shader);
    }

    public void bind(int slot, Shader2D shader) {
        this.textureSlot = slot;
        glActiveTexture(GL_TEXTURE0 + slot);  // Active l'unité de texture spécifiée
        glBindTexture(GL_TEXTURE_2D, this.textureID);
        System.out.println(textureImage.getName());
        shader.setUniform(textureImage.getName(), slot);

        System.out.println("Texture : " + textureImage.getName() + " got bind to the slot " + slot + " from textureID " + this.textureID );
    }

    public void activate(Shader2D shader){
        glActiveTexture(GL_TEXTURE0 + this.textureSlot);
        shader.setUniform("textureType", this.textureSlot);
    }

    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public void cleanUp() {
        glDeleteTextures(textureID);  // Supprimer la texture d'OpenGL
    }

    public String getTextureName(){
        return textureImage.getName();
    }

    public PositionUV getUVFromImageName(String imageName){
        return this.textureImage.getUvMap().get(imageName);
    }


}