package ww.werewolf.core.game;

import java.util.ArrayList;

import ww.werewolf.common.event.Phase;

public class GameTurn {
    
    private boolean endOfGame;
    private int currentTurn;
    private Phase currentPhase;
    private PlayerList playerList;

    public GameTurn(PlayerList playerList) {
        this.endOfGame = false;
        this.currentTurn = 0;
        this.currentPhase = null;
        this.playerList = playerList;
    }
    

    public void turnProcess(ArrayList<Phase> phases){
        while(!endOfGame){
            for(Phase phase : phases){
                currentPhase = phase;
                PlayerList playersOfThisPhase = playerList.getPlayersAmongPhase(phase);
                phase.execute(playersOfThisPhase);

                
            }
        }
    }
}
