package ww.werewolf.common.event;

import ww.werewolf.core.game.PlayerList;

public interface Phase {
    public int getId();

    public String getName();

    public String getDescription();

    public void execute(PlayerList playerList);
    
}
