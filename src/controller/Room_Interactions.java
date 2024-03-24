package controller;

import java.util.Map;

import model.ERoomEffects;
import model.Room;
import model.player.EPlayerState;
import model.player.Student;
import model.player.Teacher;

class RIfill{
    public void fill(Map<Integer, ITestcase> l){
        l.put(1, new student_enter_cursed());
        l.put(2, new student_leave_cursed());
        l.put(3, new student_enter_poison());
        l.put(4, new student_room_poisoned());
        l.put(5, new student_leave_poison());
        l.put(6, new student_room_poison_removed());
        l.put(7, new student_enter_student());
        l.put(8, new student_enter_teacher());
        l.put(9, new student_enter_full());
        l.put(10, new student_enter_empty());
        l.put(11, new teacher_enter_cursed());
        l.put(12, new teacher_leave_cursed());
        l.put(13, new teacher_enter_poison());
        l.put(14, new teacher_room_poisoned());
        l.put(15, new teacher_room_poison_removed());
        l.put(16, new teacher_enter_student());
        l.put(17, new teacher_enter_teacher());
        l.put(18, new teacher_enter_full());
        l.put(19, new teacher_enter_empty());
    }
}
/**
 * Hallgató átkozott szobába próbál lépni
 */
class student_enter_cursed implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");

        Student s = new Student();
        Room r = new Room();
        Room sRoom = new Room();

        System.out.println("\nController : setState(ALIVE) -> "+s);
        s.setState(EPlayerState.ALIVE);

        System.out.println("\nController : addEffect(CURSED) -> "+r);
        r.addEffect(ERoomEffects.CURSED);       

        System.out.println("\nController : move("+sRoom+") -> "+s);
        s.move(sRoom);

        System.out.println("\n---\tStart of test\t---");

        System.out.println("\nController : move("+r+") -> "+s);
        s.move(r);
    }
    public String testTitle() {
        return "Student tries to enter cursed room";
    };
}
/**
 * Hallgató átkozott szobából próbál kilépni
 */
class student_leave_cursed  implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Student s = new Student();
        Room rCursed = new Room();
        Room r = new Room();

        System.out.println("\nController : setState(ALIVE) -> "+s);
        s.setState(EPlayerState.ALIVE); 

        System.out.println("Controller : move("+rCursed+") -> "+s);
        s.move(rCursed);

        System.out.println("\nController : addEffect(CURSED) -> "+rCursed);
        rCursed.addEffect(ERoomEffects.CURSED);        

        System.out.println("\n---\tStart of test\t---");

        System.out.println("\nController : move("+r+") -> "+s);
        s.move(r);        
    }
    public String testTitle() {
        return "Student tries to leave cursed room";
    };
}
/**
 * Hallgató gázos szobába lép
 */
class student_enter_poison implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");        

        Student s = new Student();
        Room initRoom = new Room();
        Room r = new Room();

        System.out.println("\nController : setState(ALIVE) -> "+s);
        s.setState(EPlayerState.ALIVE);
        
        System.out.println("\nController : move("+initRoom+") -> "+s);
        s.move(initRoom);

        System.out.println("\nController : addEffect(POISONED) -> "+r);
        r.addEffect(ERoomEffects.POISONED);

        System.out.println("\n---\tStart of test\t---");

        System.out.println("\nController : move("+r+") -> "+s);
        s.move(r);
    }
    public String testTitle() {
        return "Student enters poisoned room";
    };
}
/**
 * Hallgató szobája gázos lesz
 */
class student_room_poisoned implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Student s = new Student();
        Room initRoom = new Room();

        System.out.println("\nController : setState(ALIVE) -> "+s);
        s.setState(EPlayerState.ALIVE);
        
        System.out.println("\nController : move("+initRoom+") -> "+s);
        s.move(initRoom);        

        System.out.println("\n---\tStart of test\t---");
     
        System.out.println("\nController : addEffect(POISONED) -> "+initRoom);
        initRoom.addEffect(ERoomEffects.POISONED);
    }
    public String testTitle() {
        return "Student's room is poisoned";
    };
}
/**
 * Hallgató kilép a gázos szobából
 */
class student_leave_poison implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Student s = new Student();
        Room initRoom = new Room();
        Room r = new Room();

        System.out.println("\nController : setState(ALIVE) -> "+s);
        s.setState(EPlayerState.ALIVE);
        
        System.out.println("\nController : move("+initRoom+") -> "+s);
        s.move(initRoom);

        System.out.println("\nController : addEffect(POISONED) -> "+r);
        initRoom.addEffect(ERoomEffects.POISONED);

        System.out.println("\nController : setState(ALIVE) -> "+s);
        s.setState(EPlayerState.ALIVE);

        System.out.println("\n---\tStart of test\t---");
     
        System.out.println("\nController : move("+r+") -> "+s);
        s.move(r);
    }
    public String testTitle() {
        return "Student leaves poisoned room";
    };
}
/**
 * Hallgató szobájáról lekerül a gáz
 */
class student_room_poison_removed implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Student s = new Student();
        Room initRoom = new Room();

        System.out.println("\nController : setState(ALIVE) -> "+s);
        s.setState(EPlayerState.ALIVE);
        
        System.out.println("\nController : move("+initRoom+") -> "+s);
        s.move(initRoom);

        System.out.println("\nController : addEffect(POISONED) -> "+initRoom);
        initRoom.addEffect(ERoomEffects.POISONED);

        System.out.println("\n---\tStart of test\t---");
     
        System.out.println("\nController : removeEffect(POISONED) -> "+initRoom);
        initRoom.removeEffect(ERoomEffects.POISONED);

    }
    public String testTitle() {
        return "Student's room is no longer poisoned";
    };
}
/**
 * Hallgató szobába lép, amiben hallgató van
 */
class student_enter_student implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Student s1 = new Student();
        Student s2 = new Student();
        Room initRoom = new Room();

        System.out.println("\nController : setState(ALIVE) -> "+s1);
        s1.setState(EPlayerState.ALIVE);
        System.out.println("\nController : setState(ALIVE) -> "+s2);
        s2.setState(EPlayerState.ALIVE);

        System.out.println("\nController : move("+initRoom+") -> "+s1);
        s1.move(initRoom);

        System.out.println("\n---\tStart of test\t---");
     
        System.out.println("\nController : move("+initRoom+") -> "+s2);
        s2.move(initRoom);
    }
    public String testTitle() {
        return "Student enters room with other student(s)";
    };
}
/**
 * Hallgató szobába lép, amiben oktató van
 */
class student_enter_teacher implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Student s = new Student();
        Teacher t = new Teacher();
        Room initRoom = new Room();
        Room r = new Room();

        System.out.println("\nController : setState(ALIVE) -> "+s);
        s.setState(EPlayerState.ALIVE);
        System.out.println("\nController : setState(ALIVE) -> "+t);
        t.setState(EPlayerState.ALIVE);

        System.out.println("\nController : move("+r+") -> "+t);
        t.move(r);
        System.out.println("\nController : move("+initRoom+") -> "+s);
        s.move(initRoom);

        System.out.println("\n---\tStart of test\t---");
     
        System.out.println("\nController : move("+r+") -> "+s);
        s.move(r);
    }
    public String testTitle() {
        return "Student enters room with teacher(s)";
    };
}
/**
 * Hallgató teli szobába próbál lépni
 */
class student_enter_full implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Student s1 = new Student();
        Student s2 = new Student();
        Student s3 = new Student();
        Student s4 = new Student();

        Room initRoom = new Room();
        Room r = new Room();

        System.out.println("\nController : setMax(3) -> "+r);
        r.setMax(3);

        System.out.println("\nController : setState(ALIVE) -> "+s1);
        s1.setState(EPlayerState.ALIVE);
        System.out.println("\nController : setState(ALIVE) -> "+s2);
        s2.setState(EPlayerState.ALIVE);
        System.out.println("\nController : setState(ALIVE) -> "+s3);
        s3.setState(EPlayerState.ALIVE);
        System.out.println("\nController : setState(ALIVE) -> "+s4);
        s4.setState(EPlayerState.ALIVE);

        System.out.println("\nController : move("+r+") -> "+s2);
        s2.move(r);
        System.out.println("\nController : move("+r+") -> "+s3);
        s3.move(r);
        System.out.println("\nController : move("+r+") -> "+s4);
        s4.move(r);

        System.out.println("\nController : move("+initRoom+") -> "+s1);
        s1.move(initRoom);

        System.out.println("\n---\tStart of test\t---");
     
        System.out.println("\nController : move("+r+") -> "+s1);
        s1.move(r);
     
    }
    public String testTitle() {
        return "Student tries to enter full room";
    };
}
/**
 * Hallgató üres szobába lép
 */
class student_enter_empty implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Student s = new Student();
        Room initRoom = new Room();

        System.out.println("\nController : setState(ALIVE) -> "+s);
        s.setState(EPlayerState.ALIVE);

        System.out.println("\n---\tStart of test\t---");

        System.out.println("\nController : move("+initRoom+") -> "+s);
        s.move(initRoom);
    }
    public String testTitle() {
        return "Student enters empty room";
    };
}
/**
 * Oktató átkozott szobába próbál lépni
 */
class teacher_enter_cursed implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");

        Teacher t = new Teacher();
        Room r = new Room();
        Room initRoom = new Room();

        System.out.println("\nController : setState(ALIVE) -> "+t);
        t.setState(EPlayerState.ALIVE);

        System.out.println("\nController : addEffect(POISONED) -> "+r);
        r.addEffect(ERoomEffects.CURSED);        

        System.out.println("Controller : move("+initRoom+") -> "+t);
        t.move(initRoom);

        System.out.println("\n---\tStart of test\t---");

        System.out.println("Controller : move("+r+") -> "+t);
        t.move(r);

    }
    public String testTitle() {
        return "Teacher tries to enter cursed room";
    };
}
/**
 * Oktató átkozott szobából próbál kilépni
 */
class teacher_leave_cursed implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Teacher t = new Teacher();
        Room r = new Room();
        Room rCursed = new Room();

        System.out.println("\nController : setState(ALIVE) -> "+t);
        t.setState(EPlayerState.ALIVE);             
        
        System.out.println("Controller : move("+rCursed+") -> "+t);
        t.move(rCursed);

        System.out.println("\nController : addEffect(CURSED) -> "+rCursed);
        rCursed.addEffect(ERoomEffects.CURSED);        

        System.out.println("\n---\tStart of test\t---");

        System.out.println("Controller : move("+r+") -> "+t);
        t.move(r);  
    }
    public String testTitle() {
        return "Teacher tries to leave cursed room";
    };
}
/**
 * Oktató gázos szobába lép
 */
class teacher_enter_poison implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Teacher t = new Teacher();
        Room initRoom = new Room();
        Room r = new Room();

        System.out.println("\nController : setState(ALIVE) -> "+t);
        t.setState(EPlayerState.ALIVE);
        
        System.out.println("\nController : move("+initRoom+") -> "+t);
        t.move(initRoom);

        System.out.println("\nController : addEffect(POISONED) -> "+r);
        r.addEffect(ERoomEffects.POISONED);

        System.out.println("\n---\tStart of test\t---");
     
        System.out.println("\nController : move("+r+") -> "+t);
        t.move(r);
    }
    public String testTitle() {
        return "Teacher enters poisoned room";
    };
}
/**
 * Oktató szobája gázos lesz
 */
class teacher_room_poisoned implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Teacher t = new Teacher();
        Room initRoom = new Room();

        System.out.println("\nController : setState(ALIVE) -> "+t);
        t.setState(EPlayerState.ALIVE);
        
        System.out.println("\nController : move("+initRoom+") -> "+t);
        t.move(initRoom);        

        System.out.println("\n---\tStart of test\t---");
     
        System.out.println("\nController : addEffect(POISONED) -> "+initRoom);
        initRoom.addEffect(ERoomEffects.POISONED);
    }
    public String testTitle() {
        return "Teacher's room is poisoned";
    };
}
/**
 * Oktató szobájáról lekerül a gáz
 */
class teacher_room_poison_removed implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Teacher t = new Teacher();
        Room initRoom = new Room();

        System.out.println("\nController : setState(ALIVE) -> "+t);
        t.setState(EPlayerState.ALIVE);
        
        System.out.println("\nController : move("+initRoom+") -> "+t);
        t.move(initRoom);

        System.out.println("\nController : addEffect(POISONED) -> "+initRoom);
        initRoom.addEffect(ERoomEffects.POISONED);

        System.out.println("\n---\tStart of test\t---");
     
        System.out.println("\nController : removeEffect(POISONED) -> "+initRoom);
        initRoom.removeEffect(ERoomEffects.POISONED);
    }
    public String testTitle() {
        return "Teacher's room is no longer poisoned";
    };
}
/**
 * Oktató szobába lép, amiben hallgató van
 */
class teacher_enter_student implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Student s = new Student();
        Teacher t = new Teacher();
        Room initRoom = new Room();
        Room r = new Room();

        System.out.println("\nController : setState(ALIVE) -> "+s);
        s.setState(EPlayerState.ALIVE);
        System.out.println("\nController : setState(ALIVE) -> "+t);
        t.setState(EPlayerState.ALIVE);

        System.out.println("\nController : move("+r+") -> "+t);
        t.move(r);
        System.out.println("\nController : move("+initRoom+") -> "+s);
        s.move(initRoom);

        System.out.println("\n---\tStart of test\t---");
     
        System.out.println("\nController : move("+initRoom+") -> "+t);
        t.move(initRoom);
    }
    public String testTitle() {
        return "Teacher enters room with student(s)";
    };
}
/**
 * Oktató szobába lép, amiben oktató van
 */
class teacher_enter_teacher implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Teacher t1 = new Teacher();
        Teacher t2 = new Teacher();
        Room initRoom = new Room();
        Room r = new Room();

        System.out.println("\nController : setState(ALIVE) -> "+t1);
        t1.setState(EPlayerState.ALIVE);
        System.out.println("\nController : setState(ALIVE) -> "+t2);
        t2.setState(EPlayerState.ALIVE);

        System.out.println("\nController : move("+r+") -> "+t1);
        t1.move(r);
        System.out.println("\nController : move("+initRoom+") -> "+t2);
        t2.move(initRoom);

        System.out.println("\n---\tStart of test\t---");
     
        System.out.println("\nController : move("+initRoom+") -> "+t1);
        t1.move(initRoom);
    }
    public String testTitle() {
        return "Teacher enters room with teacher(s)";
    };
}
/**
 * Oktató teli szobába próbál lépni
 */
class teacher_enter_full implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Teacher t1 = new Teacher();
        Teacher t2 = new Teacher();
        Teacher t3 = new Teacher();
        Teacher t4 = new Teacher();

        Room initRoom = new Room();
        Room r = new Room();

        System.out.println("\nController : setMax(3) -> "+r);
        r.setMax(3);

        System.out.println("\nController : setState(ALIVE) -> "+t1);
        t1.setState(EPlayerState.ALIVE);
        System.out.println("\nController : setState(ALIVE) -> "+t2);
        t2.setState(EPlayerState.ALIVE);
        System.out.println("\nController : setState(ALIVE) -> "+t3);
        t3.setState(EPlayerState.ALIVE);
        System.out.println("\nController : setState(ALIVE) -> "+t4);
        t4.setState(EPlayerState.ALIVE);

        System.out.println("\nController : move("+r+") -> "+t2);
        t2.move(r);
        System.out.println("\nController : move("+r+") -> "+t3);
        t3.move(r);
        System.out.println("\nController : move("+r+") -> "+t4);
        t4.move(r);

        System.out.println("\nController : move("+initRoom+") -> "+t1);
        t1.move(initRoom);

        System.out.println("\n---\tStart of test\t---");
     
        System.out.println("\nController : move("+r+") -> "+t1);
        t1.move(r);
     
    }
    public String testTitle() {
        return "Teacher tries to enter full room";
    };
}
/**
 * Oktató üres szobába lép
 */
class teacher_enter_empty implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Teacher t = new Teacher();
        Room initRoom = new Room();

        System.out.println("\nController : setState(ALIVE) -> "+t);
        t.setState(EPlayerState.ALIVE);

        System.out.println("\n---\tStart of test\t---");
     
        System.out.println("\nController : move("+initRoom+") -> "+t);
        t.move(initRoom);
    }
    public String testTitle() {
        return "Teacher enters empty room";
    };
}