package ww.werewolf.core.UI.shaders.Texture;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.BufferUtils;

public class ImageData {
    private ByteBuffer pixels;
    private int width, height;
    private int widthCount, heightCount;
    private String name;
    private int channels;
    private Map<String, PositionUV> uvMap = new HashMap<>();

    public ImageData(ByteBuffer pixels, int width, int height, String name, int channels){
        this.pixels = pixels;
        this.width = width;
        this.height = height;
        this.name = name;
        this.channels = channels;
    }


    private void atlasGenerateSize(int itemNumber, int widthSingleItem, int heightSingleItem){
        int resultWidth = widthSingleItem;
        int resultHeight = heightSingleItem;
        this.widthCount = 1;
        this.heightCount = 1;


        for(int i = 1; i < itemNumber;i++){
            if(resultHeight > resultWidth){
                resultWidth += widthSingleItem;
                widthCount++;
            }
            else {
                resultHeight += heightSingleItem;
                heightCount++;
            }
        }

        this.width = resultWidth;
        this.height = resultHeight;

    }

    public void mergeToGenerateAtlas(List<ImageData> images, int widthSingleItem, int heightSingleItem) {
        // Vérifie le nombre de canaux de la première image (base)
        int channels = 4; // On sort toujours en RGBA (sécurité)
        atlasGenerateSize(images.size(), widthSingleItem, heightSingleItem);
        pixels = BufferUtils.createByteBuffer(width * height * channels);

        int index = 0;

        for (ImageData im : images) {
            int col = index % widthCount;
            int row = index / widthCount;

            int offsetX = col * widthSingleItem;
            int offsetY = row * heightSingleItem;

            int srcChannels = im.channels > 0 ? im.channels : 4; // par défaut 4

            for (int y = 0; y < im.height; y++) {
                for (int x = 0; x < im.width; x++) {
                    int srcIndex = (y * im.width + x) * srcChannels;
                    int destIndex = ((offsetY + y) * width + (offsetX + x)) * 4;

                    byte r = 0, g = 0, b = 0, a = (byte)255;

                    if (srcChannels == 1) {
                        // Exemple : fonts -> alpha dans le canal rouge
                        byte val = im.pixels.get(srcIndex);
                        r = g = b = (byte)255; // texte blanc
                        a = val; // intensité du canal unique comme alpha
                    } else if (srcChannels == 3) {
                        r = im.pixels.get(srcIndex);
                        g = im.pixels.get(srcIndex + 1);
                        b = im.pixels.get(srcIndex + 2);
                        a = (byte)255;
                    } else if (srcChannels >= 4) {
                        r = im.pixels.get(srcIndex);
                        g = im.pixels.get(srcIndex + 1);
                        b = im.pixels.get(srcIndex + 2);
                        a = im.pixels.get(srcIndex + 3);
                    }

                    pixels.put(destIndex, r);
                    pixels.put(destIndex + 1, g);
                    pixels.put(destIndex + 2, b);
                    pixels.put(destIndex + 3, a);
                }
            }

            uvMap.put(im.name, new PositionUV(offsetX, offsetY, widthSingleItem, heightSingleItem, width, height));
            index++;
        }
    }


    public ByteBuffer getPixels() {
        return pixels;
    }

    public void setPixels(ByteBuffer pixels) {
        this.pixels = pixels;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidthCount() {
        return widthCount;
    }

    public int getHeightCount() {
        return heightCount;
    }

    public void setWidthCount(int widthCount) {
        this.widthCount = widthCount;
    }

    public void setHeightCount(int heightCount) {
        this.heightCount = heightCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, PositionUV> getUvMap() {
        return uvMap;
    }

    public void setUvMap(Map<String, PositionUV> uvMap) {
        this.uvMap = uvMap;
    }


    public class PositionUV{

        float uMin;
        float vMin;
        float uMax;
        float vMax;
        public PositionUV(int offsetX, int offsetY, int imgWidth, int imgHeight, int atlasWidth, int atlasHeight) {
            this.uMin = offsetX / (float) atlasWidth;
            this.vMin = offsetY / (float) atlasHeight;
            this.uMax = (offsetX + imgWidth) / (float) atlasWidth;
            this.vMax = (offsetY + imgHeight) / (float) atlasHeight;
        }
    }
}


