package model.items;

import controller.TimedObject;
import controller.Timer;
import model.ITimer;
import model.player.Player;
import model.player.Student;
import model.player.Teacher;
import test.IPrintStat;

public class Beer implements IItem, ITimer, IPrintStat {
    private Timer timer;
    private EBeerState state; // Default: INACTIVE
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
        t.addItem(this);
    }

    public Beer(Timer t) {
        timer = t;
        t.addItem(this);
    }

    @Override
    public void useItem(Player p) {
        // System.out.println("Beer : startTimer(" + this.toString() + ", 2) -> " +
        // timer.toString());
        timer.startTimer(this, 2);

        state = EBeerState.RUNNING;
        // System.out.println("Beer : setState -> " + state.toString());
    }

    @Override
    public void pickUp(Student s) {
        // System.out.println("Beer : addItem( " + this.toString() + ") -> " +
        // s.toString());
        s.addItem(this);
    }

    @Override
    public void pickUp(Teacher t) {
        // System.out.println("Beer : addItem( " + this.toString() + ") -> " +
        // t.toString());
        t.addItem(this);
    }

    @Override
    public void dropItem(Player p) {
        // System.out.println("Beer : pauseTimer(" + this.toString() + ") -> " +
        // timer.toString());
        timer.pauseTimer(this);

        // System.out.println("Beer : removeItem( " + this.toString() + ") -> " +
        // p.toString());
        p.removeItem(this);
    }

    @Override
    public boolean TeacherAttacked(Student s) {
        switch (state) {
            case INACTIVE:
                // System.out.println("Beer : startTimer(" + this.toString() + ", 2) -> " +
                // timer.toString());
                timer.startTimer(this, 2);
                state = EBeerState.RUNNING;
                // System.out.println("Beer : state -> " + state.toString());
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
        // System.out.println("Beer : setState -> " + state.toString());
    }

    @Override
    public void PrintStat(String name) {
        int myTime = 0;
        for (TimedObject to : timer.getList()) {
            if (to.getObject().equals(this)) {
                myTime = to.getTime();
            }
        }
        System.out.printf("%s timer %d%n", name, myTime);
        System.out.printf("%s state %s%n", name, state);
    }
}
