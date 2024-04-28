package model.items;

import java.util.ArrayList;

import model.player.Player;
import model.player.Student;
import model.player.Teacher;
import test.IPrintStat;

public class Logarlec implements IItem, IPrintStat {

    private boolean fake = false;
    private String name;
    public String getName() {
        return name;
    }
    @Override
    public String toString(){
        return "Logarlec@"+Integer.toString(this.hashCode()).substring(0, 4);
    }

    /*
     * Konstruktor
     */
    public Logarlec(String s) {
        name = s;
    }
    public Logarlec() {
    }

    @Override
    public void useItem(Player p) {
        /* Do nothing */
        return;
    }

    @Override
    public void pickUp(Student s) {
        //System.out.println("Logarlec : addItem( " + this.toString() + ") -> " + s.toString());
        s.addItem(this);
        //System.out.println("Logarlec : endGame(VICTORY) -> Controller");
    }

    @Override
    public void pickUp(Teacher t) {
        //System.out.println("Logarlec : addItem( " + this.toString() + ") -> " + t.toString());
        t.addItem(this);
    }

    @Override
    public void dropItem(Player p) {
        //System.out.println("Logarlec : removeItem(" + this.toString() + ") -> " + p.toString());
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
    
    @Override
    public void printStat(String name) {
        System.out.printf("%s fake %s%n", name, fake);
    }
    @Override
    public void statesOptions() {
        System.out.printf("\tSTATES");
    }
        @Override
    public void setState(ArrayList<String> args){
        if (args.get(1).equals("fake")) {
            if (args.get(2).equals("true"))
                fake = true;
            if (args.get(2).equals("false"))
                fake = false;
        }
    }
}
