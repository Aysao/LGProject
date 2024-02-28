package ww.werewolf.GameCycle;

import java.util.HashMap;

public class Turn {
    private int currentTurn;
    private int turnCount;
    private boolean end;
    
    public Turn()
    {
        currentTurn = 0;
        turnCount = 0;
        end = false;
    }


    public boolean nextTurn()
    {
        
        if(end)
            return true;
        else
            return false;
        
    }
}
