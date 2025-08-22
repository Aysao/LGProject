package ww.werewolf.core.game;

import java.util.UUID;

import ww.werewolf.common.card.Card;
import ww.werewolf.core.enumCore.PlayerState;


public class Player {
    private String uuid = null;
    private String name = "";
    private Card roleCard = null;
    private PlayerState state = PlayerState.ALIVE;
    private int idConnection = -1;

    public Player()
    {
        uuid = UUID.randomUUID().toString();
    }

    public Player(String name)
    {
        this.name = name;
        uuid = UUID.randomUUID().toString();
    }

    public PlayerState getState() {
        return state;
    }
    public void setState(PlayerState state) {
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
    
}
