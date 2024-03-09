package ww.werewolf.GameSystem;

import java.util.Random;

import javax.imageio.ImageIO;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;

import ww.werewolf.App;
import ww.werewolf.Network.GameServer;

public class Board {
    public static ListPlayer inGamePlayers;
    public static int wait = 0;
    private GameServer server;
    private Client client;
    private int time;                               //time = 1 => nuit time = 0 => jour
    private int numberTurn;
    //message loup-garou avec ptite fille qui peux voir
    //message story
    private ImageIO background;
    //icone du menu pour option etc


    public Board(ListPlayer players, ImageIO background, AvailableCard cardAvailable, Client c) {
        this.client = c;
        Board.inGamePlayers = new ListPlayer();
        Board.inGamePlayers.addAll(players);

        /*
         * Configuration Serveur 
         */
        if(App.getServer() != null){
            this.numberTurn = 0;
            //System.out.println(Board.inGamePlayers.toString());
            Board.inGamePlayers.mixPlayer();
            //System.out.println(Board.inGamePlayers.toString());
            for(Player player : Board.inGamePlayers){
                player.setRoleCard(cardAvailable.pollLast());
            }
            Board.inGamePlayers.initGamePlayer();

            App.getServer().sendToAllUDP(Board.inGamePlayers);

            
            turn();
            
        }

        while (true) {
            
        }
        /*
         * Action Client
         */
        

        
    }
    
    public void turn(){
        WinCondition winner = WinCondition.NONE;
        while(winner == WinCondition.NONE){
            Random rand = new Random();
            Board.inGamePlayers.playerDied(Board.inGamePlayers.get(rand.nextInt(Board.inGamePlayers.getAlivePlayers().size())));
            winner = Board.inGamePlayers.getWinner();
            if(winner != WinCondition.NONE){
                App.getServer().sendToAllTCP(winner);
                System.out.println(winner.getWinMessage());
            }
        }
    }

    public String vote(){
        return "";
    }
    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }
    public ImageIO getBackground() {
        return background;
    }
    public void setBackground(ImageIO background) {
        this.background = background;
    }

    @Override
    public String toString() {
        String res = "";
        for(Player player : inGamePlayers){
            res += "Board [ - " + player.getUuid() + " = " + player.getRoleCard() + " ]\n";
        }
        return res;
    }

}
