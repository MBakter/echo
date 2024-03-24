package model.items;

import model.ERoomEffects;
import model.Room;
import model.player.Player;
import model.player.Student;

public class Transistor implements IItem {
    private boolean active;
    private Room room;
    private Transistor pair;

    @Override
    public String toString(){
        return "Transistor@"+Integer.toString(this.hashCode()).substring(0, 4);
    }

    public Transistor() {
        System.out.println("<<create>> " + this.toString());
    }

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

    //Külön kéne választani a pickUp(Student) és pickUp(Teacher)-re, hogy tanár ne vehesse fel a párosított tranzisztort v.
    //legyen úgy, hogy felveheti majd ledobhatja valahova ezzel megszivatva a hallgatókat...
    @Override
    public void pickUp(Player p) {
        if(pair != null) {
            room.removeEffect(ERoomEffects.TRANSISTOR_INSIDE);
            room = null;
        }
        p.addItem(this);
    }

    @Override
    public void dropItem(Player p) {
        p.removeItem(this);
        room = p.getRoom();
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
