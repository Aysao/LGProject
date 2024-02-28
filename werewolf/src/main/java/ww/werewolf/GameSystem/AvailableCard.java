package ww.werewolf.GameSystem;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import ww.werewolf.Card.Card;

public class AvailableCard extends LinkedList<Card>{
    

    public <C> void addXCard(Class<C> clazz, int number) throws Exception{
        for(int i = 0; i < number;i++){
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            C newInstance = clazz.cast(constructor.newInstance());
            if(newInstance instanceof Card)
            {
                this.add((Card) newInstance);
            }           
        }
    }
}
