
package model.items;

import model.ERoomEffects;
import model.Room;
import model.player.Player;
import model.player.Student;
import model.player.Teacher;
import test.IPrintStat;

public class Transistor implements IItem , IPrintStat{
    private boolean active;
    private Room room;
    private Transistor pair;
    private String name;
    public String getName() {
        return name;
    }
    @Override
    public String toString(){
        return "Transistor@"+Integer.toString(this.hashCode()).substring(0, 4);
    }

    /*
     * Konstruktor
     */
    public Transistor(String s) {
        name = s;
    }
    public Transistor() {
    }
    public void ActivateTransistor() {
        //System.out.println("\t"+this+": ActivateTransistor called");
        active = true;
    }
    public void PairTransistor(Transistor t2) {
        //System.out.println("\t"+this+": PairTransistor called");
        pair = t2;

        //System.out.println("\t"+this+" : setPair( " + this.toString() + ") -> " + t2.toString());
        t2.setPair(this);

    }
    private void setPair(Transistor transistor) {
        //System.out.println("\t"+this+": setPair called");
        pair = transistor;
    }

    public void UnpairTransistor(Transistor t2) {
        //System.out.println("\t"+this+": UnpairTransistor called");
    }
    public void setRoom(Room r) {
        //System.out.println("\t"+this+": setRoom called");
        room = r;
    }
    public void deactivateTransistor(Player p) {
        //System.out.println("\t"+this+": deactivateTransistor called");
        active = false;
    }

    @Override
    public void useItem(Player p) {
        return;
    }

    @Override
    public void pickUp(Student s) {
        //System.out.println("Transistor : addItem( " + this.toString() + ") -> " + s.toString());
        if(pair != null) {
            room.removeEffect(ERoomEffects.TRANSISTOR_INSIDE);
            room = null;
        }
        s.addItem(this);
    }

    @Override
    public void pickUp(Teacher t) {
        //System.out.println("Transistor : addItem( " + this.toString() + ") -> " + t.toString());
        t.addItem(this);
    }

    @Override
    public void dropItem(Player p) {
        //System.out.println("\t"+this+": dropItem called");
        
        if (pair != null){
            if (pair.room != null && active){
                //System.out.println("\t"+this+" : teleport( " + p.toString() + ") -> " + this.toString());
                teleport(p);

                //System.out.println("\t"+this+" : deactivateTransistor( " + p.toString() + ") -> " + this.toString());
                deactivateTransistor(p);
            }

        }

        room = p.getRoom();
        //System.out.println("\t"+this+" : removeItem( " + this.toString() + ") -> " + p.toString());
        p.removeItem(this);

    }

    private void teleport(Player p) {
        //System.out.println("\t"+this+": teleport called");

        //System.out.println("\t"+this+" : move( " + pair.room.toString() + ") -> " + p.toString());
        p.move(pair.room);
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
    public void printStat(String gasdgag) {
        System.out.printf("%s pair %s", name, pair.getName());
        System.out.printf("%s active %s",name, active);
        System.out.printf("%s room %s", name, room.getName());
    }
    @Override
    public void statesOptions() {
        System.out.printf("\tSTATES");
    }
}
