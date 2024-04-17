package ww.werewolf.Network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import ww.werewolf.GameSystem.Board;


public class ServerListener extends Listener {

    @Override
    public void connected(Connection connection) {
        System.out.println("Le joueur suivant vient de se connecter : " + connection.getID());
    }

    @Override
    public void disconnected(Connection connection) {
        System.out.println("Le joueur suivant vient de se déconnecter : " + connection.getID());
    }

    @Override
    public void idle(Connection connection) {
        // TODO Auto-generated method stub
        super.idle(connection);
    }

    @Override
    public void received(Connection connection, Object object) {
        
        if(object instanceof OnlineAction){
            OnlineAction onlineAction = (OnlineAction) object;
            if(onlineAction.getAction().equals("Voted") && Board.nbVoteWaited > 0){

                if(onlineAction.getLinkObject() instanceof String){
                    String receivingAction = (String) onlineAction.getLinkObject();
                    Board.votedPlayer.add(receivingAction);
                    Board.nbVoteWaited--; 
                    if(Board.nbVoteWaited == 0)
                        Board.waitingVote = false;
                }
            }
        }
    }
    
}
