package model.items;

import controller.Timer;
import model.ERoomEffects;
import model.ITimer;
import model.Room;
import model.player.Player;
import model.player.Student;

public class Cheese implements IItem, ITimer {
    private Timer timer;
    private boolean isUsed;
    private Room room;

    @Override
    public String toString(){
        return "Cheese@"+Integer.toString(this.hashCode()).substring(0, 4);
    }

    public Cheese(Timer t) {
        System.out.println("<<create>> " + this.toString());
        timer = t;
        t.addItem(this);
    }

    public void startTimer() {
        System.out.println("Cheese : startTimer(" + this.toString() + ", 2) -> " + timer.toString());
        timer.startTimer(this, 2);
    }

    @Override
    public void useItem(Player p) {
        if(isUsed) 
            return;
        startTimer();
        room = p.getRoom();

        System.out.println("Cheese : addEffect(POISONED) -> " + room.toString());
        room.addEffect(ERoomEffects.POISONED);
    }

    @Override
    public void pickUp(Player p) {
        System.out.println("Cheese : addItem( " + this.toString() + ") -> " + p.toString());
        p.addItem(this);
    }

    @Override
    public void dropItem(Player p) {
        System.out.println("Cheese : removeItem( " + this.toString() + ") -> " + p.toString());
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
        System.out.println("Cheese : removeEffect(POISONED) -> " + room.toString());
    }
    
    
}
