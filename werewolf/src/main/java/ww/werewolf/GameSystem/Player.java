package ww.werewolf.GameSystem;

import java.util.HashMap;
import java.util.UUID;
import ww.werewolf.Card.Card;

public class Player {
    private String uuid = null;
    private String name = "";
    private Card roleCard = null;
    private int state = 1;         // 1 = alive ; 0 = dead
    private int idConnection = -1;
    private HashMap<WinCondition,String> specialCondition;
    public Player()
    {
        uuid = UUID.randomUUID().toString();
        specialCondition = new HashMap<>();
    }

    public Player(String name)
    {
        this.name = name;
        uuid = UUID.randomUUID().toString();
    }

    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public String getUuid() {
        if(uuid == null)
            uuid = UUID.randomUUID().toString();
        return uuid;
    }
    public Card getRoleCard() {
        return roleCard;
    }
    public void setRoleCard(Card roleCard) {
        this.roleCard = roleCard;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClient() {
        return idConnection;
    }

    public void setClient(int client) {
        this.idConnection = client;
    }

    public int getIdConnection() {
        return idConnection;
    }

    public void setIdConnection(int idConnection) {
        this.idConnection = idConnection;
    }

    public HashMap<WinCondition, String> getSpecialCondition() {
        return specialCondition;
    }

    public void setSpecialCondition(HashMap<WinCondition, String> specialCondition) {
        this.specialCondition = specialCondition;
    }

    
    
}
