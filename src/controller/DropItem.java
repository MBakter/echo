package controller;

import java.util.Map;

import model.*;
import model.items.*;
import model.player.*;

class DIfill {
    public static void fill(Map<Integer, ITestcase> l){
        int i = 1;
        l.put(i++, new student_drop_mask());
        l.put(i++, new student_drop_cheese());
        l.put(i++, new student_drop_beer());
        l.put(i++, new student_drop_tvsz());
        l.put(i++, new student_drop_transistor_paired());
        l.put(i++, new student_drop_transistor_unpaired());
        l.put(i++, new student_drop_transistor_paired_notplacedpair());
        l.put(i++, new teacher_drop_mask());
        l.put(i++, new teacher_drop_cheese());
        l.put(i++, new teacher_drop_beer());
        l.put(i++, new teacher_drop_tvsz());
    }
}

class student_drop_mask implements ITestcase{

    public void runTest() {

        System.out.println("\n---\tSetup\t---\n");
        Mask m = new Mask();
        Room r = new Room();
        Student s = new Student();

        System.out.println("Controller : addItem(" + m.toString() + ") -> " + s.toString());
        s.addItem(m);

        System.out.println("Controller : setRoom(" + r.toString() + ") -> " + s.toString());
        s.setRoom(r);

        System.out.println("\n---\tStart of test\t---\n");

        System.out.println("Controller : dropItem(" + m.toString() + ") -> " + s.toString());
        s.dropItem(m);

    }
    public String testTitle() {
        return "Student Drops Mask";
    };
}

class student_drop_cheese implements ITestcase{

    public void runTest() {

        System.out.println("\n---\tSetup\t---\n");
        Cheese c = new Cheese();
        Room r = new Room();
        Student s = new Student();

        System.out.println("Controller : addItem(" + c.toString() + ") -> " + s.toString());
        s.addItem(c);

        System.out.println("Controller : setRoom(" + r.toString() + ") -> " + s.toString());
        s.setRoom(r);

        System.out.println("\n---\tStart of test\t---\n");

        System.out.println("Controller : dropItem(" + c.toString() + ") -> " + s.toString());
        s.dropItem(c);

    }
    public String testTitle() {
        return "Student Drops Cheese";
    };
}

class student_drop_beer implements ITestcase{

    public void runTest() {


        System.out.println("\n---\tSetup\t---\n");
        Timer t = new Timer();
        Beer b = new Beer();
        Room r = new Room();
        Student s = new Student();

        System.out.println("Controller : addItem(" + b.toString() + ") -> " + s.toString());
        s.addItem(b);

        System.out.println("Controller : setRoom(" + r.toString() + ") -> " + s.toString());
        s.setRoom(r);

        System.out.println("\n---\tStart of test\t---\n");

        System.out.println("Controller : dropItem(" + b.toString() + ") -> " + s.toString());
        s.dropItem(b);

    }
    public String testTitle() {
        return "Student Drops Beer";
    };
}

class student_drop_tvsz implements ITestcase{

    public void runTest() {

        System.out.println("\n---\tSetup\t---\n");
        TVSZ tvsz = new TVSZ();
        Room r = new Room();
        Student s = new Student();

        System.out.println("Controller : addItem(" + tvsz.toString() + ") -> " + s.toString());
        s.addItem(tvsz);

        System.out.println("Controller : setRoom(" + r.toString() + ") -> " + s.toString());
        s.setRoom(r);

        System.out.println("\n---\tStart of test\t---\n");

        System.out.println("Controller : dropItem(" + tvsz.toString() + ") -> " + s.toString());
        s.dropItem(tvsz);

    }
    public String testTitle() {
        return "Student Drops TVSZ";
    };
}

class student_drop_transistor_paired implements ITestcase{

    public void runTest() {

        System.out.println("\n---\tSetup\t---\n");
        Transistor t1 = new Transistor();
        Transistor t2 = new Transistor();
        Room r1 = new Room();
        Room r2= new Room();
        Student s = new Student();

        System.out.println("Controller : PairTransistor(" + t1.toString() + ") -> " + t2.toString());
        t2.PairTransistor(t1);

        System.out.println("Controller : addItem(" + t1.toString() + ") -> " + s.toString());
        s.addItem(t1);
       
        System.out.println("Controller : setRoom(" + r1.toString() + ") -> " + t1.toString());
        t1.setRoom(r1);

        System.out.println("Controller : setRoom(" + r2.toString() + ") -> " + s.toString());
        s.setRoom(r2);

        System.out.println("Controller : ActivateTransistor() -> " + t2.toString());
        t2.ActivateTransistor();

        System.out.println("\n---\tStart of test\t---\n");

        System.out.println("Controller : dropItem(" + t2.toString() + ") -> " + s.toString());
        s.dropItem(t2);

    }
    public String testTitle() {
        return "Student places paired active, pair is placed transistor";
    };
}

class student_drop_transistor_paired_notplacedpair implements ITestcase{

    public void runTest() {

        System.out.println("\n---\tSetup\t---\n");
        Transistor t1 = new Transistor();
        Transistor t2 = new Transistor();
        Room r1 = new Room();
        Room r2= new Room();
        Student s = new Student();

        System.out.println("Controller : PairTransistor(" + t1.toString() + ") -> " + t2.toString());
        t2.PairTransistor(t1);

        System.out.println("Controller : addItem(" + t1.toString() + ") -> " + s.toString());
        s.addItem(t1);
       
        // System.out.println("Controller : setRoom(" + r1.toString() + ") -> " + t1.toString());
        // t1.setRoom(r1);

        System.out.println("Controller : setRoom(" + r2.toString() + ") -> " + s.toString());
        s.setRoom(r2);

        System.out.println("Controller : ActivateTransistor() -> " + t2.toString());
        t2.ActivateTransistor();

        System.out.println("\n---\tStart of test\t---\n");

        System.out.println("Controller : dropItem(" + t2.toString() + ") -> " + s.toString());
        s.dropItem(t2);

    }
    public String testTitle() {
        return "Student places paired active transistor, pair is notplaced";
    };
}

class student_drop_transistor_unpaired implements ITestcase{

    public void runTest() {

        System.out.println("\n---\tSetup\t---\n");
        Transistor t1 = new Transistor();
        Transistor t2 = new Transistor();
        Room r1 = new Room();
        Room r2= new Room();
        Student s = new Student();


        System.out.println("Controller : addItem(" + t1.toString() + ") -> " + s.toString());
        s.addItem(t1);
       
        System.out.println("Controller : setRoom(" + r1.toString() + ") -> " + t1.toString());
        t1.setRoom(r1);

        System.out.println("Controller : setRoom(" + r2.toString() + ") -> " + s.toString());
        s.setRoom(r2);

        System.out.println("Controller : ActivateTransistor() -> " + t2.toString());
        t2.ActivateTransistor();

        System.out.println("\n---\tStart of test\t---\n");

        System.out.println("Controller : dropItem(" + t2.toString() + ") -> " + s.toString());
        s.dropItem(t2);

    }
    public String testTitle() {
        return "Student Drops Unpaired Transistor";
    };
}

class teacher_drop_mask implements ITestcase{

    public void runTest() {

        System.out.println("\n---\tSetup\t---\n");
        Mask m = new Mask();
        Room r = new Room();
        Teacher s = new Teacher();

        System.out.println("Controller : addItem(" + m.toString() + ") -> " + s.toString());
        s.addItem(m);

        System.out.println("Controller : setRoom(" + r.toString() + ") -> " + s.toString());
        s.setRoom(r);

        System.out.println("\n---\tStart of test\t---\n");

        System.out.println("Controller : dropItem(" + m.toString() + ") -> " + s.toString());
        s.dropItem(m);

    }
    public String testTitle() {
        return "Teacher Drops Mask";
    };
}

class teacher_drop_cheese implements ITestcase{

    public void runTest() {

        System.out.println("\n---\tSetup\t---\n");
        Cheese c = new Cheese();
        Room r = new Room();
        Teacher s = new Teacher();

        System.out.println("Controller : addItem(" + c.toString() + ") -> " + s.toString());
        s.addItem(c);

        System.out.println("Controller : setRoom(" + r.toString() + ") -> " + s.toString());
        s.setRoom(r);

        System.out.println("\n---\tStart of test\t---\n");

        System.out.println("Controller : dropItem(" + c.toString() + ") -> " + s.toString());
        s.dropItem(c);

    }
    public String testTitle() {
        return "Teacher Drops Cheese";
    };
}

class teacher_drop_beer implements ITestcase{

    public void runTest() {

        System.out.println("\n---\tSetup\t---\n");
        Beer b = new Beer();
        Room r = new Room();
        Teacher s = new Teacher();

        System.out.println("Controller : addItem(" + b.toString() + ") -> " + s.toString());
        s.addItem(b);

        System.out.println("Controller : setRoom(" + r.toString() + ") -> " + s.toString());
        s.setRoom(r);

        System.out.println("\n---\tStart of test\t---\n");

        System.out.println("Controller : dropItem(" + b.toString() + ") -> " + s.toString());
        s.dropItem(b);

    }
    public String testTitle() {
        return "Teacher Drops Beer";
    };
}

class teacher_drop_tvsz implements ITestcase{

    public void runTest() {

        System.out.println("\n---\tSetup\t---\n");
        TVSZ tvsz = new TVSZ();
        Room r = new Room();
        Teacher s = new Teacher();

        System.out.println("Controller : addItem(" + tvsz.toString() + ") -> " + s.toString());
        s.addItem(tvsz);

        System.out.println("Controller : setRoom(" + r.toString() + ") -> " + s.toString());
        s.setRoom(r);

        System.out.println("\n---\tStart of test\t---\n");

        System.out.println("Controller : dropItem(" + tvsz.toString() + ") -> " + s.toString());
        s.dropItem(tvsz);

    }
    public String testTitle() {
        return "Teacher Drops TVSZ";
    };
}
