package controller;

import java.util.Map;

import model.*;
import model.items.*;
import model.player.*;


class UIfill {
    public static void fill(Map<Integer, ITestcase> l){
        int i = 1;
        l.put(i++, new student_pairs_transistor());
        l.put(i++, new student_activates_transistor());
        l.put(i++, new student_uses_beer());
        l.put(i++, new student_timerup_beer());
        l.put(i++, new student_uses_cheese());
        l.put(i++, new student_timerup_cheese());
    }
}
class student_pairs_transistor implements ITestcase{

    public void runTest() {

        System.out.println("\n---\tSetup\t---\n");
        Transistor t1 = new Transistor();
        Transistor t2 = new Transistor();
        Room r1 = new Room();
        Room r2= new Room();
        Student s = new Student();

        System.out.println("\n---\tStart of test\t---\n");
        System.out.println("Controller : PairTransistor(" + t1.toString() + ") -> " + t2.toString());
        t2.PairTransistor(t1);

    }
    public String testTitle() {
        return "Student pairs transistor";
    };
}

class student_activates_transistor implements ITestcase{

    public void runTest() {

        System.out.println("\n---\tSetup\t---\n");
        Transistor t1 = new Transistor();
        Transistor t2 = new Transistor();
        Room r1 = new Room();
        Room r2= new Room();
        Student s = new Student();

        System.out.println("\n---\tStart of test\t---\n");
        System.out.println("Controller : ActivateTransistor() -> " + t2.toString());
        t2.ActivateTransistor();

    }
    public String testTitle() {
        return "Student activates transistor";
    };
}

class student_uses_beer implements ITestcase{

    public void runTest() {

        System.out.println("\n---\tSetup\t---\n");
        Timer t = new Timer();
        Beer b = new Beer(t);
        Room r = new Room();
        Student s = new Student();

        System.out.println("Controller : addItem(" + b.toString() + ") -> " + s.toString());
        s.addItem(b);

        System.out.println("\n---\tStart of test\t---\n");

        System.out.println("Controller : useItem(" + b.toString() + ") -> " + s.toString());
        s.useItem(b);

    }
    public String testTitle() {
        return "Student Uses Beer";
    };
}

class student_timerup_beer implements ITestcase{

    public void runTest() {

        System.out.println("\n---\tSetup\t---\n");
        Timer t = new Timer();
        Beer b = new Beer(t);
        Room r = new Room();
        Student s = new Student();

        System.out.println("Controller : addItem(" + b.toString() + ") -> " + s.toString());
        s.addItem(b);

        System.out.println("\n---\tStart of test\t---\n");

        System.out.println("Timer : timerEnd() -> " + b.toString());
        b.timerEnd();

    }
    public String testTitle() {
        return "Beer timer is up";
    };
}

class student_uses_cheese implements ITestcase{

    public void runTest() {

        System.out.println("\n---\tSetup\t---\n");
        Timer t = new Timer();
        Cheese c = new Cheese(t);
        Room r = new Room();
        Student s = new Student();

        System.out.println("Controller : addItem(" + c.toString() + ") -> " + s.toString());
        s.addItem(c);

        System.out.println("Controller : addStudent(" + s.toString() + ") -> " + r.toString());
        r.addStudent(s);

        System.out.println("Controller : setRoom(" + r.toString() + ") -> " + s.toString());
        s.setRoom(r);

        System.out.println("\n---\tStart of test\t---\n");

        System.out.println("Controller : useItem(" + c.toString() + ") -> " + s.toString());
        s.useItem(c);


    }
    public String testTitle() {
        return "Student Uses Cheese";
    };
}

class student_timerup_cheese implements ITestcase{

    public void runTest() {

        System.out.println("\n---\tSetup\t---\n");
        Timer t = new Timer();
        Cheese c = new Cheese(t);
        Room r = new Room();
        Student s = new Student();

        System.out.println("Controller : addItem(" + c.toString() + ") -> " + s.toString());
        s.addItem(c);

        System.out.println("Controller : setRoom(" + r.toString() + ") -> " + s.toString());
        s.setRoom(r);

        System.out.println("\n---\tStart of test\t---\n");

        System.out.println("Timer : timerEnd() -> " + c.toString());
        c.timerEnd();


    }
    public String testTitle() {
        return "Cheese timer is up";
    };
}


