package model.items;

import controller.Timer;
import model.ITimer;
import model.player.Player;
import model.player.Student;

public class Mask implements IItem, ITimer {
    private controller.Timer timer;
    private boolean functional;
    private Player wearer;
    @Override
    public String toString(){
        return "Mask@"+Integer.toString(this.hashCode()).substring(0, 4);
    }

    /*
     * Konstruktor
     * Paraméterként kapja a Timer osztályt amit a kontroller kezel
     * Majd ezt a refernciát eltárolja és a timerbe is beleteszi magát
     */
    public Mask(Timer t) {
        System.out.println("<<create>> " + this.toString());
        timer = t;
        t.addItem(this);
        functional = true;
    }

    /*
     * Beállítja a hordozót a paraméterként kapott Playerre
     */
    public void setPlayer(Player p) {
        wearer = p;
        System.out.println("Mask : setWearer -> " + wearer.toString());
    }

    @Override
    public void useItem(Player p) {
        /* Do nothing */
        return;
    }

    @Override
    public void pickUp(Player p) {
        wearer = p;

        System.out.println("\t"+"Mask : addItem(" + this.toString() + ") -> " + p.toString());
        p.addItem(this);
    }

    @Override
    public void dropItem(Player p) {
        System.out.println("\t"+"Mask : pauseTimer(" + this.toString() + ") -> " + timer.toString());
        timer.pauseTimer(this);
        
        System.out.println("\t"+"Mask : removeItem( " + this.toString() + ") -> " + p.toString());
        p.removeItem(this);
    }

    @Override
    public boolean TeacherAttacked(Student s) {
        /* Do nothing */
        return false;
    }

    @Override
    public boolean RoomPoisoned(Student s) {

        System.out.println("Mask : functional: " + (functional == true ? "true" : "false"));

        if(!functional)
            return false;

        System.out.println("Mask : startTimer(" + this.toString() + ", 2) -> " + timer.toString());
        timer.startTimer(this, 2);
        return true;
    }

    @Override
    public void RoomCleanFromPoison(Student s) {
        System.out.println("Mask : pauseTimer(" + this.toString() + ") -> " + timer.toString());
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
        System.out.println("Mask : setFunctional -> " + (functional ? "true" : "false"));
    }

}
