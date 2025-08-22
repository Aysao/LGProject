package ww.werewolf.core.card;

import ww.werewolf.common.card.Card;
import ww.werewolf.common.card.Group;
import ww.werewolf.common.event.Phase;
import ww.werewolf.common.event.PlayerEvent;

public class WereWolf implements Card{

    @Override
    public int getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Phase getPhaseAction() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PlayerEvent getPlayerEvent() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onPlayerEvent(PlayerEvent playerEvent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Group getGroupPlayer() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
