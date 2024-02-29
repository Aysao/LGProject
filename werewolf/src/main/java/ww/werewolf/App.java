package ww.werewolf;

import ww.werewolf.GameCycle.Board;
import ww.werewolf.GameSystem.AvailableCard;
import ww.werewolf.GameSystem.ListPlayer;
import ww.werewolf.GameSystem.Player;

import ww.werewolf.Card.Loupgarou;
import ww.werewolf.Card.Villager;

public class App {

	public static void main(String[] args) {
		

		/*
		 * Création block Joueur
		 */
		ListPlayer players = new ListPlayer();
		for(int i = 0; i < 10; i++)
		{
			Player p = new Player();
			players.add(p);
		}

		/*
		 * Création carte available
		 */

		AvailableCard cardAvailable = new AvailableCard();
		try{
			cardAvailable.addXCard(Loupgarou.class, 2);
			cardAvailable.addXCard(Villager.class, 8);
		}
		catch(Exception e){
			e.printStackTrace();
		}

		/*
		 * Création du Board
		 */
		Board b = new Board(players, null, cardAvailable);
		

		//Board b = new Board(user, null,cardAvailable);
		//System.out.println(b.toString());
		//Window w = Window.getWindow();
		//w.run();
	}

}
