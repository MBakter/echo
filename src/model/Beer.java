package model;

public class Beer implements IItem, ITimer {
    private controller.Timer timer;
    private EBeerState state;

    void setState(EBeerState s) {}

    @Override
    public void useItem(Player p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'useItem'");
    }

    @Override
    public void pickUp(Player p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pickUp'");
    }

    @Override
    public void dropItem(Player p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dropItem'");
    }

    @Override
    public boolean TeacherAttacked(Student s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'TeacherAttacked'");
    }

    @Override
    public boolean RoomPoisoned(Student s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'RoomPoisoned'");
    }

    @Override
    public void RoomCleanFromPoison(Student s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'RoomCleanFromPoison'");
    }

    @Override
    public void TeacherAttackable(Student s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'TeacherAttackable'");
    }

    @Override
    public void timerEnd() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'timerEnd'");
    }
    

}
