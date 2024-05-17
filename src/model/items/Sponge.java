package model.items;

import java.util.ArrayList;

import controller.TimedObject;
import controller.Timer;
import model.*;
import model.items.view_model_items.IVMSponge;
import model.player.*;
import test.IPrintStat;
public class Sponge implements IItem, ITimedEntity ,IPrintStat, IVMSponge{
    private ITimer timer;
    private static int TIME = 4;
    private boolean functional;
    private String name;
    public String getName() {
        return name;
    }
    @Override
    public String toString(){
        return "Sponge@"+Integer.toString(this.hashCode()).substring(0, 4);
    }

    /*
     * Konstruktor
     * Paraméterként kapja a Timer osztályt amit a kontroller kezel
     * Majd ezt a refernciát eltárolja és a timerbe is beleteszi magát
     */
    public Sponge(String s, Timer t) {
        name = s;
        timer = t;
        t.addEntity(this);
    }
    public Sponge(Timer t) {
        timer = t;
        t.addEntity(this);
    }
    @Override
    public void useItem(Player p) {
        /* Do nothing */
        return;
    }

    @Override
    public void pickUp(Student s) {
        timer.startTimer(this, TIME);

        functional = true;
        
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
        
        timer.pauseTimer(this);
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
        if(!functional)
            return false;

        Room r = s.getRoom();
        for (Teacher t : r.getTeachers()) 
            t.setState(EPlayerState.UNCONSCIOUS);
        
        return true;
    }

    @Override
    public void timerEnd() {
        functional = false;
    }

    @Override
    public void printStat(String name) {
        System.out.printf("%s timer %d%n", name, TIME);
        System.out.printf("%s functional %s%n", name, functional);

    }
    @Override
    public void statesOptions() {
        System.out.printf("\tSTATES");
    }
        @Override
    public void setState(ArrayList<String> args){}
        @Override
        public Integer getIime() {
            return TIME;
        }
        @Override
        public boolean getFunctional() {
            return functional;
        }
}
