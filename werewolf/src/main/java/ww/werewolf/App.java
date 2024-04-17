package ww.werewolf;

import ww.werewolf.GameSystem.AvailableCard;
import ww.werewolf.GameSystem.Board;
import ww.werewolf.GameSystem.ListPlayer;
import ww.werewolf.GameSystem.Player;
import ww.werewolf.Network.GameClient;
import ww.werewolf.Network.GameServer;
import ww.werewolf.Card.WereWolf;
import ww.werewolf.Card.Witch;

import java.util.Scanner;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Server;

import ww.werewolf.Card.Cupidon;

public class App {
	private static Server server = null;
	public static void main(String[] args) {
		ListPlayer players = new ListPlayer();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Serveur : 1, Client : 2");
		String choice = scanner.nextLine();

		/*
		 * Initialisation GameServer
		 */
		if(choice.equals("1")){
			App.server = new GameServer().getServer();
		}

		/*
		 * Initialisation Client
		 */
		GameClient networkClient = new GameClient();
		Client client = networkClient.getClient();
		client.sendTCP("DoneInit");

		/*
		 * Mis en attente du serveur pour qu'il puisse récup tous les clients
		 */

		if(client.getID() == 1 && App.server != null){
			System.out.println("Ready ?");
			String test = scanner.nextLine();
			
			/*
		 	 * Création block Joueur
		 	 */

			for(int i = 0; i < App.server.getConnections().length; i++){
					Player p = new Player();
					p.setClient(App.server.getConnections()[i].getID());
					players.add(p);
				}
			
			
		}

		
		
		/*
		 * Création carte available
		 */

		AvailableCard cardAvailable = new AvailableCard();
		try{
			cardAvailable.addXCard(WereWolf.class, 5);
			//cardAvailable.addXCard(Villager.class, 1);
			cardAvailable.addXCard(Witch.class, 1);
			cardAvailable.addXCard(Cupidon.class, 1);

		}
		catch(Exception e){
			e.printStackTrace();
		}

		/*
		 * Création du Board
		 */
		Board b = new Board(players, null, cardAvailable, client);
		

		//Board b = new Board(user, null,cardAvailable);
		//System.out.println(b.toString());
		//Window w = Window.getWindow();
		//w.run();
	}
	public static Server getServer() {
		return server;
	}
	public static void setServer(Server server) {
		App.server = server;
	}

	

}
