package ww.werewolf.core.init;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import ww.werewolf.common.card.Card;
import ww.werewolf.common.event.Phase;


public class ModLoader {
    

    public static List<Card> loadAllCardsMod(){
        List<Card> modCards = new ArrayList<>();

        ServiceLoader<Card> loader = ServiceLoader.load(Card.class);
        for(Card card : loader){
            modCards.add(card);
        }
        return modCards;
    }

    public static List<Phase> loadAllPhasesMod(){
        List<Phase> modPhases = new ArrayList<>();

        ServiceLoader<Phase> loader = ServiceLoader.load(Phase.class);
        for(Phase phase : loader){
            modPhases.add(phase);
        }
        return modPhases;
    }
}
