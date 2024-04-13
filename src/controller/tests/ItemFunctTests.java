package controller.tests;

import model.player.*;

import java.util.Map;

import controller.Timer;
import model.*;
import model.items.*;

class fillIF {
    public static void fill(Map<Integer, ITestcase> tests) {
      tests.put(1, new Beer_save());
      tests.put(2, new TVSZ_save());
      tests.put(3, new Sponge_save());
      tests.put(4, new Mask_save());
      tests.put(5, new Cheese_timer());
      tests.put(6, new Cheese_timer_end());
      tests.put(7, new Beer_timer());
      tests.put(8, new Sponge_timer());
      tests.put(9, new Mask_pause());
    }
  }
  
class Beer_save implements ITestcase{
    public void runTest() {

        System.out.println("\n---\tSetup\t---\n");
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

        System.out.println("\n---\tStart of test\t---\n");
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

        System.out.println("\n---\tSetup\t---\n");
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

        System.out.println("\n---\tStart of test\t---\n");
        System.out.println("Controller : addTeacher(" + t.toString() + ") -> " + r.toString());
        r.addTeacher(t);
    }
    public String testTitle() {
        return "Student saved by TVSZ";
    };
}

class Sponge_save implements ITestcase{
    public void runTest() {

        System.out.println("\n---\tSetup\t---\n");
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

        System.out.println("\n---\tStart of test\t---\n");
        System.out.println("Controller : addTeacher(" + t.toString() + ") -> " + r.toString());
        r.addTeacher(t);

    }
    public String testTitle() {
        return "Student saved by Sponge";
    };
}

class Mask_save implements ITestcase{
    public void runTest() {

        System.out.println("\n---\tSetup\t---\n");
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

        System.out.println("\n---\tStart of test\t---\n");
        System.out.println("Controller : addEffect(POISONED) -> " + r.toString());
        r.addEffect(ERoomEffects.POISONED);
    }
    public String testTitle() {
        return "Student saved by mask";
    };
}

class Cheese_timer implements ITestcase{
    public void runTest() {

        System.out.println("\n---\tSetup\t---\n");
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

        System.out.println("\n---\tStart of test\t---\n");
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
        
        System.out.println("\n---\tStart of test\t---\n");
        System.out.println("Controller : iterateTime() -> " + c.toString());
        for (int i = 0; i < timer.getList().size(); i++) {
            if(timer.getList().get(i).getObject().equals(c)) {
                while(true){
                    timer.iterateTime();
                    if (timer.getList().get(i).getTime() == 0) 
                        break;  
                }
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

        System.out.println("\n---\tStart of test\t---\n");
        System.out.println("Controller : useItem(" + b.toString() + ") -> " + s.toString());
        s.useItem(b);

        System.out.println("Controller : iterateTime() -> " + b.toString());
        for (int i = 0; i < timer.getList().size(); i++) {
            if(timer.getList().get(i).getObject().equals(b)) {
                while(true){
                    timer.iterateTime();
                    if (timer.getList().get(i).getTime() == 0) 
                        break;  
                }
            }
        }
        
        System.out.println("Controller : addTeacher(" + t.toString() + ") -> " + r.toString());
        r.addTeacher(t);

    }
    public String testTitle() {
        return "Beer timer";
    };
}

class Sponge_timer implements ITestcase{
    public void runTest() {
        
        System.out.println("\n---\tSetup\t---\n");
        Timer timer = new Timer();
        Student s = new Student();
        Sponge sp = new Sponge(timer);
        Room r = new Room();
        Teacher t = new Teacher();
        System.out.println("Controller : addItem(" + sp.toString() + ") -> " + s.toString());
        s.addItem(sp);
        System.out.println("Controller : addStudent(" + s.toString() + ") -> " + r.toString());
        r.addStudent(s);
        System.out.println("Controller : setRoom(" + r.toString() + ") -> " + s.toString());
        s.setRoom(r);
        System.out.println("Controller : setRoom(" + r.toString() + ") -> " + t.toString());
        t.setRoom(r);

        System.out.println("\n---\tStart of test\t---\n");
        System.out.println("Controller : pickUp(" + sp.toString() + ") -> " + s.toString());
        s.pickUp(sp);
        System.out.println("Controller : iterateTime() -> " + sp.toString());
        for (int i = 0; i < timer.getList().size(); i++) {
            if(timer.getList().get(i).getObject().equals(sp)) {
                while(true){
                    timer.iterateTime();
                    if (timer.getList().get(i).getTime() == 0) 
                        break;  
                }
            }
        }
        System.out.println("Controller : addTeacher(" + t.toString() + ") -> " + r.toString());
        r.addTeacher(t);

    }
    public String testTitle() {
        return "Sponge timer";
    };
}

class Mask_pause implements ITestcase{
    public void runTest() {
        
        System.out.println("\n---\tSetup\t---\n");
        Timer timer = new Timer();
        Student s = new Student();
        Mask m = new Mask(timer);
        Room r = new Room();
        System.out.println("Controller : addItem(" + m.toString() + ") -> " + s.toString());
        s.addItem(m);
        System.out.println("Controller : addStudent(" + s.toString() + ") -> " + r.toString());
        r.addStudent(s);
        System.out.println("Controller : setRoom(" + r.toString() + ") -> " + s.toString());
        s.setRoom(r);
        System.out.println("Controller : addEffect(POISONED) -> " + r.toString());
        r.addEffect(ERoomEffects.POISONED);
        
        System.out.println("\n---\tStart of test\t---\n");
        System.out.println("Controller : removeEffect(POISONED) -> " + r.toString());
        r.removeEffect(ERoomEffects.POISONED);

    }
    public String testTitle() {
        return "Mask pause";
    };
}