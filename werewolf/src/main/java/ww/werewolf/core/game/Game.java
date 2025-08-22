package ww.werewolf.core.game;

public class Game {
    
    private GameTurn gameTurn;

    public Game(PlayerList playerList){
        gameInitializer(playerList);

        //gameTurn.turnProcess(phases);


    }

    public void gameInitializer(PlayerList playerList){
        gameTurn = new GameTurn(playerList);
    }

}
