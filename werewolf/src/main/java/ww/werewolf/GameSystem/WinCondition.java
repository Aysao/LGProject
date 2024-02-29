package ww.werewolf.GameSystem;

public enum WinCondition {
    NONE(-1,"Match nul"),
    LOVER(0, "Victoire des amoureux"),
    VILLAGER(1,"Victoire des villagois"),
    LOUP_GAROU(2, "Victoire des loup-garous");

    private final int code;
    private final String winMessage;
    private WinCondition(int index, String winMessage){
        code = index;
        this.winMessage = winMessage;
    }

    public int getCode(){
        return code;
    }

    public String getWinMessage(){
        return winMessage;
    }
}
