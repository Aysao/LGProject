package ww.werewolf.core.init;

import java.util.List;

import ww.werewolf.common.card.Card;
import ww.werewolf.common.event.Phase;
import ww.werewolf.core.card.role.Villager;
import ww.werewolf.core.card.role.WereWolf;
import ww.werewolf.core.event.phase.DayPhase;
import ww.werewolf.core.event.phase.NightPhase;

public class CoreLoader {
    
    public static List<Card> loadCoreCard(){
        return List.of(
            new Villager(),
            new WereWolf()
        );
    }

    public static List<Phase> loadCorePhases(){
        return List.of(
            new DayPhase(),
            new NightPhase()
        );
    }
}
