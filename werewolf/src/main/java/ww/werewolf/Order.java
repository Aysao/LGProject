package ww.werewolf;

public enum Order {

    SPECIAL(0),
    VOYANTE(1),
    LOUP_GAROU(2),
    SORCIERE(3),
    CHASSEUR(4),
    ELECTION(5),
    SIMPLE_VILLAGER(6),

    ;
    private final int order;

    private Order(int o){
        this.order = o;
    }
}
