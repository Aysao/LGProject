package ww.werewolf.core.card.group;

import ww.werewolf.common.card.Group;
import ww.werewolf.core.game.Player;
import ww.werewolf.core.game.PlayerList;

public class WereWolfGroup implements Group {

    private final int _idGroup = 0;
    private final String name = "WereWolfGroup";
    private final String description = "Groupe des loups garous leur but est de tué les villageois sans se faire débusquer";
    private final int priority = 500;


    @Override
    public int getId() {
        return _idGroup;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public boolean hasWon(PlayerList playerList) {
        boolean result = true;
        for(Player player : playerList.getPlayersList()){
            if(!player.getRoleCard().getGroupPlayer().contains(this)){
                result = false;
            }
        }
        return result;
    }
    
}
