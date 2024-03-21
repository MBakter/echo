package model;

public class Beer implements IItem, ITimer {
    private controller.Timer timer;
    private EBeerState state; //Default: INACTIVE

    @Override
    public void useItem(Player p) {
        timer.startTimer();
        state = EBeerState.RUNNING;
    }

    @Override
    public void pickUp(Player p) {
        p.addItem(this);
    }

    @Override
    public void dropItem(Player p) {
        timer.pauseTimer();
        p.removeItem(this);
    }

    @Override
    public boolean TeacherAttacked(Student s) {
        switch (state) {
            case INACTIVE:
                timer.startTimer();
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
