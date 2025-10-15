package ww.werewolf.core.card;
//class destiner à regrouper toute les cartes du jeux et à creer des instances de ces cartes à la fin de la selection.

import java.util.ArrayList;
import java.util.Random;

import ww.werewolf.common.card.Card;
import ww.werewolf.common.card.CardManager;
import ww.werewolf.core.game.Player;
import ww.werewolf.core.game.PlayerList;
import ww.werewolf.core.init.CoreLoader;
import ww.werewolf.core.init.ModLoader;

public class CardManagerImpl implements CardManager{
    ArrayList<Card> cardAvailableToSelect;
    ArrayList<Card> cardsSelected;

    @Override
    public ArrayList<Card> getAvailableCards() {
        return cardAvailableToSelect;
    }

    public void randomCardDistribution(PlayerList playerList){
        for(Player player : playerList.getPlayersList()){
            Random random = new Random();
            int indexCards = random.nextInt(cardsSelected.size());
            Card selectedCard = cardsSelected.remove(indexCards);
            player.setRoleCard(selectedCard);
        }
    }

    public void addSelectedCard(Card card){
        cardsSelected.add(card);
    }

    public void removeSelectedCard(Card card){
        cardsSelected.remove(card);
    }

    public void initiateAvailableCards(){
        cardAvailableToSelect.addAll(CoreLoader.loadCoreCard());
        cardAvailableToSelect.addAll(ModLoader.loadAllCardsMod());
    }




}
