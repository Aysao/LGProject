package ww.werewolf.Card;

public class Villager extends Card{



    public Villager() {

        this._idcard = 0;
        this.description = "Le villageois n'a aucun pouvoir particulier il ne doit seulement ne pas se faire tuer";
        this.framework = null;
        this.image = null;
        this.win_condition = 0;
    }


    @Override
    public int Effect() {
        return 1;
    }

}
