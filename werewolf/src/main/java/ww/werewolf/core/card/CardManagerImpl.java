package ww.werewolf.core.card;
//class destiner à regrouper toute les cartes du jeux et à creer des instances de ces cartes à la fin de la selection.

import java.util.ArrayList;

import ww.werewolf.common.card.Card;
import ww.werewolf.common.card.CardManager;

public class CardManagerImpl implements CardManager{
    ArrayList<Card> cardAvailableToSelect; 

    @Override
    public ArrayList<ww.werewolf.common.card.Card> getAvailableCards() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAvailableCards'");
    }


}
