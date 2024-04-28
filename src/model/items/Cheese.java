package model.items;

import java.util.ArrayList;

import controller.TimedObject;
import controller.Timer;
import model.ERoomEffects;
import model.ITimer;
import model.Room;
import model.player.Player;
import model.player.Student;
import model.player.Teacher;
import test.IPrintStat;

public class Cheese implements IItem, ITimer, IPrintStat {
    private Timer timer;
    private boolean isUsed;
    private Room room;
    private String name;
    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return "Cheese@"+Integer.toString(this.hashCode()).substring(0, 4);
    }

    /*
     * Konstruktor
     * Paraméterként kapja a Timer osztályt amit a kontroller kezel
     * Majd ezt a refernciát eltárolja és a timerbe is beleteszi magát
     */
    public Cheese(String s, Timer t) {
        name = s;
        timer = t;
        t.addItem(this);
    }
    public Cheese(Timer t) {
        timer = t;
        t.addItem(this);
    }

    /*
     * Csak a tesztekhez kell
     * Elindítja a cheese timerét
     */
    public void startTimer() {
        //System.out.println("Cheese : startTimer(" + this.toString() + ", 2) -> " + timer.toString());
        timer.startTimer(this, 2);
    }

    @Override
    public void useItem(Player p) {
        if(isUsed) 
            return;
        
        //System.out.println("Cheese : startTimer(" + this.toString() + ", 2) -> " + timer.toString());
        timer.startTimer(this, 2);
        
        room = p.getRoom();

        //System.out.println("Cheese : addEffect(POISONED) -> " + room.toString());
        room.addEffect(ERoomEffects.POISONED);

        //System.out.println("Cheese : removeItem(" + this.toString() + ") -> " + p.toString());
        p.removeItem(this);

        //System.out.println("Cheese : addItem(" + this.toString() + ") -> " + room.toString());
        room.addItem(this);
    }

    @Override
    public void pickUp(Student s) {
        //System.out.println("Cheese : addItem( " + this.toString() + ") -> " + s.toString());
        s.addItem(this);
    }

    @Override
    public void pickUp(Teacher t) {
        //System.out.println("Cheese : addItem( " + this.toString() + ") -> " + t.toString());
        t.addItem(this);
    }

    @Override
    public void dropItem(Player p) {
        //System.out.println("Cheese : removeItem( " + this.toString() + ") -> " + p.toString());
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
        //System.out.println("Cheese : removeEffect(POISONED) -> " + room.toString());
        room.removeEffect(ERoomEffects.POISONED);
    }
    
    @Override
    public void printStat(String name) {
        int myTime = 0;
        for (TimedObject to : timer.getList()) {
            if(to.getObject().equals(this)){
                myTime = to.getTime();
            }
        }
        System.out.printf("%s timer %d%n",name, myTime);
        System.out.printf("%s isUsed %s%n",name, isUsed);
        room = new Room("NOT IMPLEMENTED");
        System.out.printf("%s room %s%n",name,room.getName());
    }
    @Override
    public void statesOptions() {
        System.out.printf("\tSTATES");
    }
        @Override
    public void setState(ArrayList<String> args){}
}
