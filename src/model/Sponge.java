package model;

public class Sponge implements IItem, ITimer {
    private controller.Timer timer;
    private boolean functional;

    @Override
    public void useItem(Player p) {
        /* Do nothing */
        return;
    }

    @Override
    public void pickUp(Player p) {
        timer.startTimer();
        functional = true;
        p.addItem(this);
    }

    @Override
    public void dropItem(Player p) {
        p.removeItem(this);
        timer.pauseTimer();
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
