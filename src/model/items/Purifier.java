package model.items;

import java.util.ArrayList;

import model.player.*;
import test.IPrintStat;

public class Purifier implements IItem, IPrintStat{
    private String name;
    private boolean isUsed;

    public Purifier(String s) {
        name = s;
    }

    @Override
    public void useItem(Player p) {
        if(isUsed)
            return;
        p.getRoom().purifyRoom();
        isUsed = true;
    }

    @Override
    public void pickUp(Student s) {
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
    public String getName() {
        return name;
    }

    @Override
    public void printStat(String name) {
        System.out.printf("%s isUsed %s%n",name, isUsed);
    }

    @Override
    public void statesOptions() {
        System.out.printf("\tisUsed%n");
    }

    @Override
    public void setState(ArrayList<String> args) {
        if(args.get(2).equals("true"))
            isUsed = true;
        if(args.get(2).equals("false"))
            isUsed = false;
    }
    
}
