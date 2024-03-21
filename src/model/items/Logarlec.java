package model.items;

import model.player.Player;
import model.player.Student;

public class Logarlec implements IItem {

    @Override
    public void useItem(Player p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'useItem'");
    }

    @Override
    public void pickUp(Player p) {
        p.addItem(this);
        
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
    public boolean TeacherAttackable(Student s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'TeacherAttackable'");
    }
    
}
