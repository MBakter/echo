package model.items;

import model.player.Player;
import model.player.Student;

public class TVSZ implements IItem {
    private int hitpoints;

    @Override
    public String toString(){
        return "TVSZ@"+Integer.toString(this.hashCode()).substring(0, 4);
    }

    public TVSZ() {
        System.out.println("<<create>> " + this.toString());
        hitpoints = 3;
    }

    @Override
    public void useItem(Player p) {
        /* Do nothing */
        return;
    }

    @Override
    public void pickUp(Player p) {
        System.out.println("TVSZ : addItem(" + this.toString() + ") -> " + p.toString());
        p.addItem(this);
    }

    @Override
    public void dropItem(Player p) {
        System.out.println("TVSZ : removeItem(" + this.toString() + ") -> " + p.toString());
        p.removeItem(this);
    }

    @Override
    public boolean TeacherAttacked(Student s) {
        if(hitpoints > 0) {
            System.out.print("TVSZ : hitpoints => " + hitpoints);
            hitpoints--;
            System.out.println(" -> " + hitpoints);
            return true;
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


}
