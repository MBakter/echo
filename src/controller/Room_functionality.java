package controller;

import java.util.Map;

import model.*;
import model.items.*;
import model.player.*;

class fillRF {
  public static void fill(Map<Integer, ITestcase> tests) {
    tests.put(1, new room_merge());
    tests.put(2, new room_merge_transistor());
    tests.put(3, new room_merge_not_empty());
    tests.put(4, new room_split());
    tests.put(5, new room_split_transistor());
    tests.put(6, new room_split_not_empty());
  }
}

class room_merge implements ITestcase {
  public void runTest() {
    System.out.println("---\tSetup\t---");

    Room r1 = new Room();
    Room r2 = new Room();
    Logarlec l = new Logarlec();
    Mask m = new Mask();
    Beer b = new Beer();

    System.out.println("Controller : addItem("+l+") -> "+r1);
    r1.addItem(l);

    System.out.println("Controller : addItem("+m+") -> "+r1);
    r1.addItem(m);

    System.out.println("Controller : addItem("+b+") -> "+r2);
    r2.addItem(b);

    System.out.println("Controller : addNeighbour("+r2+") -> "+r1);
    r1.addNeighbour(r2);

    System.out.println("---\tStart of test\t---");

    System.out.println("Controller : merge("+r2+") -> "+r1);
    r1.merge(r2);
  }

  public String testTitle() {
    return "Room successfully merge";
  }
}

class room_merge_transistor implements ITestcase {
  public void runTest() {
    System.out.println("---\tSetup\t---");

    Room r1 = new Room();
    Room r2 = new Room();
    Transistor t = new Transistor();

    System.out.println("Controller : setRoom("+r1+") -> "+t);
    t.setRoom(r1);

    System.out.println("Transistor : addEffect(TRANSISTOR_INSIDE) -> "+r1);
    r1.addEffect(ERoomEffects.TRANSISTOR_INSIDE);


    System.out.println("---\tStart of test\t---");

    System.out.println("Controller : merge("+r2+") -> "+r1);
    r1.merge(r2);
  }

  public String testTitle() {
    return "Room cannot merge because transistor is inside";
  }
}

class room_merge_not_empty implements ITestcase {
  public void runTest() {
    System.out.println("---\tSetup\t---");

    Room r1 = new Room();
    Room r2 = new Room();
    Student s = new Student();

    System.out.println("Controller : move("+r1+") -> "+s);
    s.move(r1);

    System.out.println("Student : addStudent("+s+") -> "+r1);
    r1.addStudent(s);

    System.out.println("---\tStart of test\t---");

    System.out.println("Controller : merge("+r2+") -> "+r1);
    r1.merge(r2);
  }

  public String testTitle() {
    return "Room cannot merge because it is not empty";
  }
}

class room_split implements ITestcase {
  public void runTest() {
    System.out.println("---\tSetup\t---");

    Room r1 = new Room();
    Room r2 = new Room();
    Logarlec l = new Logarlec();
    Mask m = new Mask();
    Beer b = new Beer();

    System.out.println("Controller : addItem("+l+") -> "+r1);
    r1.addItem(l);

    System.out.println("Controller : addItem("+m+") -> "+r1);
    r1.addItem(m);

    System.out.println("Controller : addItem("+b+") -> "+r2);
    r2.addItem(b);

    System.out.println("Controller : addNeighbour("+r2+") -> "+r1);
    r1.addNeighbour(r2);

    System.out.println("---\tStart of test\t---");

    System.out.println("Controller : split() -> "+r1);
    r1.split();
  }

  public String testTitle() {
    return "Rooms successfully split";
  }
}

class room_split_transistor implements ITestcase {
  public void runTest() {
    System.out.println("---\tSetup\t---");

    Room r1 = new Room();
    Room r2 = new Room();
    Transistor t = new Transistor();

    System.out.println("Controller : setRoom("+r1+") -> "+t);
    t.setRoom(r1);

    System.out.println("Transistor : addEffect(TRANSISTOR_INSIDE) -> "+r1);
    r1.addEffect(ERoomEffects.TRANSISTOR_INSIDE);


    System.out.println("---\tStart of test\t---");

    System.out.println("Controller : split() -> "+r1);
    r1.split();
  }

  public String testTitle() {
    return "Rooms cannot split because transistor is inside";
  }
}

class room_split_not_empty implements ITestcase {
  public void runTest() {
    System.out.println("---\tSetup\t---");

    Room r1 = new Room();
    Room r2 = new Room();
    Student s = new Student();

    System.out.println("Controller : move("+r1+") -> "+s);
    s.move(r1);

    System.out.println("Student : addStudent("+s+") -> "+r1);
    r1.addStudent(s);

    System.out.println("---\tStart of test\t---");

    System.out.println("Controller : split() -> "+r1);
    r1.split();
  }

  public String testTitle() {
    return "Rooms cannot split because it is not empty";
  }
}
