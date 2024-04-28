package model.items;

import controller.TimedObject;
import controller.Timer;
import model.ITimer;
import model.Room;
import model.player.EPlayerState;
import model.player.Player;
import model.player.Student;
import model.player.Teacher;
import test.IPrintStat;

public class Sponge implements IItem, ITimer, IPrintStat {
    private controller.Timer timer;
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
        t.addItem(this);
    }
    public Sponge(Timer t) {
        timer = t;
        t.addItem(this);
    }
    @Override
    public void useItem(Player p) {
        /* Do nothing */
        return;
    }

    @Override
    public void pickUp(Student s) {
        //System.out.println("Sponge : startTimer(" + this.toString() + ", 2) -> " + timer.toString());
        timer.startTimer(this, 2);

        functional = true;
        //System.out.println("Sponge : setFunctional -> " + (functional ? "true" : "false"));
        
        //System.out.println("Sponge : addItem(" + this.toString() + ") -> " + s.toString());
        s.addItem(this);
    }

    @Override
    public void pickUp(Teacher t) {
        //System.out.println("Sponge : addItem( " + this.toString() + ") -> " + t.toString());
        t.addItem(this);
    }

    @Override
    public void dropItem(Player p) {
        //System.out.println("Sponge : addItem(" + this.toString() + ") -> " + p.toString());
        p.removeItem(this);
        
        //System.out.println("Sponge : pauseTimer(" + this.toString() + ") -> " + timer.toString());
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
        for (Teacher t : r.getTeachers()) {
            //System.out.println("Sponge : setState(UNCONSCIOUS) -> " + t.toString());
            t.setState(EPlayerState.UNCONSCIOUS);
        }

        return true;
    }

    @Override
    public void timerEnd() {
        functional = false;
        //System.out.println("Sponge : setFunctional -> " + (functional ? "true" : "false"));
    }

    @Override
    public void printStat(String name) {
                int myTime = 0;
        for (TimedObject to : timer.getList()) {
            if (to.getObject().equals(this)) {
                myTime = to.getTime();
            }
        }
        System.out.printf("%s timer %d%n", name, myTime);
        System.out.printf("%s functional %s%n", name, functional);

    }
    @Override
    public void statesOptions() {
        System.out.printf("\tSTATES");
    }
}
