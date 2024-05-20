package model.items;

import java.util.ArrayList;

import controller.Timer;
import model.*;
import model.player.*;
import test.IPrintStat;
import view.IVItems;
import view.VCheese;

public class Cheese implements IItem, ITimedEntity, IPrintStat, IVMCheese {
    private ITimer timer;
    private boolean isUsed;
    private Room room;
    private static int TIME = 2;
    private String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Cheese@" + Integer.toString(this.hashCode()).substring(0, 4);
    }

    /*
     * Konstruktor
     * Paraméterként kapja a Timer osztályt amit a kontroller kezel
     * Majd ezt a refernciát eltárolja és a timerbe is beleteszi magát
     */
    public Cheese(String s, Timer t) {
        name = s;
        timer = t;
        t.addEntity(this);
    }

    public Cheese(Timer t) {
        timer = t;
        t.addEntity(this);
    }

    @Override
    public void useItem(Player p) {
        if (isUsed)
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

    @Override
    public void printStat(String name) {
        System.out.printf("%s timer %d%n", name, TIME);
        System.out.printf("%s isUsed %s%n", name, isUsed);
        room = new Room("NOT IMPLEMENTED");
        System.out.printf("%s room %s%n", name, room.getName());
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
        return TIME;
    }

    @Override
    public boolean isUsed() {
        return isUsed;
    }

    @Override
    public void acceptView(ArrayList<IVItems> l) {
        l.add(new VCheese(this));
    }
}
