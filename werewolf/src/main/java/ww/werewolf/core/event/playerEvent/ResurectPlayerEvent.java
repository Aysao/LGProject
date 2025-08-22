package ww.werewolf.core.event.playerEvent;

import java.util.ArrayList;

import ww.werewolf.common.event.PlayerEvent;
import ww.werewolf.core.enumCore.PlayerState;
import ww.werewolf.core.game.Player;

public class ResurectPlayerEvent implements PlayerEvent {

    private final int _idEvent = 0;
    private final String name = "Resurect";
    private final String description = "Réssucite une ou plusieurs personnes";

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
    public void execute(ArrayList<Player> targets) {
        for(Player player: targets){
            player.setState(PlayerState.ALIVE);
        }
    }
}
