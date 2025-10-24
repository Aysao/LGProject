package ww.werewolf.core.UI.shaders.Texture;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joml.Vector2f;
import org.lwjgl.BufferUtils;

public class ImageData {
    private ByteBuffer pixels;
    private int width, height;
    private int widthCount, heightCount;
    private String name;
    private Map<String, PositionUV> uvMap = new HashMap<>();

    public ImageData(ByteBuffer pixels, int width, int height, String name){
        this.pixels = pixels;
        this.width = width;
        this.height = height;
        this.name = name;
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

    public void mergeToGenerateAtlas(List<ImageData> images, int widthSingleItem, int heightSingleItem){
        atlasGenerateSize(images.size(), widthSingleItem, heightSingleItem);
        pixels = BufferUtils.createByteBuffer(width * height * 4);
        int index = 0;

        for(ImageData im : images){
            System.out.println("Merging " + im.getName() + " to the atlas " + this.name);
            int col = index % widthCount;
            int row = index / widthCount;
            
            int offsetX = col * widthSingleItem;
            int offsetY = row * heightSingleItem;

            for (int y = 0; y < im.height; y++) {
                for (int x = 0; x < im.width; x++) {
                    int srcIndex = (y * im.width + x) * 4;
                    int destIndex = ((offsetY + y) * width + (offsetX + x)) * 4;

                    pixels.put(destIndex, im.pixels.get(srcIndex));
                    pixels.put(destIndex + 1, im.pixels.get(srcIndex + 1));
                    pixels.put(destIndex + 2, im.pixels.get(srcIndex + 2));
                    pixels.put(destIndex + 3, im.pixels.get(srcIndex + 3));
                }
            }
            index++;
            
            int lastPoint = im.name.lastIndexOf(".");
            uvMap.put(im.name.substring(0, lastPoint), new PositionUV(offsetX, offsetY, widthSingleItem, heightSingleItem, width, height));
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

        public Vector2f getUVmin(){
            return new Vector2f(this.uMin, this.vMin);
        }

        public Vector2f getUVMax(){
            return new Vector2f(this.uMax, this.vMax);
        }
    }

}


