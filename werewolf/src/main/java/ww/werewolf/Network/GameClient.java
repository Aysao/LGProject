package ww.werewolf.Network;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;

import ww.werewolf.Card.Card;
import ww.werewolf.Card.Cupidon;
import ww.werewolf.Card.Villager;
import ww.werewolf.Card.WereWolf;
import ww.werewolf.Card.Witch;
import ww.werewolf.GameSystem.ListPlayer;
import ww.werewolf.GameSystem.Player;
import ww.werewolf.GameSystem.WinCondition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.esotericsoftware.kryo.Kryo;

public class GameClient extends GameNet {
    private Client client;

    public GameClient(){
        try {
            client = new Client();
            client.start();
            client.connect(10000, "localhost", 54555, 54777); // Choisissez les ports appropriés pour votre application

            // Enregistrez vos classes personnalisées pour la sérialisation avec Kryo
            Kryo kryo = client.getKryo();
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

            client.addListener(new ClientListener()); // Définissez votre propre gestionnaire d'événements


            System.out.println("Client démarré.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    
}
