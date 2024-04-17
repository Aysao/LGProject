package ww.werewolf.Card;

import ww.werewolf.GameSystem.WinCondition;

public class WereWolf extends Card{




    public WereWolf() {

        this._idcard = 1;
        this.description = "Le Loup-garou n'a aucun pouvoir particulier il doit seulement tuer tous les villageois la nuit tombé";
        this.win_condition = WinCondition.LOUP_GAROU;
        this.NightOrder = 300;
    }

    @Override
    public void effects() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'effects'");
    }


}
