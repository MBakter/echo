package model;

public class Mask implements IItem, ITimer {
    private controller.Timer timer;
    private boolean functional;
    private Player wearer;

    @Override
    public void useItem(Player p) {
        /* Do nothing */
        return;
    }

    @Override
    public void pickUp(Player p) {
        wearer = p;
        p.addItem(this);
    }

    @Override
    public void dropItem(Player p) {
        timer.pauseTimer();
        p.removeItem(this);
    }

    @Override
    public boolean TeacherAttacked(Student s) {
        /* Do nothing */
        return false;
    }

    @Override
    public boolean RoomPoisoned(Student s) {
        if(!functional)
            return false;
        timer.startTimer();
        return true;
    }

    @Override
    public void RoomCleanFromPoison(Student s) {
        timer.pauseTimer();
    }

    @Override
    public boolean TeacherAttackable(Student s) {
        /* Do nothing */
        return false;
    }

    @Override
    public void timerEnd() {
        functional = false;
    }

}
