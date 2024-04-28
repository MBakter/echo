package model.items;

import controller.Timer;
import model.*;
import model.player.Player;
import model.player.Student;
import model.player.Teacher;

public class Beer implements IItem, ITimedEntity {
    private ITimer timer;
    private EBeerState state; //Default: INACTIVE
    private static int TIME = 5;

    @Override
    public String toString(){
        return "Beer@"+Integer.toString(this.hashCode()).substring(0, 4);
    }

    /*
     * Konstruktor
     * Paraméterként kapja a Timer osztályt amit a kontroller kezel
     * Majd ezt a refernciát eltárolja és a timerbe is beleteszi magát
     */
    public Beer(ITimer t) {
        timer = t;
        t.addItem(this);
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
    

}
