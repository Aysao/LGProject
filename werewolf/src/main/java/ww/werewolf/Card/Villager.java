package ww.werewolf.Card;

import java.util.HashMap;

public class Villager extends Card{



    public Villager() {

        this._idcard = 0;
        this.description = "Le villageois n'a aucun pouvoir particulier il ne doit seulement ne pas se faire tuer";
        this.frame = null;
        this.image = null;
        this.win_condition = 0;
    }


    @Override
    public int Effect() {
        return 1;
    }


     @Override
    public int action(HashMap<Card,Integer> c)
    {
        return 1;
    }

}
