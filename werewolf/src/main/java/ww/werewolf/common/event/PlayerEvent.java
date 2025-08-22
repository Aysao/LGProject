package ww.werewolf.common.event;

import java.util.ArrayList;

import ww.werewolf.core.game.Player;

public interface PlayerEvent {
    
    public int getId();

    public String getName();

    public String getDescription();
    
    public void execute(ArrayList<Player> target);
}
