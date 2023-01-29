package ww.werewolf.Card;

import ww.werewolf.Order;

public class Loupgarou extends Card{




    public Loupgarou() {

        this._idcard = 1;
        this.description = "Le Loup-garou n'a aucun pouvoir particulier il doit seulement tuer tous les villageois la nuit tombé";
        this.framework = null;
        this.image = null;
        this.win_condition = 1;
        this.order = Order.LOUP_GAROU;
    }

    @Override
    public int Effect() {
        int user_choice = -1;
        //si c'est la nuit
        // donner le choix de tuer quelqu'un
        return user_choice;
    }

}
