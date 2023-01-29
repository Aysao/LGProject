package ww.werewolf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import ww.werewolf.Card.Card;

public class Board {
    private HashMap<Integer,String> userMap;
    private HashMap<Integer,Card> cardMap;
    private List<Integer> order;
    private List<Integer> playerAlive;
    private int time;                               //time = 1 => nuit time = 0 => jour
    private int nturn;
    //message loup-garou avec ptite fille qui peux voir
    //message story
    private ImageIO background;
    //icone du menu pour option etc


    public Board(HashMap<Integer, String> userMap, ImageIO background, List<Card> cardAvailable) {
        this.userMap = userMap;
        playerAlive = Arrays.asList(0,0,0,0);
        order = new ArrayList<Integer>();

        for(int i = 1; i < userMap.size();i++)
        {
            Random rand = new Random();
            int icard = rand.nextInt(cardAvailable.size() - 0 + 1) + 0;
            System.out.println("User : " + i + " | icard : " + icard);
            Card c = cardAvailable.get(icard);
            playerAlive.set(c.getWin_condition(),playerAlive.get(c.getWin_condition())+1);
            cardMap.put(i, cardAvailable.get(icard));
        }
        this.time = 1;
        this.nturn = 0;
        this.background = background;
    }

    public int GameStart(){
        int game_status = -1;

        while(game_status == -1)
        {



            game_status = whoWin();
        }

        return game_status;
    };

    public int whoWin()
    {

        if(playerAlive.get(3) == 2 && playerAlive.get(1) == 1 && playerAlive.get(2) == 1)
            return 3;
        else if(playerAlive.get(2) == 0)
            return 1;
        else if(playerAlive.get(1) == 0)
            return 2;
        else
            return -1;
    }



    public HashMap<Integer, String> getUserMap() {
        return userMap;
    }
    public void setUserMap(HashMap<Integer, String> userMap) {
        this.userMap = userMap;
    }
    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }
    public int getNturn() {
        return nturn;
    }
    public void setNturn(int nturn) {
        this.nturn = nturn;
    }
    public ImageIO getBackground() {
        return background;
    }
    public void setBackground(ImageIO background) {
        this.background = background;
    }
    public HashMap<Integer, Card> getCardMap() {
        return cardMap;
    }
    public void setCardMap(HashMap<Integer, Card> cardMap) {
        this.cardMap = cardMap;
    }


}
