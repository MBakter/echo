package controller;

import model.player.*;
import model.*;
import model.items.*;

class Beer_save implements ITestcase{
    public void runTest() {

        System.out.println("---\tSetup\t---");
        Room r = new Room();
        Beer b = new Beer();
        Teacher t = new Teacher();
        Student s = new Student();
        System.out.println("Controller : addItem(" + b.toString() + ") -> " + s.toString());
        s.addItem(b);
        System.out.println("Controller : addStudent(" + s.toString() + ") -> " + r.toString());
        r.addStudent(s);
        System.out.println("Controller : setRoom(" + r.toString() + ") -> " + s.toString());
        s.setRoom(r);
        System.out.println("Controller : setRoom(" + r.toString() + ") -> " + t.toString());
        t.setRoom(r);

        System.out.println("---\tStart of test\t---");
        System.out.println("Controller : useItem(" + b.toString() + ") -> " + s.toString());
        s.useItem(b);

        System.out.println("Controller : addTeacher(" + t.toString() + ") -> " + r.toString());
        r.addTeacher(t);

    }
    public String testTitle() {
        return "Student saved by beer";
    };
}

class TVSZ_save implements ITestcase{
    public void runTest() {

        System.out.println("---\tSetup\t---");
        Student s = new Student();
        TVSZ tvsz = new TVSZ();
        Room r = new Room();
        Teacher t = new Teacher();
        System.out.println("Controller : addItem(" + tvsz.toString() + ") -> " + s.toString());
        s.addItem(tvsz);
        System.out.println("Controller : addStudent(" + s.toString() + ") -> " + r.toString());
        r.addStudent(s);
        System.out.println("Controller : setRoom(" + r.toString() + ") -> " + s.toString());
        s.setRoom(r);
        System.out.println("Controller : setRoom(" + r.toString() + ") -> " + t.toString());
        t.setRoom(r);

        System.out.println("---\tStart of test\t---");
        System.out.println("Controller : addTeacher(" + t.toString() + ") -> " + r.toString());
        r.addTeacher(t);
    }
    public String testTitle() {
        return "Student saved by TVSZ";
    };
}

/*  
    TODO: Kommunikációs diagram átírása: s.pickUp kitörli a szobából így előtte
    abba is bele kell tenni az itemet
*/
class Sponge_save implements ITestcase{
    public void runTest() {

        System.out.println("---\tSetup\t---");
        Student s = new Student();
        Teacher t = new Teacher();
        Room r = new Room();
        Sponge sp = new Sponge();
        s.setRoom(r);
        r.addItem(sp);
        s.pickUp(sp);
        t.setRoom(r);

        System.out.println("---\tStart of test\t---");
        r.addTeacher(t);

    }
    public String testTitle() {
        return "Student saved by Sponge";
    };
}

/*  
    TODO: A controller->room : addStudent(s) kimaradt
*/
class Mask_save implements ITestcase{
    public void runTest() {

        System.out.println("---\tSetup\t---");
        Student s = new Student();
        Mask m = new Mask();
        Room r = new Room();
        s.addItem(m);
        m.setPlayer(s);
        m.setFunctional();
        s.setRoom(r);

        System.out.println("---\tStart of test\t---");
        r.addStudent(s);

        r.addEffect(ERoomEffects.POISONED);
    }
    public String testTitle() {
        return "Student saved by mask";
    };
}

/*  
    TODO: Ezt két külön diagramra kell tenni. Itt legyen az ahol csak aktiválódik use-nál
*/
class Cheese_timer implements ITestcase{
    public void runTest() {

        System.out.println("---\tSetup\t---");
        Student s = new Student();
        Cheese c = new Cheese();
        Room r = new Room();
        r.addStudent(s);
        s.addItem(c);
        c.startTimer();
        s.setRoom(r);

        System.out.println("---\tStart of test\t---");
        s.useItem(c);
    }
    public String testTitle() {
        return "Cheese timer";
    };
}

class Cheese_timer_end implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        Student s = new Student();
        Cheese c = new Cheese();
        Room r = new Room();

        r.addStudent(s);
        s.addItem(c);
        c.startTimer();
        s.setRoom(r);
        s.useItem(c);
    }
    public String testTitle() {
        return "Cheese timer end";
    };
}

class Beer_timer implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        Student s = new Student();
        Beer b = new Beer();
        Room r = new Room();
    }
    public String testTitle() {
        return "My third option!";
    };
}

class Sponge_timer implements ITestcase{
    public void runTest() {
        System.out.println("420!");
    }
    public String testTitle() {
        return "My third option!";
    };
}

class Mask_pause implements ITestcase{
    public void runTest() {
        System.out.println("420!");
    }
    public String testTitle() {
        return "My third option!";
    };
}