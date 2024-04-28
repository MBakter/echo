package model.items;

import model.player.Player;
import model.player.Student;
import model.player.Teacher;
import test.IPrintStat;

public class TVSZ implements IItem, IPrintStat {
    private int hitpoints;
    private boolean fake;
    private String name;
    public String getName() {
        return name;
    }
    @Override
    public String toString(){
        return "TVSZ@"+Integer.toString(this.hashCode()).substring(0, 4);
    }

    /*
     * Konstruktor
     */
    public TVSZ(String s) {
        name = s;
        hitpoints = 3;
    }
    public TVSZ() {
        hitpoints = 3;
    }
    @Override
    public void useItem(Player p) {
        /* Do nothing */
        return;
    }

    @Override
    public void pickUp(Student s) {
        //System.out.println("TVSZ : addItem( " + this.toString() + ") -> " + s.toString());
        s.addItem(this);
    }

    @Override
    public void pickUp(Teacher t) {
        //System.out.println("TVSZ : addItem( " + this.toString() + ") -> " + t.toString());
        t.addItem(this);
    }

    @Override
    public void dropItem(Player p) {
        //System.out.println("TVSZ : removeItem(" + this.toString() + ") -> " + p.toString());
        p.removeItem(this);
    }

    @Override
    public boolean TeacherAttacked(Student s) {
        if(hitpoints > 0) {
            //System.out.print("TVSZ : hitpoints => " + hitpoints);
            hitpoints--;
            //System.out.println(" -> " + hitpoints);
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

    @Override
    public void printStat(String name) {
        System.out.printf("%s hitPoint %d%n",name,hitpoints);
        System.out.printf("%s fake %s%n",name,fake);
    }
    @Override
    public void statesOptions() {
        System.out.printf("\tSTATES");
    }
}
