package controller.DropItem;
import controller.ITestcase;
import model.*;
import model.items.*;
import model.player.*;

public class StudentDropsMask implements ITestcase{
    public void printFunction(String args){
        System.out.format("[%s]", args);
    }
    public void runTest() {
        //System.out.println("WOWOWOWO");
        Student s = new Student();
        Room r = new Room();
        Mask m = new Mask(new controller.Timer());

        printFunction("s");r.addStudent(s);
        //System.out.print("m:Mask");
        printFunction("m");s.addItem(m);
        printFunction("s");s.setRoom(r);
        //System.out.println("m");

        printFunction("m");s.dropItem(m);
        System.out.println("");
    }
    public String testTitle() {
        return "Student Drops Mask";
    };
}


