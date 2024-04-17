package ww.werewolf.Network;

public class OnlineAction {
    private String action = "";
    private Object linkObject;
    private String message = "";

    public OnlineAction (String action, Object linkObject, String message){
        this.action = action;
        this.linkObject = linkObject;
        this.message = message;
    }
    public OnlineAction (String action, Object linkObject){
        this.action = action;
        this.linkObject = linkObject;
        this.message = "";
    }
    public OnlineAction (){
        this.action = "";
        this.linkObject = null;
        this.message = "";
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Object getLinkObject() {
        return linkObject;
    }

    public void setLinkObject(Object linkObject) {
        this.linkObject = linkObject;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    
}
