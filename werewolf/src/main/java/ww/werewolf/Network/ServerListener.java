package ww.werewolf.Network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import ww.werewolf.Card.WereWolf;

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
        
    }
    
}
