
package model.items;

import java.util.ArrayList;

import model.ERoomEffects;
import model.Room;
import model.player.*;
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
        active = true;
    }
    public void PairTransistor(Transistor t2) {
        pair = t2;
        t2.setPair(this);

    }
    private void setPair(Transistor transistor) {
        pair = transistor;
    }

    public void UnpairTransistor(Transistor t2) {
    }
    public void setRoom(Room r) {
        room = r;
    }
    public void deactivateTransistor(Player p) {
        active = false;
    }

    @Override
    public void useItem(Player p) {
        return;
    }

    @Override
    public void pickUp(Student s) {
        if(pair != null) {
            room.removeEffect(ERoomEffects.TRANSISTOR_INSIDE);
            room = null;
        }
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
        
        if (pair != null){
            if (pair.room != null && active){
                teleport(p);
                deactivateTransistor(p);
            }

        }

        room = p.getRoom();
        p.removeItem(this);

    }

    private void teleport(Player p) {
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
        @Override
    public void setState(ArrayList<String> args){}
}
