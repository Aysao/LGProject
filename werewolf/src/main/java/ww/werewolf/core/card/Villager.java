package ww.werewolf.core.card;


import ww.werewolf.common.card.Card;
import ww.werewolf.common.card.Group;
import ww.werewolf.common.event.Phase;
import ww.werewolf.common.event.PlayerEvent;

public class Villager implements Card{

    private final int _idcard = 0;
    private final String description = "Le villageois n'a aucun pouvoir particulier il ne doit seulement ne pas se faire tuer tout en débusquant les Loup garou";
    private final String name = "Villager";

    public Villager() {
        
    }

    @Override
    public int getId() {
        return this._idcard;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Phase getPhaseAction() {
        return null;
    }

    @Override
    public PlayerEvent getPlayerEvent() {
        return null;
    }

    @Override
    public void onPlayerEvent(PlayerEvent playerEvent) {
        
    }

    @Override
    public Group getGroupPlayer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGroupPlayer'");
    }

}
