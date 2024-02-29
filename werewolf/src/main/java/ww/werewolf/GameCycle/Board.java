package ww.werewolf.GameCycle;

import java.util.Random;

import javax.imageio.ImageIO;

import ww.werewolf.GameSystem.AvailableCard;
import ww.werewolf.GameSystem.ListPlayer;
import ww.werewolf.GameSystem.Player;
import ww.werewolf.GameSystem.WinCondition;

public class Board {
    private ListPlayer inGamePlayers;
    private int time;                               //time = 1 => nuit time = 0 => jour
    //message loup-garou avec ptite fille qui peux voir
    //message story
    private ImageIO background;
    //icone du menu pour option etc


    public Board(ListPlayer players, ImageIO background, AvailableCard cardAvailable) {
        this.inGamePlayers = new ListPlayer();
        this.inGamePlayers.addAll(players);
        System.out.println(this.inGamePlayers.toString());
        this.inGamePlayers.mixPlayer();
        System.out.println(this.inGamePlayers.toString());
        for(Player player : this.inGamePlayers){
            player.setRoleCard(cardAvailable.pollLast());
        }
        System.out.println(toString());
        players.initGamePlayer();

        WinCondition winner = null;
        for(int i = 0; i < players.size(); i++){
            Random rand = new Random();
            players.playerDied(players.get(rand.nextInt(players.getAlivePlayers().size())));
            winner = players.getWinner();
            if(winner != WinCondition.NONE){
                System.out.println(winner.getWinMessage());
                break;
            }
        }
        /* 
        this.time = 1;
        this.background = background;

        Turn turn = new Turn();
        */
    }

    public void WerewolfWin(){
        
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
