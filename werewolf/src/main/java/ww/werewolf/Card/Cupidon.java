package ww.werewolf.Card;

import ww.werewolf.GameSystem.WinCondition;

public class Cupidon extends Card {
    
    public int remainEffect;
    
    public Cupidon() {
        this._idcard = 2;
        this.description = "Cupidon rend amoureux deux joueurs qui devront gagner ensemble si l'un meurt l'autre meurt aussi";
        this.win_condition = WinCondition.VILLAGER;
        this.remainEffect = 1;
        this.NightOrder = 100;
    }

}

