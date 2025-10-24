package ww.werewolf.core.enumCore;

public enum ImageAssets {
    FONT_ASSET("font_spritesheet"),
    BLOODFOREST_BACKGROUND("BloodForest"),
    WEREWOLF_CLASSIC_CARD("loup");



    public final String name;
    private ImageAssets(String name){
        this.name = name;
    }
}
