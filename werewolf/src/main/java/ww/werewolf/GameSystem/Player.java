package ww.werewolf.GameSystem;

import java.util.UUID;
import ww.werewolf.Card.Card;

public class Player {
    private String uuid = null;
    private String name = "";
    private Card roleCard = null;
    private String lover = "";
    private int state = 1;                  // 1 = alive ; 0 = dead

   
    public Player()
    {
        uuid = UUID.randomUUID().toString();
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
    public String getLover() {
        return lover;
    }
    public void setLover(String lover) {
        this.lover = lover;
    }

    
}
