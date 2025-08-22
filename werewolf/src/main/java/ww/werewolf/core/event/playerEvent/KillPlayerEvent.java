package ww.werewolf.core.event.playerEvent;

import java.util.ArrayList;

import ww.werewolf.common.event.PlayerEvent;
import ww.werewolf.core.enumCore.PlayerState;
import ww.werewolf.core.game.Player;

public class KillPlayerEvent implements PlayerEvent {

    private final int _idEvent = 0;
    private final String name = "Kill";
    private final String description = "Déclenche la mort d'une ou plusieurs personnes";

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
            player.setState(PlayerState.DEAD);
        }
    }
    
}
