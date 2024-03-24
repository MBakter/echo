package model.items;

import model.player.Player;
import model.player.Student;

public class Logarlec implements IItem {

    @Override
    public String toString(){
        return "Logarlec@"+Integer.toString(this.hashCode()).substring(0, 4);
    }

    public Logarlec() {
        System.out.println("<<create>> " + this.toString());
    }

    @Override
    public void useItem(Player p) {
        /* Do nothing */
        return;
    }

    @Override
    public void pickUp(Player p) {
        System.out.println("Logarlec : addItem(" + this.toString() + ") -> " + p.toString());
        p.addItem(this);
    }

    @Override
    public void dropItem(Player p) {
        System.out.println("Logarlec : removeItem(" + this.toString() + ") -> " + p.toString());
        p.removeItem(this);
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
    
}
