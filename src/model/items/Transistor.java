
package model.items;

import java.util.ArrayList;

import model.ERoomEffects;
import model.Room;
import model.player.*;
import test.IPrintStat;
import view.IVItems;
import view.VTransistor;

public class Transistor implements IItem, IPrintStat, IVMTransistor {
    private boolean active;
    private Room room;
    private Transistor pair;
    private String name;

    @Override
    public String toString() {
        return "Transistor@" + Integer.toString(this.hashCode()).substring(0, 4);
    }

    /*
     * Konstruktor
     */
    public Transistor(String s) {
        name = s;
    }

    public String getName() {
        if (name == null)
            return "";
        return name;
    }

    public Transistor() {

    }

    public void ActivateTransistor() {
        active = true;
    }

    public void PairTransistor(Transistor t2) {
        if(t2.equals(this))
            return;
        pair = t2;
        t2.setPair(this);

    }

    private void setPair(Transistor transistor) {
        pair = transistor;
    }

    public void UnpairTransistor(Transistor t2) {
        t2.pair = null;
        pair = null;
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
        room = null;
        if (pair != null) {
            s.getRoom().removeEffect(ERoomEffects.TRANSISTOR_INSIDE);
            
        }
        s.addItem(this);
        //setRoom(s.getRoom());
    }

    @Override
    public void pickUp(Teacher t) {
        t.addItem(this);
        setRoom(t.getRoom());
    }

    @Override
    public void pickUp(Cleaner c) {
        c.addItem(this);
        setRoom(c.getRoom());
    }

    @Override
    public void dropItem(Player p) {
        room = p.getRoom();
        p.removeItem(this);

        if (pair != null) {
            if (pair.room != null && active) {
                teleport(p);
                deactivateTransistor(p);
            }            
        }
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
        if (pair != null)
            System.out.printf("%s pair %s", name, pair.getName());
        else
            System.out.printf("%s pair %s", name, "");
        System.out.printf("%s active %s", name, active);
        String roomName = "";
        if (room != null)
            roomName = room.getName();
        System.out.printf("%s room %s", name, roomName);
    }

    @Override
    public void statesOptions() {
        System.out.printf("\tSTATES");
    }

    @Override
    public void setState(ArrayList<String> args) {
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void acceptView(ArrayList<IVItems> l) {
        l.add(new VTransistor(this));
    }

    @Override
    public String getPairName() {
        if (pair != null) {
            return pair.getName();
        } else {
            return "No pair!";
        }

    }
}
