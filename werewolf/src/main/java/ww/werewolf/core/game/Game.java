package ww.werewolf.core.game;

import ww.werewolf.common.card.CardManager;
import ww.werewolf.core.card.CardManagerImpl;

public class Game {
    
    private GameTurn gameTurn;
    private PlayerList playerList;
    private CardManagerImpl cardManager;

    public Game(PlayerList playerList, CardManager cardManager){
        gameInitializer(playerList, cardManager);

        //gameTurn.turnProcess(phases);


    }

    public void gameInitializer(PlayerList playerList, CardManager cardManager){
        this.playerList = playerList;
        this.cardManager = (CardManagerImpl) cardManager;

        this.cardManager.randomCardDistribution(this.playerList);

        this.gameTurn = new GameTurn(playerList);
    }

}
