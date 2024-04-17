package ww.werewolf.Card;

import java.util.ArrayList;

import ww.werewolf.App;
import ww.werewolf.GameSystem.Board;
import ww.werewolf.GameSystem.Player;
import ww.werewolf.GameSystem.WinCondition;
import ww.werewolf.Network.OnlineAction;

public class Cupidon extends Card {
    
    public int remainEffect;
    
    public Cupidon() {
        this._idcard = 2;
        this.description = "Cupidon rend amoureux deux joueurs qui devront gagner ensemble si l'un meurt l'autre meurt aussi";
        this.win_condition = WinCondition.VILLAGER;
        this.remainEffect = 1;
        this.NightOrder = 100;
    }

    @Override
    public void effects() {
        if(remainEffect > 0){
            ArrayList<Player> cupidons = Board.inGamePlayers.getAllPlayerFromRole(Cupidon.class);
            Board.nbVoteWaited = cupidons.size();
            for(int i = 0; i < cupidons.size();i++){
                App.getServer().sendToUDP(cupidons.get(i).getIdConnection(), new OnlineAction("Vote/2",null, "Choisissez deux personnes qui tomberons amoureux : "));
            }
            Board.waitingVote();
            String[] res = Board.votedPlayer.get(0).split("/");
            
            Player p1 = Board.inGamePlayers.getPlayerFromUuid(res[0]);
            Player p2 = Board.inGamePlayers.getPlayerFromUuid(res[1]);

            if(p1 != null && p2 != null){
                p1.getSpecialCondition().put(WinCondition.LOVER, p2.getUuid());
                App.getServer().sendToUDP(p1.getIdConnection(), new OnlineAction("SelfUpdate", p1));
                p2.getSpecialCondition().put(WinCondition.LOVER, p1.getUuid());
                App.getServer().sendToUDP(p1.getIdConnection(), new OnlineAction("SelfUpdate", p2));
            }
            Board.waitingVote();
        
        }
    }
    
}

