package model.items;

import controller.TimedObject;
import controller.Timer;
import model.ITimer;
import model.player.Player;
import model.player.Student;
import model.player.Teacher;
import test.IPrintStat;

public class Mask implements IItem, ITimer, IPrintStat {
    private controller.Timer timer;
    private boolean functional;
    private boolean fake;
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
    public Mask(String s, Timer t) {
        name = s;
        timer = t;
        t.addItem(this);
        functional = true;
    }

    public Mask(Timer t) {
        timer = t;
        t.addItem(this);
        functional = true;
    }

    /*
     * Beállítja a hordozót a paraméterként kapott Studentre
     */
    public void setWearer(Student s) {
        wearer = s;
        // System.out.println("Mask : setWearer -> " + wearer.toString());
    }

    @Override
    public void useItem(Player p) {
        /* Do nothing */
        return;
    }

    @Override
    public void pickUp(Student s) {
        wearer = s;

        // System.out.println("Mask : addItem( " + this.toString() + ") -> " +
        // s.toString());
        s.addItem(this);
    }

    @Override
    public void pickUp(Teacher t) {
        // System.out.println("Mask : addItem( " + this.toString() + ") -> " +
        // t.toString());
        t.addItem(this);
    }

    @Override
    public void dropItem(Player p) {
        // System.out.println("\t"+"Mask : pauseTimer(" + this.toString() + ") -> " +
        // timer.toString());
        timer.pauseTimer(this);

        // System.out.println("\t"+"Mask : removeItem( " + this.toString() + ") -> " +
        // p.toString());
        p.removeItem(this);
    }

    @Override
    public boolean TeacherAttacked(Student s) {
        /* Do nothing */
        return false;
    }

    @Override
    public boolean RoomPoisoned(Student s) {

        // System.out.println("Mask : functional: " + (functional == true ? "true" :
        // "false"));

        if (!functional)
            return false;

        // System.out.println("Mask : startTimer(" + this.toString() + ", 2) -> " +
        // timer.toString());
        timer.startTimer(this, 2);
        return true;
    }

    @Override
    public void RoomCleanFromPoison(Student s) {
        // System.out.println("Mask : pauseTimer(" + this.toString() + ") -> " +
        // timer.toString());
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
        // System.out.println("Mask : setFunctional -> " + (functional ? "true" :
        // "false"));
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
        System.out.printf("%s student %s%n", name, wearer);
        System.out.printf("%s fake %s%n", name, fake);
    }
    @Override
    public void statesOptions() {
        System.out.printf("\tSTATES");
    }
}
