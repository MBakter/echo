package model.items;

import java.util.Random;

import model.player.*;

public class TVSZ implements IItem {
    private int hitpoints;
    private boolean fake;

    @Override
    public String toString(){
        return "TVSZ@"+Integer.toString(this.hashCode()).substring(0, 4);
    }

    /*
     * Konstruktor
     */
    public TVSZ() {
        hitpoints = 3;
        Random rand = new Random();
        fake = rand.nextDouble() <= 0.2 ? true : false;
    }

    @Override
    public void useItem(Player p) {
        /* Do nothing */
        return;
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
        if(hitpoints > 0 && !fake) {
            hitpoints--;
            return true;
        }
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


}
