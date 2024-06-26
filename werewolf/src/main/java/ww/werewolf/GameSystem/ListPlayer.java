package ww.werewolf.GameSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class ListPlayer extends ArrayList<Player> {



    private HashMap<WinCondition,Integer> aliveRoles;
    private ArrayList<Player> alivePlayers;
    private int maxNumberRolesAliveForWin = 1;

    public void initGamePlayer(){
        aliveRoles = new HashMap<>();
        alivePlayers = new ArrayList<>();

        for(Player player : this){
            if(aliveRoles.containsKey(player.getRoleCard().getWin_condition()))
            {
                aliveRoles.put(player.getRoleCard().getWin_condition(), aliveRoles.get(player.getRoleCard().getWin_condition()) + 1);
            }
            else{
                aliveRoles.put(player.getRoleCard().getWin_condition(), 1);
            }
        }
        Collections.sort(this,new Comparator<Player>() {

            @Override
            public int compare(Player o1, Player o2) {
                return o1.getRoleCard().getNightOrder() - o2.getRoleCard().getNightOrder();
            }
            
        });
        alivePlayers.addAll(this);
    }

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

    public void playerDied(Player player){
        WinCondition index = player.getRoleCard().getWin_condition();
        aliveRoles.replace(index,aliveRoles.get(index) - 1);
        if(aliveRoles.get(index) == 0){
            aliveRoles.remove(index);
        }
        alivePlayers.remove(player);
    }

    public WinCondition getWinner(){
        if(alivePlayers.size() <= maxNumberRolesAliveForWin || aliveRoles.size() == 1)
        {

            /*
             * Management of specialWinCondition
             */

            if(alivePlayers.size() <= maxNumberRolesAliveForWin){
                WinCondition winner = updateMaxNumberRolesAliveForWin();
                if(!winner.equals(WinCondition.NONE)){
                    return winner;
                }
            }

            /*
             * Management of other win
             */
            
            return aliveRoles.keySet().stream().findFirst().get();

        }
        return WinCondition.NONE;
        
    }

    // à appeler pour chaque roles spécial
    public WinCondition updateMaxNumberRolesAliveForWin(){
        int max;
        WinCondition winner = WinCondition.NONE;
        for(WinCondition w : WinCondition.values()){
            max = 0;
            for(Player p : alivePlayers){
                if(p.getSpecialCondition().containsKey(w)){
                    max++;
                }
            }
            if(maxNumberRolesAliveForWin < max)
            {
                maxNumberRolesAliveForWin = max;
                winner = w;
            }
        }

        if(maxNumberRolesAliveForWin == alivePlayers.size()){
            return winner;
        }
        else{
            return WinCondition.NONE;
        }

    }

    public ArrayList<Player> getaliveRoles(){
        ArrayList<Player> alives = new ArrayList<>();
        for(Player player : this){
            if(player.getState() == 1){
                alives.add(player);
            }
        }
        return alives;
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

    public <T> ArrayList<Player> getAllPlayerFromRole(Class<T> clazz){
        ArrayList<Player> res = new ArrayList<Player>();
        for(Player player : this){
            if(clazz.isInstance(player.getRoleCard())){
                res.add(player);
            }
        }
        return res;
    }
    public HashMap<WinCondition, Integer> getAliveRoles() {
        return aliveRoles;
    }

    public ArrayList<Player> getAlivePlayers() {
        return alivePlayers;
    }

    public Player getPlayerFromConnection(int i){
        for(Player p : this){
            if(p.getClient() == i){
                return p;
            }
        }
        return null;
    }

    public List<Player> getPlayerFromSpecialWinCondition(WinCondition w){
        List<Player> res = new ArrayList<>();

        for(Player p : alivePlayers){
            if(p.getSpecialCondition().containsKey(w)){
                res.add(p);
            }
        }

        return res;

    }

    public Player getPlayerFromUuid(String uuid){
        for(Player p : this){
            if(p.getUuid().equals(uuid)){
                return p;
            }
        }
        return null;
    }
    
}
