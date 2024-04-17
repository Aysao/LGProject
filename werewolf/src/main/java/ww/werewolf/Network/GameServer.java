package ww.werewolf.Network;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;

import ww.werewolf.Card.Card;
import ww.werewolf.Card.Cupidon;
import ww.werewolf.Card.Villager;
import ww.werewolf.Card.WereWolf;
import ww.werewolf.Card.Witch;
import ww.werewolf.GameSystem.ListPlayer;
import ww.werewolf.GameSystem.Player;
import ww.werewolf.GameSystem.WinCondition;

public class GameServer extends GameNet {
    private Server server;
    public GameServer(){
        
        try {
            server = new Server();
            server.start();
            server.bind(54555, 54777); // Choisissez les ports appropriés pour votre application
    
            // Enregistrez vos classes personnalisées pour la sérialisation avec Kryo
            Kryo kryo = server.getKryo();
            kryo.register(Card.class); // Remplacez SomeCustomClass par vos classes personnalisées
            kryo.register(WereWolf.class); 
            kryo.register(Witch.class); 
            kryo.register(Cupidon.class); 
            kryo.register(Villager.class); 
            kryo.register(WinCondition.class); 
            kryo.register(ListPlayer.class);
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
