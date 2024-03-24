package controller;

import model.player.*;
import model.*;
import model.items.*;

class Beer_save implements ITestcase{
    public void runTest() {

        System.out.println("---\tSetup\t---");
        Timer timer = new Timer();
        Room r = new Room();
        Beer b = new Beer(timer);
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
        Timer timer = new Timer();
        Student s = new Student();
        Teacher t = new Teacher();
        Room r = new Room();
        Sponge sp = new Sponge(timer);
        System.out.println("Controller : setRoom(" + r.toString() + ") -> " + s.toString());
        s.setRoom(r);
        System.out.println("Controller : addStudent(" + s.toString() + ") -> " + r.toString());
        r.addStudent(s);
        System.out.println("Controller : addItem(" + sp.toString() + ") -> " + r.toString());
        r.addItem(sp);
        System.out.println("Controller : pickUp(" + sp.toString() + ") -> " + s.toString());
        s.pickUp(sp);
        System.out.println("Controller : setRoom(" + r.toString() + ") -> " + t.toString());
        t.setRoom(r);

        System.out.println("---\tStart of test\t---");
        System.out.println("Controller : addTeacher(" + t.toString() + ") -> " + r.toString());
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
        Timer timer = new Timer();
        Student s = new Student();
        Mask m = new Mask(timer);
        Room r = new Room();
        System.out.println("Controller : addItem(" + m.toString() + ") -> " + s.toString());
        s.addItem(m);
        System.out.println("Controller : setPlayer(" + s.toString() + ") -> " + m.toString());
        m.setPlayer(s);
        System.out.println("Controller : setRoom(" + r.toString() + ") -> " + s.toString());
        s.setRoom(r);
        System.out.println("Controller : addStudent(" + s.toString() + ") -> " + r.toString());
        r.addStudent(s);

        System.out.println("---\tStart of test\t---");
        System.out.println("Controller : addEffect(POISONED) -> " + r.toString());
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
        Timer timer = new Timer();
        Student s = new Student();
        Cheese c = new Cheese(timer);
        Room r = new Room();
        System.out.println("Controller : addStudent(" + s.toString() + ") -> " + r.toString());
        r.addStudent(s);
        System.out.println("Controller : addItem(" + c.toString() + ") -> " + s.toString());
        s.addItem(c);
        System.out.println("Controller : startTimer() -> " + c.toString());
        c.startTimer();
        System.out.println("Controller : setRoom(" + r.toString() + ") -> " + s.toString());
        s.setRoom(r);

        System.out.println("---\tStart of test\t---");
        System.out.println("Controller : useItem(" + c.toString() + ") -> " + s.toString());
        s.useItem(c);
    }
    public String testTitle() {
        return "Cheese timer";
    };
}

class Cheese_timer_end implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        Timer timer = new Timer();
        Student s = new Student();
        Cheese c = new Cheese(timer);
        Room r = new Room();

        System.out.println("Controller : addStudent(" + s.toString() + ") -> " + r.toString());
        r.addStudent(s);
        System.out.println("Controller : addItem(" + c.toString() + ") -> " + s.toString());
        s.addItem(c);
        System.out.println("Controller : startTimer() -> " + c.toString());
        c.startTimer();
        System.out.println("Controller : setRoom(" + r.toString() + ") -> " + s.toString());
        s.setRoom(r);
        System.out.println("Controller : useItem(" + c.toString() + ") -> " + s.toString());
        s.useItem(c);
        
        System.out.println("---\tStart of test\t---");
        System.out.println("Controller : iterateTime() -> " + c.toString());
        for (int i = 0; i < timer.getList().size(); i++) {
            if(timer.getList().get(i).getObject().equals(c)) {
                while(timer.getList().get(i).getTime() > 0)
                    timer.getList().get(i).iterateTime();
            }
        }
        
    }
    public String testTitle() {
        return "Cheese timer end";
    };
}

class Beer_timer implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        Timer timer = new Timer();
        Student s = new Student();
        Beer b = new Beer(timer);
        Room r = new Room();
        Teacher t = new Teacher();
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

        System.out.println("Controller : iterateTime() -> " + b.toString());
        for (int i = 0; i < timer.getList().size(); i++) {
            if(timer.getList().get(i).getObject().equals(b)) {
                while(timer.getList().get(i).getTime() > 0)
                    timer.getList().get(i).iterateTime();
            }
        }
        
        System.out.println("Controller : addTeacher(" + t.toString() + ") -> " + r.toString());
        r.addTeacher(t);

    }
    public String testTitle() {
        return "My third option!";
    };
}

class Sponge_timer implements ITestcase{
    public void runTest() {
        System.out.println("soon...");
    }
    public String testTitle() {
        return "My third option!";
    };
}

class Mask_pause implements ITestcase{
    public void runTest() {
        System.out.println("soon...");
    }
    public String testTitle() {
        return "My third option!";
    };
}