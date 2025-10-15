package ww.werewolf.core.init;

import ww.werewolf.common.card.CardManager;



public class GameLoader {
    private CardManager cardManager;

    public GameLoader(){
        cardManager.initiateAvailableCards();
    }
}
