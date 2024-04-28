package model.items;

import controller.Timer;
import model.*;
import model.player.*;

public class Cheese implements IItem, ITimedEntity {
    private ITimer timer;
    private boolean isUsed;
    private Room room;
    private static int TIME = 2;

    @Override
    public String toString(){
        return "Cheese@"+Integer.toString(this.hashCode()).substring(0, 4);
    }

    /*
     * Konstruktor
     * Paraméterként kapja a Timer osztályt amit a kontroller kezel
     * Majd ezt a refernciát eltárolja és a timerbe is beleteszi magát
     */
    public Cheese(ITimer t) {
        timer = t;
        t.addItem(this);
    }

    @Override
    public void useItem(Player p) {
        if(isUsed) 
            return;
        
        timer.startTimer(this, TIME);
        room = p.getRoom();

        room.addEffect(ERoomEffects.POISONED);
        p.removeItem(this);
        room.addItem(this);
    }

    @Override
    public void pickUp(Student s) {
        s.addItem(this);
    }

    @Override
    public void pickUp(Teacher t) {
        t.addItem(this);
    }

    @Override
    public void pickUp(Cleaner c) {
        c.addItem(this);
    }

    @Override
    public void dropItem(Player p) {
        p.removeItem(this);
    }

    @Override
    public boolean TeacherAttacked(Student s) {
        /* Do nothing */
        return false;
    }

    @Override
    public boolean RoomPoisoned(Student s) {
        /* Do nothing */
        return false;
    }

    @Override
    public void RoomCleanFromPoison(Student s) {
        /* Do nothing */
        return;
    }

    @Override
    public boolean TeacherAttackable(Student s) {
        /* Do nothing */
        return false;
    }

    @Override
    public void timerEnd() {
        room.removeEffect(ERoomEffects.POISONED);
    }
    
    
}
