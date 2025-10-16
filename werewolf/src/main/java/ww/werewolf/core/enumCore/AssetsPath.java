package ww.werewolf.core.enumCore;

public enum AssetsPath {
    FONTS("fontsAtlas", "Assets\\font", 512, 192, 2, 4),
    CARDS("cardsAtlas", "Assets\\Card",600,900, 1, 4),
    BACKGROUND("backgroundAtlas", "Assets\\Background",1920,1080, 0, 4);

    public final String atlasName;
    public final String path;
    public final int widthItem;
    public final int heightItem;
    public final int textureSlot;
    public final int channels;

    private AssetsPath(String atlasName, String path, int widthItem, int heightItem, int textureSlot, int channels) {
        this.atlasName = atlasName;
        this.path = path;
        this.widthItem = widthItem;
        this.heightItem = heightItem;
        this.textureSlot = textureSlot;
        this.channels = channels;
    }

    
}
