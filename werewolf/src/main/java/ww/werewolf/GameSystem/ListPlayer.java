package ww.werewolf.GameSystem;

import java.util.ArrayList;
import java.util.Random;

public class ListPlayer extends ArrayList<Player> {

    public void mixPlayer(){
        ArrayList<Player> mixList = new ArrayList<>();
        Random rand = new Random();
        for(int i = 0; i < this.size(); i++){
            
            int r = rand.nextInt(this.size());
            mixList.add(this.get(r));
            this.remove(r);
        }
        this.addAll(mixList);
    }

    public String toString(){
        String res = "";
        for(Player player : this)
        {
            res += "player : " + player.getUuid() + "\n";
        }
        return res;
    }

    public String arraysToString(ArrayList<Player> array){
        String res = "";
        for(Player player : array)
        {
            res += "player : " + player.getUuid() + "\n";
        }
        return res;
    }
}
