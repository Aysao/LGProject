package ww.werewolf.core.event.playerEvent;

import java.util.ArrayList;

import ww.werewolf.common.event.PlayerEvent;
import ww.werewolf.core.game.Player;

public class LovePlayerEvent implements PlayerEvent {
    private final int _idEvent = 0;
    private final String name = "Love";
    private final String description = "Rend deux personnes amoureux";

    @Override
    public int getId() {
        return _idEvent;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void execute(ArrayList<Player> target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
}
