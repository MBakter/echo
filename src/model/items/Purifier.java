package model.items;

import model.player.*;

public class Purifier implements IItem{
    
    private boolean isUsed;

    public Purifier() {

    }

    @Override
    public void useItem(Player p) {
        if(isUsed)
            return;
        p.getRoom().purifyRoom();
        isUsed = true;
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
    public void pickUp(Cleaner c) {
        c.addItem(this);
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
    
}
