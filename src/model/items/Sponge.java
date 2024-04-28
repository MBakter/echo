package model.items;

import controller.Timer;
import model.*;
import model.player.EPlayerState;
import model.player.Player;
import model.player.Student;
import model.player.Teacher;

public class Sponge implements IItem, ITimedEntity {
    private ITimer timer;
    private boolean functional;

    @Override
    public String toString(){
        return "Sponge@"+Integer.toString(this.hashCode()).substring(0, 4);
    }

    /*
     * Konstruktor
     * Paraméterként kapja a Timer osztályt amit a kontroller kezel
     * Majd ezt a refernciát eltárolja és a timerbe is beleteszi magát
     */
    public Sponge(ITimer t) {
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
        timer.startTimer(this, 2);

        functional = true;
        
        s.addItem(this);
    }

    @Override
    public void pickUp(Teacher t) {
        t.addItem(this);
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

}
