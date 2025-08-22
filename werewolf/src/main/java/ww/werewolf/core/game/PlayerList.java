package ww.werewolf.core.game;

import java.util.ArrayList;

import ww.werewolf.common.card.Group;
import ww.werewolf.common.event.Phase;

public class PlayerList {
    private ArrayList<Player> playerList;
    
    public PlayerList(){
        playerList = new ArrayList<Player>();
    }

    public void addPlayer(Player player){
        playerList.add(player);
    }

    public void removePlayer(Player player){
        playerList.remove(player);
    }

    public ArrayList<Player> getPlayersAmongGroup(Group group){
        ArrayList<Player> result = new ArrayList<Player>();
        for(Player player : playerList){
            if(player.getRoleCard().getGroupPlayer().equals(group)){
                result.add(player);
            }
        }
        return result;
    }

    public PlayerList getPlayersAmongPhase(Phase phase){
       PlayerList result = new PlayerList();
        for(Player player : playerList){
            if(player.getRoleCard().getPhaseAction().equals(phase)){
                result.addPlayer(player);
            }
        }
        return result;
    }

    public ArrayList<Player> getPlayersList(){
        return playerList;
    }
}
