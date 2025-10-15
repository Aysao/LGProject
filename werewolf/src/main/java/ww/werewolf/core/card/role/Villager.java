package ww.werewolf.core.card.role;


import java.util.ArrayList;

import ww.werewolf.common.card.Card;
import ww.werewolf.common.card.Group;
import ww.werewolf.common.event.Phase;
import ww.werewolf.common.event.PlayerEvent;
import ww.werewolf.core.card.group.VillagerGroup;

public class Villager implements Card{

    private final int _idcard = 0;
    private final String description = "Le villageois n'a aucun pouvoir particulier il ne doit seulement ne pas se faire tuer tout en débusquant les Loup garou";
    private final String name = "Villager";
    private ArrayList<Group> groups;

    public Villager() {
        groups = new ArrayList<>();
        groups.add(new VillagerGroup());
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
    public ArrayList<Group> getGroupPlayer() {
        return groups;
    }

}
