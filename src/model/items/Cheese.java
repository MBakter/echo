package model.items;

import model.ERoomEffects;
import model.ITimer;
import model.Room;
import model.player.Player;
import model.player.Student;

public class Cheese implements IItem, ITimer {
    private controller.Timer timer;
    private boolean isUsed;
    private Room room;

    @Override
    public void useItem(Player p) {
        if(isUsed) 
            return;
        timer.startTimer();
        room = p.getRoom();
        room.addEffect(ERoomEffects.POISONED);
    }

    @Override
    public void pickUp(Player p) {
        p.addItem(this);
    }

    @Override
    public void dropItem(Player p) {
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
    }
    
    
}
