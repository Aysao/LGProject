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
    private final Map<String, Texture> textureMap = new HashMap<>();
    // Charge ou récupère la texture si elle est déjà en mémoire


    public TextureManager(Shader2D shader){
        loadTextures(shader);
    }

    public Texture getTexture(String name) {
        return textureMap.get(name);
    }

    private void loadTextures(Shader2D shader){
        List<ImageData> imageToTexture = getAtlasList();
        for(ImageData atlasImage : imageToTexture){
            System.out.println("Mapping of " + atlasImage.getName() + "...");
            textureMap.put(atlasImage.getName(), new Texture(atlasImage));
        }
        bindAllAtlases(shader);
        System.out.println("Textures mapped to shaders");

    }

    public void bindAllAtlases(Shader2D shader){
        for(AssetsPath atlas : AssetsPath.values()){
            if(textureMap.get(atlas.atlasName) != null){
                textureMap.get(atlas.atlasName).loadTexture(atlas.textureSlot, shader);
            }
        }
    }

    public void activeTexture(String name, Shader2D shader){
        textureMap.get(name).activate(shader);
    }

    private List<ImageData> getAtlasList(){
        List<ImageData> result = new ArrayList<ImageData>();

        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer width = stack.mallocInt(1);
            IntBuffer height = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            for(AssetsPath assetsEnum : AssetsPath.values()){

                List<ImageData> imagesFromFolder = new ArrayList<>();
                System.out.println("Starting reading folder");
                for(File f : getFilesFromFolder(assetsEnum.path)){
                    System.out.println("reading file " + f.getName());
                    // Charger l'image en mémoire
                    ByteBuffer image = STBImage.stbi_load(f.getAbsolutePath(), width, height, channels, assetsEnum.channels);
                    if (image == null) {
                        throw new IllegalStateException("Failed to load texture file: " + f.getAbsolutePath());
                    }
                    ByteBuffer copy = BufferUtils.createByteBuffer(width.get(0) * height.get(0) * channels.get(0));
                    copy.put(image);
                    copy.flip(); 
                    ImageData imageFromFile = new ImageData(copy, width.get(0), height.get(0), f.getName());
                    imagesFromFolder.add(imageFromFile);
                    image.position(0);
                    STBImage.stbi_image_free(image);
                }
                if(imagesFromFolder.size() > 0 ){
                    ImageData assetImageData = new ImageData(null, 0, 0, assetsEnum.atlasName);
                    assetImageData.mergeToGenerateAtlas(imagesFromFolder, assetsEnum.widthItem, assetsEnum.heightItem);
                    System.out.println("files merged");
                    result.add(assetImageData);
                }
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
    public void cleanup() {
        for (Texture tex : textureMap.values()) {
            tex.cleanUp();
        }
        textureMap.clear();
    }
}