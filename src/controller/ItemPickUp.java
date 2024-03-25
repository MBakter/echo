package controller;

import model.player.*;

import java.util.Map;
import model.Room;
import model.items.*;

class IPUFill {
    public static void fill(Map<Integer, ITestcase> tests) {
        tests.put(1, new StudentPicksMask());
        tests.put(2, new StudentPicksSponge());
        tests.put(3, new StudentPicksBeer());
        tests.put(4, new StudentPicksCheese());
    }
}

class StudentPicksMask implements ITestcase{
    public void runTest() {

        System.out.println("---\tSetup\t---");

        Room r = new Room();
        Mask m = new Mask(new Timer());
        Student s = new Student();

        System.out.println("Controller : addItem(" + m + ") -> " + r);
        r.addItem(m);

        System.out.println("Controller : addStudent(" + s + ") -> " + r);
        r.addStudent(s);
        System.out.println("    Students in room: " + r.getStudents());

        System.out.println("Controller : setRoom(" + r + ") -> " + s);
        s.setRoom(r);


        System.out.println("---\tStart of test\t---");

        System.out.println("Controller : pickUpItem(" + m + ") -> " + s);
        s.pickUp(m);

    }
    public String testTitle() {
        return "Student picks up mask";
    }
}

class StudentPicksSponge implements ITestcase{
    public void runTest() {

        System.out.println("---\tSetup\t---");

        Timer timer = new Timer();
        Room r = new Room();
        Sponge sp = new Sponge(timer);
        Student s = new Student();

        System.out.println("Controller : addItem(" + sp + ") -> " + r);
        r.addItem(sp);

        System.out.println("Controller : addStudent(" + s + ") -> " + r);
        r.addStudent(s);
        System.out.println("    Students in room: " + r.getStudents());

        System.out.println("Controller : setRoom(" + r + ") -> " + s);
        s.setRoom(r);

        System.out.println("---\tStart of test\t---");
        System.out.println("Controller : pickUpItem(" + sp + ") -> " + s);
        s.pickUp(sp);

    }
    public String testTitle() {
        return "Student picks up sponge";
    }
}

class StudentPicksBeer implements ITestcase{
    public void runTest() {

        System.out.println("---\tSetup\t---");

        Timer timer = new Timer();
        Room r = new Room();
        Beer b = new Beer(timer);
        Student s = new Student();

        System.out.println("Controller : addItem(" + b + ") -> " + r);
        r.addItem(b);

        System.out.println("Controller : addStudent(" + s + ") -> " + r);
        r.addStudent(s);
        System.out.println("    Students in room: " + r.getStudents());

        System.out.println("Controller : setRoom(" + r + ") -> " + s);
        s.setRoom(r);

        System.out.println("---\tStart of test\t---");

        System.out.println("Controller : pickUpItem(" + b + ") -> " + s);
        s.pickUp(b);

    }
    public String testTitle() {
        return "Student picks up beer";
    }
}

class StudentPicksCheese implements ITestcase{
    public void runTest() {

        System.out.println("---\tSetup\t---");

        Timer timer = new Timer();
        Room r = new Room();
        Cheese c = new Cheese(timer);
        Student s = new Student();

        System.out.println("Controller : addItem(" + c + ") -> " + r);
        r.addItem(c);

        System.out.println("Controller : addStudent(" + s + ") -> " + r);
        r.addStudent(s);
        System.out.println("    Students in room: " + r.getStudents());

        System.out.println("Controller : setRoom(" + r + ") -> " + s);
        s.setRoom(r);


        System.out.println("---\tStart of test\t---");

        System.out.println("Controller : pickUpItem(" + c + ") -> " + s);
        s.pickUp(c);

    }
    public String testTitle() {
        return "Student picks up cheese";
    }
}


