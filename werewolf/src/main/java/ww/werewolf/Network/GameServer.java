package ww.werewolf.Network;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;

import ww.werewolf.core.card.Villager;
import ww.werewolf.core.card.WereWolf;
import ww.werewolf.core.game.Player;

public class GameServer extends GameNet {
    private Server server;
    public GameServer(){
        
        try {
            server = new Server();
            server.start();
            server.bind(54555, 54777); // Choisissez les ports appropriés pour votre application
    
            // Enregistrez vos classes personnalisées pour la sérialisation avec Kryo
            Kryo kryo = server.getKryo();
            kryo.register(WereWolf.class); 
            kryo.register(Villager.class); 
            kryo.register(Player.class);  
            kryo.register(ArrayList.class); 
            kryo.register(HashMap.class); 
            kryo.register(Connection.class); 
            kryo.register(OnlineAction.class); 
    
            server.addListener(new ServerListener()); // Définissez votre propre gestionnaire d'événements
    
            System.out.println("Serveur démarré.");            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Server getServer() {
        return server;
    }
    public void setServer(Server server) {
        this.server = server;
    }
    
}
