package ww.werewolf.GameSystem;

import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import com.esotericsoftware.kryonet.Client;

import ww.werewolf.App;
import ww.werewolf.Card.Villager;
import ww.werewolf.Network.OnlineAction;

public class Board {
    public static ListPlayer inGamePlayers;
    public static ArrayList<String> votedPlayer;
    public static ArrayList<OnlineAction> actionPlayer;
    public static int nbVoteWaited = 0;
    public static boolean wait = true;
    public static boolean waitingVote = true;
    private boolean test = true;
    private Client client;
    private int time;                               //time = 1 => nuit time = 0 => jour
    private int numberTurn;
    private boolean chiefChoosen = false;
    //message loup-garou avec ptite fille qui peux voir
    //message story
    private ImageIO background;
    //icone du menu pour option etc


    public Board(ListPlayer players, ImageIO background, AvailableCard cardAvailable, Client c) {
        this.client = c;
        Board.actionPlayer = new ArrayList<>();
        Board.inGamePlayers = new ListPlayer();
        Board.inGamePlayers.addAll(players);

        /*
         * Configuration Serveur 
         */
        if(App.getServer() != null){
            this.numberTurn = 0;
            votedPlayer = new ArrayList<>();
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

        /*
         *  Client 
         */

        while (wait) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }   
        
    }
    
    public void turn(){
        WinCondition winner = WinCondition.NONE;
        //une itération par role d'action
        int indexPlayer = 0;
        while(winner == WinCondition.NONE){
            votedPlayer.removeAll(votedPlayer);
            if(test){
                Random rand = new Random();
                Board.inGamePlayers.playerDied(Board.inGamePlayers.get(rand.nextInt(Board.inGamePlayers.getAlivePlayers().size())));
                winner = Board.inGamePlayers.getWinner();
            }
            else{
                //night
                if(Board.inGamePlayers.getAlivePlayers().get(indexPlayer).getRoleCard() instanceof Villager){
                    if(!chiefChoosen){
                        App.getServer().sendToAllUDP(new OnlineAction("Vote" , null, "Votez pour le maire de l'assemblé"));
                        waitingVote();
                    }
                    App.getServer().sendToAllUDP(new OnlineAction("Vote" , null,"Votez pour la personne que vous allez tuer ce soir"));
                    waitingVote();
                }
                else{
                    Board.inGamePlayers.getAlivePlayers().get(indexPlayer).getRoleCard().effects();
                }
            }

            if(winner != WinCondition.NONE){
                App.getServer().sendToAllTCP(winner);
                System.out.println(winner.getWinMessage());
            }
            if(indexPlayer < Board.inGamePlayers.getAlivePlayers().size()){
                indexPlayer++;
            }
            else{
                if(!(Board.inGamePlayers.getAlivePlayers().get(indexPlayer).getRoleCard() instanceof Villager)){
                    if(!chiefChoosen){
                        App.getServer().sendToAllUDP(new OnlineAction("Vote" , null, "Votez pour le maire de l'assemblé"));
                    }
                    App.getServer().sendToAllUDP(new OnlineAction("Vote" , null,"Votez pour la personne que vous allez tuer ce soir"));
                }
                indexPlayer = 0;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getNumberTurn() {
        return numberTurn;
    }

    public void setNumberTurn(int numberTurn) {
        this.numberTurn = numberTurn;
    }

    public static void waitingVote(){
        while (waitingVote) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        waitingVote = true;
    }
    
}
