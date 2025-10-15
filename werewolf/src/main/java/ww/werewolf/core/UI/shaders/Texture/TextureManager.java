package ww.werewolf.core.UI.shaders.Texture;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import ww.werewolf.core.UI.shaders.Shader2D;
import ww.werewolf.core.enumCore.AssetsPath;

public class TextureManager {
    private static final int MAX_TEXTURE_UNITS = 32; // Limite OpenGL standard
    private final Map<String, TextureSlot> textureSlots = new HashMap<>();
    private final boolean[] usedUnits = new boolean[MAX_TEXTURE_UNITS];

    // Charge ou récupère la texture si elle est déjà en mémoire
    public Texture getTexture(String path) {
        if (textureSlots.containsKey(path)) {
            return textureSlots.get(path).texture;
        }

        // Si la texture n'est pas encore chargée, on la charge et on lui assigne une unité libre
        Texture texture = new Texture(path);
        int unit = findFreeUnit();
        if (unit == -1) {
            throw new IllegalStateException("Aucune unité de texture libre !");
        }

        usedUnits[unit] = true;
        textureSlots.put(path, new TextureSlot(texture, unit));
        return texture;
    }

    // Lier une texture à une unité et l'envoyer au shader
    public void bindTexture(String path, Shader2D shader, String uniformName) {
        TextureSlot slot = textureSlots.get(path);
        if (slot == null) {
            throw new IllegalStateException("Texture non chargée: " + path);
        }
        slot.texture.bind(slot.unit);
        slot.texture.sendToShader(slot.unit, shader, uniformName);
    }

    // Libérer toutes les textures utilisées
    public void unbindAll() {
        for (TextureSlot slot : textureSlots.values()) {
            slot.texture.unbind(); // Unbind de chaque texture sur l'unité active
        }
    }

    // Libérer une texture spécifique et son unité
    public void freeTexture(String path) {
        TextureSlot slot = textureSlots.get(path);
        if (slot != null) {
            slot.texture.cleanUp();  // Appeler cleanup() pour détruire la texture dans OpenGL
            usedUnits[slot.unit] = false; // Libérer l'unité de texture
            textureSlots.remove(path); // Retirer la texture du gestionnaire
        }
    }

    // Trouver une unité de texture libre
    private int findFreeUnit() {
        for (int i = 0; i < MAX_TEXTURE_UNITS; i++) {
            if (!usedUnits[i]) return i;
        }
        return -1; // Pas d'unité libre
    }

    private List<ImageData> getImageList(){
        List<ImageData> result = new ArrayList<ImageData>();



        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer width = stack.mallocInt(1);
            IntBuffer height = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            for(AssetsPath assetsEnum : AssetsPath.values()){

                List<ImageData> imagesFromFolder = new ArrayList<>();
                for(File f : getFilesFromFolder(assetsEnum.path)){
                    // Charger l'image en mémoire
                    ByteBuffer image = STBImage.stbi_load(f.getAbsolutePath(), width, height, channels, 4);
                    if (image == null) {
                        throw new IllegalStateException("Failed to load texture file: " + AssetsPath.FONTS.path);
                    }
                    ByteBuffer copy = BufferUtils.createByteBuffer(width.get(0) * height.get(0) * 4);
                    copy.put(image);
                    copy.flip(); 
                    ImageData imageFromFile = new ImageData(copy, width.get(0), height.get(0), f.getName());
                    imagesFromFolder.add(imageFromFile);
                    STBImage.stbi_image_free(image);
                }
                ImageData assetImageData = new ImageData(null, 0, 0, assetsEnum.name());
                assetImageData.mergeToGenerateAtlas(imagesFromFolder, assetsEnum.widthItem, assetsEnum.heightItem);
                result.add(assetImageData);
            }
        }
        return result;
    }

    private List<File> getFilesFromFolder(String folderPath){
        File folderFile = new File(folderPath);
        File[] files = folderFile.listFiles();
        List<File> result = new ArrayList<>();
        if(files != null){
            for(File f : files){
                if(f.isFile()){
                    result.add(f);
                }
            }
        }
        return result;
    }

    // Classe interne pour représenter une texture et son unité de texture
    private static class TextureSlot {
        final Texture texture;
        final int unit;

        TextureSlot(Texture texture, int unit) {
            this.texture = texture;
            this.unit = unit;
        }
    }

}