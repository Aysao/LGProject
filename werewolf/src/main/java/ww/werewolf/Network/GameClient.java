package ww.werewolf.Network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;

import ww.werewolf.core.card.role.Villager;
import ww.werewolf.core.card.role.WereWolf;
import ww.werewolf.core.game.Player;

public class GameClient extends GameNet {
    private Client client;

    public GameClient(){
        try {
            client = new Client();
            client.start();
            client.connect(60000, "localhost", 54555, 54777); // Choisissez les ports appropriés pour votre application

            // Enregistrez vos classes personnalisées pour la sérialisation avec Kryo
            Kryo kryo = client.getKryo();
            kryo.register(WereWolf.class); 
            kryo.register(Villager.class); 
            kryo.register(Player.class);  
            kryo.register(ArrayList.class); 
            kryo.register(HashMap.class); 
            kryo.register(Connection.class); 
            kryo.register(OnlineAction.class);

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
