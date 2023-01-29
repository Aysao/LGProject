package ww.werewolf.Card;

import ww.werewolf.Order;

public class Villager extends Card{



    public Villager() {

        this._idcard = 0;
        this.description = "Le villageois n'a aucun pouvoir particulier il ne doit seulement ne pas se faire tuer";
        this.framework = null;
        this.image = null;
        this.win_condition = 0;
        this.order = Order.SIMPLE_VILLAGER;
    }


    @Override
    public int Effect() {
        return 1;
    }

}
