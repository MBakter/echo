package model;

public class Transistor implements IItem {
    private boolean active;
    private Room room;
    private Transistor pair;

    public void ActivateTransistor() {}
    public void PairTransistor(Transistor t2) {}
    public void UnpairTransistor(Transistor t2) {}
    public void setRoom(Room r) {}
    public void UnactivateTransistor() {}

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

}
