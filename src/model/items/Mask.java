package model.items;

import java.util.ArrayList;

import controller.TimedObject;
import java.util.Random;

import model.*;
import model.items.view_model_items.IVMMask;
import model.player.*;
import test.IPrintStat;

public class Mask implements IItem, ITimedEntity, IPrintStat, IVMMask {
    private ITimer timer;
    private static int TIME = 4;
    private boolean fake;
    private boolean functional;
    private Student wearer;
    private String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Mask@" + Integer.toString(this.hashCode()).substring(0, 4);
    }

    /*
     * Konstruktor
     * Paraméterként kapja a Timer osztályt amit a kontroller kezel
     * Majd ezt a refernciát eltárolja és a timerbe is beleteszi magát
     */
    public Mask(String s, ITimer t) {
        name = s;
        timer = t;
        t.addEntity(this);
        functional = true;
    }

    public Mask(ITimer t) {
        timer = t;
        t.addEntity(this);
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

    @Override
    public void printStat(String name) {
        System.out.printf("%s timer %d%n", name, TIME);
        System.out.printf("%s functional %s%n", name, functional);
        System.out.printf("%s student %s%n", name, wearer);
        System.out.printf("%s fake %s%n", name, fake);
    }
    @Override
    public void statesOptions() {
        System.out.printf("\tSTATES");
    }
        @Override
    public void setState(ArrayList<String> args){
        if (args.get(1).equals("fake")) {
            if (args.get(2).equals("true"))
                fake = true;
            if (args.get(2).equals("false"))
                fake = false;
        }
    }

        @Override
        public Integer getTime() {
            return TIME;
        }

        @Override
        public boolean getFunctional() {
            return functional;
        }

        @Override
        public boolean getFake() {
            return fake;
        }
}
