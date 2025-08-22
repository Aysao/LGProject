package ww.werewolf.Network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;



public class ClientListener extends Listener {

    @Override
    public void idle(Connection connection) {
        // TODO Auto-generated method stub
        super.idle(connection);
    }

    @Override
    public void received(Connection connection, Object object) {

    }
    
}
