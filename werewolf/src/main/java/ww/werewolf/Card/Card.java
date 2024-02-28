package ww.werewolf.Card;

import java.util.HashMap;

import javax.imageio.ImageIO;


public abstract class Card {
    protected int _idcard;              //l'id de la carte (ex: villager = 0)
    protected String description;       //description de la carte et de son effets
    protected ImageIO image;            //Image de la carte
    protected ImageIO frame;        //cadre de la carte
    protected int win_condition;        //l'equipe a laquelle elle appartient (villageois 0, loup garou 1, lover 2)
    protected int state;                //Mort = 0; Vivant = 1

    public abstract int Effect();



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
    public ImageIO getImage() {
        return image;
    }
    public void setImage(ImageIO image) {
        this.image = image;
    }
    public ImageIO getFrame() {
        return frame;
    }
    public void setFrame(ImageIO frame) {
        this.frame = frame;
    }
    public int getWin_condition() {
        return win_condition;
    }

    public void setWin_condition(int win_condition) {
        this.win_condition = win_condition;
    }
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public abstract int action(HashMap<Card,Integer> cards);


}
