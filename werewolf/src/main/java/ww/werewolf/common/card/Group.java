package ww.werewolf.common.card;

import ww.werewolf.core.game.PlayerList;

public interface Group {
    public int getId();

    public String getName();

    public String getDescription();

    public int getPriority();

    public boolean hasWon(PlayerList playerList);
    
}
