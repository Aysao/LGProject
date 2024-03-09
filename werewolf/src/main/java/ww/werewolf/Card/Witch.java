package ww.werewolf.Card;

import ww.werewolf.GameSystem.WinCondition;

public class Witch extends Card{
    
    public int healingPotion;
    public int deathPotion;
    public Witch() {
        this._idcard = 3;
        this.description = "La soricère à deux potions une de vie et une de mort chacune utilisable une seul fois, chaque nuit elle pourra utiliser l'une d'elle pour ressuciter ou tuer quelqu'un";
        this.healingPotion = 1;
        this.deathPotion = 1;
        this.win_condition = WinCondition.VILLAGER;
        this.NightOrder = 400;
    }

}
