package ww.werewolf.core.enumCore;

public enum AssetsPath {
    FONTS("fontsAtlas", "Assets\\font",512,192),
    CARDS("cardsAtlas", "Assets\\Card",600,900),
    BACKGROUND("backgroundAtlas", "Assets\\Background",1920,1080);

    public final String atlasName;
    public final String path;
    public final int widthItem;
    public final int heightItem;

    private AssetsPath(String atlasName, String path, int widthItem, int heightItem) {
        this.atlasName = atlasName;
        this.path = path;
        this.widthItem = widthItem;
        this.heightItem = heightItem;
    }

    
}
