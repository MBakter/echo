package controller;

import java.util.Map;

import model.ERoomEffects;
import model.Room;
import model.player.EPlayerState;
import model.player.Student;
import model.player.Teacher;

class fill{
    public void fill(Map<Integer, ITestcase> asd){
        asd.put(1, new student_enter_cursed());
        asd.put(2, new student_leave_cursed());
        asd.put(3, new student_enter_poison());
        asd.put(4, new student_room_poisoned());
        asd.put(5, new student_leave_poison());
        asd.put(6, new student_room_poison_removed());
        asd.put(7, new student_enter_student());
        asd.put(8, new student_enter_teacher());
        asd.put(9, new student_enter_full());
        asd.put(10, new student_enter_empty());
        asd.put(11, new teacher_enter_cursed());
        asd.put(12, new teacher_leave_cursed());
        asd.put(13, new teacher_enter_poison());
        asd.put(14, new teacher_room_poisoned());
        asd.put(15, new teacher_room_poison_removed());
        asd.put(16, new teacher_enter_student());
        asd.put(17, new teacher_enter_teacher());
        asd.put(18, new teacher_enter_full());
        asd.put(19, new teacher_enter_empty());
    }
}

class student_enter_cursed implements ITestcase{
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
        return "Hallgató átkozott szobába próbál lépni";
    };
}

class student_leave_cursed  implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Student s = new Student();

        System.out.println("Controller : setState("+s+") -> ALIVE");
        s.setState(EPlayerState.ALIVE);        
        
        Room rCursed = new Room();

        System.out.println("Controller : move("+rCursed+") -> "+s);
        s.move(rCursed);

        System.out.println("Controller : addEffect("+rCursed+") -> CURSED");
        rCursed.addEffect(ERoomEffects.CURSED);

        Room r = new Room();

        System.out.println("---\tStart of test\t---");

        System.out.println("Controller : move("+r+") -> "+s);
        s.move(r);        
    }
    public String testTitle() {
        return "Hallgató átkozott szobából próbál kilépni";
    };
}

class student_enter_poison implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Student s = new Student();
        Room initRoom = new Room();
        Room r = new Room();

        s.setState(EPlayerState.ALIVE);
        
        s.move(initRoom);

        r.addEffect(ERoomEffects.POISONED);

        System.out.println("---\tStart of test\t---");
     
        s.move(r);
    }
    public String testTitle() {
        return "Hallgató gázos szobába lép";
    };
}

class student_room_poisoned implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Student s = new Student();
        Room initRoom = new Room();

        s.setState(EPlayerState.ALIVE);
        
        s.move(initRoom);        

        System.out.println("---\tStart of test\t---");
     
        initRoom.addEffect(ERoomEffects.POISONED);
    }
    public String testTitle() {
        return "Hallgató szobája gázos lesz";
    };
}

class student_leave_poison implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Student s = new Student();
        Room initRoom = new Room();
        Room r = new Room();

        s.setState(EPlayerState.ALIVE);
        
        s.move(initRoom);

        initRoom.addEffect(ERoomEffects.POISONED);

        s.setState(EPlayerState.ALIVE);

        System.out.println("---\tStart of test\t---");
     
        s.move(r);
    }
    public String testTitle() {
        return "Hallgató kilép a gázos szobából";
    };
}

class student_room_poison_removed implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Student s = new Student();
        Room initRoom = new Room();

        s.setState(EPlayerState.ALIVE);
        
        s.move(initRoom);

        initRoom.addEffect(ERoomEffects.POISONED);

        s.setState(EPlayerState.ALIVE);

        System.out.println("---\tStart of test\t---");
     
        initRoom.removeEffect(ERoomEffects.POISONED);

    }
    public String testTitle() {
        return "Hallgató szobájáról lekerül a gáz";
    };
}

class student_enter_student implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Student s1 = new Student();
        Student s2 = new Student();
        Room initRoom = new Room();

        s1.setState(EPlayerState.ALIVE);
        s2.setState(EPlayerState.ALIVE);

        s1.move(initRoom);

        System.out.println("---\tStart of test\t---");
     
        s2.move(initRoom);
    }
    public String testTitle() {
        return "Hallgató szobába lép, amiben hallgató van";
    };
}

class student_enter_teacher implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Student s = new Student();
        Teacher t = new Teacher();
        Room initRoom = new Room();
        Room r = new Room();

        s.setState(EPlayerState.ALIVE);
        t.setState(EPlayerState.ALIVE);

        t.move(r);
        s.move(initRoom);

        System.out.println("---\tStart of test\t---");
     
        s.move(r);
    }
    public String testTitle() {
        return "Hallgató szobába lép, amiben oktató van";
    };
}

class student_enter_full implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Student s1 = new Student();
        Student s2 = new Student();
        Student s3 = new Student();
        Student s4 = new Student();

        Room initRoom = new Room();
        Room r = new Room();

        r.setMax(3);

        s1.setState(EPlayerState.ALIVE);
        s2.setState(EPlayerState.ALIVE);
        s3.setState(EPlayerState.ALIVE);
        s4.setState(EPlayerState.ALIVE);

        s2.move(r);
        s3.move(r);
        s4.move(r);

        s1.move(initRoom);

        System.out.println("---\tStart of test\t---");
     
        s1.move(r);
     
    }
    public String testTitle() {
        return "Hallgató teli szobába próbál lépni";
    };
}

class student_enter_empty implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Student s = new Student();

        Room initRoom = new Room();

        s.setState(EPlayerState.ALIVE);

        System.out.println("---\tStart of test\t---");
     
        s.move(initRoom);
    }
    public String testTitle() {
        return "Hallgató üres szobába lép";
    };
}

class teacher_enter_cursed implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");

        Teacher t = new Teacher();

        Room r = new Room();

        System.out.println("Controller : setState("+t+") -> ALIVE");
        t.setState(EPlayerState.ALIVE);

        System.out.println("Controller : addEffect("+r+") -> CURSED");
        r.addEffect(ERoomEffects.CURSED);

        Room initRoom = new Room();

        System.out.println("Controller : move("+initRoom+") -> "+t);
        t.move(initRoom);

        System.out.println("---\tStart of test\t---");

        System.out.println("Controller : move("+r+") -> "+t);
        t.move(r);

    }
    public String testTitle() {
        return "Oktató átkozott szobába próbál lépni";
    };
}

class teacher_leave_cursed implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Teacher t = new Teacher();

        System.out.println("Controller : setState("+t+") -> ALIVE");
        t.setState(EPlayerState.ALIVE);        
        
        Room rCursed = new Room();

        System.out.println("Controller : move("+rCursed+") -> "+t);
        t.move(rCursed);

        System.out.println("Controller : addEffect("+rCursed+") -> CURSED");
        rCursed.addEffect(ERoomEffects.CURSED);

        Room r = new Room();

        System.out.println("---\tStart of test\t---");

        System.out.println("Controller : move("+r+") -> "+t);
        t.move(r);  
    }
    public String testTitle() {
        return "Oktató átkozott szobából próbál kilépni";
    };
}

class teacher_enter_poison implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Teacher t = new Teacher();
        Room initRoom = new Room();
        Room r = new Room();

        t.setState(EPlayerState.ALIVE);
        
        t.move(initRoom);

        r.addEffect(ERoomEffects.POISONED);

        System.out.println("---\tStart of test\t---");
     
        t.move(r);
    }
    public String testTitle() {
        return "Oktató gázos szobába lép";
    };
}

class teacher_room_poisoned implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Teacher t = new Teacher();
        Room initRoom = new Room();

        t.setState(EPlayerState.ALIVE);
        
        t.move(initRoom);        

        System.out.println("---\tStart of test\t---");
     
        initRoom.addEffect(ERoomEffects.POISONED);
    }
    public String testTitle() {
        return "Oktató szobája gázos lesz";
    };
}

class teacher_room_poison_removed implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Teacher t = new Teacher();
        Room initRoom = new Room();

        t.setState(EPlayerState.ALIVE);
        
        t.move(initRoom);

        initRoom.addEffect(ERoomEffects.POISONED);

        System.out.println("---\tStart of test\t---");
     
        initRoom.removeEffect(ERoomEffects.POISONED);
    }
    public String testTitle() {
        return "Oktató szobájáról lekerül a gáz";
    };
}

class teacher_enter_student implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Student s = new Student();
        Teacher t = new Teacher();
        Room initRoom = new Room();
        Room r = new Room();

        s.setState(EPlayerState.ALIVE);
        t.setState(EPlayerState.ALIVE);

        t.move(r);
        s.move(initRoom);

        System.out.println("---\tStart of test\t---");
     
        t.move(initRoom);
    }
    public String testTitle() {
        return "Oktató szobába lép, amiben hallgató van";
    };
}

class teacher_enter_teacher implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Teacher t1 = new Teacher();
        Teacher t2 = new Teacher();
        Room initRoom = new Room();
        Room r = new Room();

        t1.setState(EPlayerState.ALIVE);
        t2.setState(EPlayerState.ALIVE);

        t1.move(r);
        t2.move(initRoom);

        System.out.println("---\tStart of test\t---");
     
        t1.move(initRoom);
    }
    public String testTitle() {
        return "Oktató szobába lép, amiben oktató van";
    };
}

class teacher_enter_full implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Teacher t1 = new Teacher();
        Teacher t2 = new Teacher();
        Teacher t3 = new Teacher();
        Teacher t4 = new Teacher();

        Room initRoom = new Room();
        Room r = new Room();

        r.setMax(3);

        t1.setState(EPlayerState.ALIVE);
        t2.setState(EPlayerState.ALIVE);
        t3.setState(EPlayerState.ALIVE);
        t4.setState(EPlayerState.ALIVE);

        t2.move(r);
        t3.move(r);
        t4.move(r);

        t1.move(initRoom);

        System.out.println("---\tStart of test\t---");
     
        t1.move(r);
     
    }
    public String testTitle() {
        return "Oktató teli szobába próbál lépni";
    };
}

class teacher_enter_empty implements ITestcase{
    public void runTest() {
        System.out.println("---\tSetup\t---");
        
        Teacher t = new Teacher();

        Room initRoom = new Room();

        t.setState(EPlayerState.ALIVE);

        System.out.println("---\tStart of test\t---");
     
        t.move(initRoom);
    }
    public String testTitle() {
        return "Oktató üres szobába lép";
    };
}