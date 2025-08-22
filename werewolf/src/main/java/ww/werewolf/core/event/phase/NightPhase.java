package ww.werewolf.core.event.phase;

import ww.werewolf.common.event.Phase;
import ww.werewolf.core.game.Player;
import ww.werewolf.core.game.PlayerList;

public class NightPhase implements Phase {

    private final int _idPhase = 0;
    private final String name = "Day Phase";
    private final String description = "Phase de jour ou le vote aura lieu";

    @Override
    public int getId() {
        return _idPhase;
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
    public void execute(PlayerList playerList) {
        for(Player player : playerList.getPlayersList()){
            if(player.getRoleCard().getPlayerEvent() != null){
                //TODO : Mettre en place le systeme de target sur un des joueurs encore en vie
                //player.getRoleCard().getPlayerEvent().execute(target);
                
            }
                
        }
    }
    
}
