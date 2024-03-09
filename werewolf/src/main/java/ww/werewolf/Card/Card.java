package ww.werewolf.Card;

import javax.imageio.ImageIO;

import ww.werewolf.GameSystem.WinCondition;


public abstract class Card {
    protected int _idcard;              //l'id de la carte (ex: villager = 0)
    protected String description;       //description de la carte et de son effets
    protected WinCondition win_condition;        //l'equipe a laquelle elle appartient (villageois 0, loup garou 1, lover 2)
    protected int state;                //Mort = 0; Vivant = 1
    protected int NightOrder = 10000;

    public int get_idcard() {
        return _idcard;
    }
    public void set_idcard(int _idcard) {
        this._idcard = _idcard;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public WinCondition getWin_condition() {
        return win_condition;
    }

    public void setWin_condition(WinCondition win_condition) {
        this.win_condition = win_condition;
    }
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public int getNightOrder() {
        return NightOrder;
    }
    public void setNightOrder(int nightOrder) {
        NightOrder = nightOrder;
    }

}
