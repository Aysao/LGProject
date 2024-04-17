package ww.werewolf.Card;

import ww.werewolf.GameSystem.WinCondition;

public class Villager extends Card{



    public Villager() {

        this._idcard = 0;
        this.description = "Le villageois n'a aucun pouvoir particulier il ne doit seulement ne pas se faire tuer";
        this.win_condition = WinCondition.VILLAGER;
    }

    @Override
    public void effects() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'effects'");
    }

}
