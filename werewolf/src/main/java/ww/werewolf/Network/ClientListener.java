package ww.werewolf.Network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;


import ww.werewolf.GameSystem.Board;
import ww.werewolf.GameSystem.ListPlayer;
import ww.werewolf.GameSystem.WinCondition;

public class ClientListener extends Listener {

    @Override
    public void idle(Connection connection) {
        // TODO Auto-generated method stub
        super.idle(connection);
    }

    @Override
    public void received(Connection connection, Object object) {
        //System.out.println("Message de " + Board.inGamePlayers.getPlayerFromConnection(connection.getID()));
        if(object instanceof ListPlayer){
            System.out.println("ListPlayer récupéré");
            ListPlayer players = (ListPlayer) object;
            Board.inGamePlayers = players;
            Board.inGamePlayers.initGamePlayer();
            System.out.println("Alive player : " + players.getAlivePlayers().size());
            System.out.println("Role size : " + players.getAliveRoles().size());
            Board.wait = false;
        }

        if(object instanceof WinCondition){
            WinCondition winner = (WinCondition) object;
            if(winner != WinCondition.NONE){
                System.out.println(winner.getWinMessage());
            }
        }

        if(object instanceof OnlineAction){
            OnlineAction onlineAction = (OnlineAction) object;
            Board.actionPlayer.add(onlineAction);    
            
        }
    }
    
}
