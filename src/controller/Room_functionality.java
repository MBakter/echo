package controller;

import java.util.Map;

import model.*;
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

    Student s = new Student();

    Room r = new Room();

    System.out.println("Controller : setState("+s+") -> ALIVE");
    s.setState(EPlayerState.ALIVE);

    System.out.println("Controller : addEffect("+r+") -> CURSED");
    r.addEffect(ERoomEffects.CURSED);

    Room sRoom = new Room();

    System.out.println("Controller : move("+sRoom+") -> "+s);
    s.move(sRoom);

    System.out.println("---\tStart of test\t---");

    System.out.println("Controller : move("+r+") -> "+s);
    s.move(r);
  }

  public String testTitle() {
    return "Room successfully merge";
  }
}

class room_merge_transistor implements ITestcase {
  public void runTest() {
    System.out.println("BUTTOOON");
  }

  public String testTitle() {
    return "Room cannot merge because transistor is inside";
  }
}

class room_merge_not_empty implements ITestcase {
  public void runTest() {
    System.out.println("BUTTOOON");
  }

  public String testTitle() {
    return "Room cannot merge because it is not empty";
  }
}

class room_split implements ITestcase {
  public void runTest() {
    System.out.println("BUTTOOON");
  }

  public String testTitle() {
    return "Rooms successfully split";
  }
}

class room_split_transistor implements ITestcase {
  public void runTest() {
    System.out.println("BUTTOOON");
  }

  public String testTitle() {
    return "Rooms cannot split because transistor is inside";
  }
}

class room_split_not_empty implements ITestcase {
  public void runTest() {
    System.out.println("BUTTOOON");
  }

  public String testTitle() {
    return "Rooms cannot split because it is not empty";
  }
}
