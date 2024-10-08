package model.items;

import java.util.ArrayList;

import controller.Timer;
import model.*;
import model.player.*;
import test.IPrintStat;
import view.IVItems;
import view.VBeer;

public class Beer implements IItem, ITimedEntity, IPrintStat, IVMBeer {
    private ITimer timer;
    private EBeerState state;
    private static int TIME = 5;
    private String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Beer@" + Integer.toString(this.hashCode()).substring(0, 4);
    }

    /*
     * Konstruktor
     * Paraméterként kapja a Timer osztályt amit a kontroller kezel
     * Majd ezt a refernciát eltárolja és a timerbe is beleteszi magát
     */
    public Beer(String s, Timer t) {
        name = s;
        timer = t;
        t.addEntity(this);
        state = EBeerState.INACTIVE;
    }

    public Beer(Timer t) {
        timer = t;
        t.addEntity(this);
        state = EBeerState.INACTIVE;
    }

    @Override
    public void useItem(Player p) {
        timer.startTimer(this, TIME);

        state = EBeerState.RUNNING;
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
        timer.pauseTimer(this);

        p.removeItem(this);
    }

    @Override
    public boolean TeacherAttacked(Student s) {
        switch (state) {
            case INACTIVE:
                timer.startTimer(this, TIME);
                state = EBeerState.RUNNING;
                return true;
            case RUNNING:
                return true;
            case DISABLED:
                return false;
            default:
                break;
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

    @Override
    public void timerEnd() {
        state = EBeerState.DISABLED;
    }

    @Override
    public void printStat(String name) {
        System.out.printf("%s timer %d%n", name, TIME);
        System.out.printf("%s state %s%n", name, state);
    }

    @Override
    public void statesOptions() {
        System.out.printf("\tSTATES");
    }

    @Override
    public void setState(ArrayList<String> args) {
    }

    @Override
    public int getTime() {
        return timer.getETA(this);
    }

    @Override
    public EBeerState getState() {
        return state;
    }

    @Override
    public void acceptView(ArrayList<IVItems> l) {
        l.add(new VBeer(this));
    }
}
