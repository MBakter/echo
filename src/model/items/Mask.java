package model.items;

import java.util.Random;

import model.*;
import model.player.*;

public class Mask implements IItem, ITimedEntity {
    private ITimer timer;
    private static int TIME = 4;
    private boolean fake;
    private boolean functional;
    private Student wearer;

    @Override
    public String toString(){
        return "Mask@"+Integer.toString(this.hashCode()).substring(0, 4);
    }

    /*
     * Konstruktor
     * Paraméterként kapja a Timer osztályt amit a kontroller kezel
     * Majd ezt a refernciát eltárolja és a timerbe is beleteszi magát
     */
    public Mask(ITimer t) {
        timer = t;
        t.addItem(this);
        functional = true;
        Random rand = new Random();
        fake = rand.nextDouble() <= 0.2 ? true : false;
    }

    /*
     * Beállítja a hordozót a paraméterként kapott Studentre
     */
    public void setWearer(Student s) {
        wearer = s;
    }

    @Override
    public void useItem(Player p) {
        /* Do nothing */
        return;
    }

    @Override
    public void pickUp(Student s) {
        wearer = s;
    
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
        timer.pauseTimer(this);
        
        p.removeItem(this);
    }

    @Override
    public boolean TeacherAttacked(Student s) {
        /* Do nothing */
        return false;
    }

    @Override
    public boolean RoomPoisoned(Student s) {

        if(!functional || fake)
            return false;

        timer.startTimer(this, TIME);
        return true;
    }

    @Override
    public void RoomCleanFromPoison(Student s) {
        timer.pauseTimer(this);
    }

    @Override
    public boolean TeacherAttackable(Student s) {
        /* Do nothing */
        return false;
    }

    @Override
    public void timerEnd() {
        functional = false;
    }

}
