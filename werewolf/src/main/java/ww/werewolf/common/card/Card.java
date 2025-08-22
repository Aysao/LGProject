package ww.werewolf.common.card;

import ww.werewolf.common.event.Phase;
import ww.werewolf.common.event.PlayerEvent;

public interface Card {
    
    public int getId();

    public String getName();

    public String getDescription();

    public Phase getPhaseAction();

    public PlayerEvent getPlayerEvent();

    public void onPlayerEvent(PlayerEvent playerEvent);

    public Group getGroupPlayer();
    
}
