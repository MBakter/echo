package controller;

import java.util.Map;

import model.ERoomEffects;
import model.Room;
import model.player.EPlayerState;
import model.player.Student;

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
        asd.put(13, new teacher_enter_poisoned());
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

        System.out.println("Controller : setState("+s.toString()+") -> ALIVE");
        s.setState(EPlayerState.ALIVE);

        System.out.println("Controller : addEffect("+r.toString()+") -> CURSED");
        r.addEffect(ERoomEffects.CURSED);

        Room sRoom = new Room();

        System.out.println("Controller : move("+sRoom.toString()+") -> "+s.toString());
        s.move(sRoom);

        System.out.println("---\tStart of test\t---");

        System.out.println("Controller : move("+r.toString()+") -> "+s.toString());
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

        System.out.println("Controller : setState("+s.toString()+") -> ALIVE");
        s.setState(EPlayerState.ALIVE);        
        
        Room rCursed = new Room();

        System.out.println("Controller : move("+rCursed.toString()+") -> "+s.toString());
        s.move(rCursed);

        System.out.println("Controller : addEffect("+rCursed.toString()+") -> CURSED");
        rCursed.addEffect(ERoomEffects.CURSED);

        Room r = new Room();

        System.out.println("---\tStart of test\t---");

        System.out.println("Controller : move("+r.toString()+") -> "+s.toString());
        s.move(r);        
    }
    public String testTitle() {
        return "Hallgató átkozott szobából próbál kilépni";
    };
}

class student_enter_poison implements ITestcase{
    public void runTest() {
        System.out.println("Eredmeny");
    }
    public String testTitle() {
        return "Hallgató gázos szobába lép";
    };
}

class student_room_poisoned implements ITestcase{
    public void runTest() {
        System.out.println("Eredmeny");
    }
    public String testTitle() {
        return "Hallgató szobája gázos lesz";
    };
}

class student_leave_poison implements ITestcase{
    public void runTest() {
        System.out.println("Eredmeny");
    }
    public String testTitle() {
        return "Hallgató kilép a gázos szobából";
    };
}

class student_room_poison_removed implements ITestcase{
    public void runTest() {
        System.out.println("Eredmeny");
    }
    public String testTitle() {
        return "Hallgató szobájáról lekerül a gáz";
    };
}

class student_enter_student implements ITestcase{
    public void runTest() {
        System.out.println("Eredmeny");
    }
    public String testTitle() {
        return "Hallgató szobába lép, amiben hallgató van";
    };
}

class student_enter_teacher implements ITestcase{
    public void runTest() {
        System.out.println("Eredmeny");
    }
    public String testTitle() {
        return "Hallgató szobába lép, amiben oktató van";
    };
}

class student_enter_full implements ITestcase{
    public void runTest() {
        System.out.println("Eredmeny");
    }
    public String testTitle() {
        return "Hallgató teli szobába próbál lépni";
    };
}

class student_enter_empty implements ITestcase{
    public void runTest() {
        System.out.println("Eredmeny");
    }
    public String testTitle() {
        return "Hallgató üres szobába lép";
    };
}

class teacher_enter_cursed implements ITestcase{
    public void runTest() {
        System.out.println("Eredmeny");
    }
    public String testTitle() {
        return "Oktató átkozott szobába próbál lépni";
    };
}

class teacher_leave_cursed implements ITestcase{
    public void runTest() {
        System.out.println("Eredmeny");
    }
    public String testTitle() {
        return "Oktató átkozott szobából próbál kilépni";
    };
}

class teacher_enter_poisoned implements ITestcase{
    public void runTest() {
        System.out.println("Eredmeny");
    }
    public String testTitle() {
        return "Oktató gázos szobába lép";
    };
}

class teacher_room_poisoned implements ITestcase{
    public void runTest() {
        System.out.println("Eredmeny");
    }
    public String testTitle() {
        return "Oktató szobája gázos lesz";
    };
}

class teacher_room_poison_removed implements ITestcase{
    public void runTest() {
        System.out.println("Eredmeny");
    }
    public String testTitle() {
        return "Oktató szobájáról lekerül a gáz";
    };
}

class teacher_enter_student implements ITestcase{
    public void runTest() {
        System.out.println("Eredmeny");
    }
    public String testTitle() {
        return "Oktató szobába lép, amiben hallgató van";
    };
}

class teacher_enter_teacher implements ITestcase{
    public void runTest() {
        System.out.println("Eredmeny");
    }
    public String testTitle() {
        return "Oktató szobába lép, amiben oktató van";
    };
}

class teacher_enter_full implements ITestcase{
    public void runTest() {
        System.out.println("Eredmeny");
    }
    public String testTitle() {
        return "Oktató teli szobába próbál lépni";
    };
}

class teacher_enter_empty implements ITestcase{
    public void runTest() {
        System.out.println("Eredmeny");
    }
    public String testTitle() {
        return "Oktató üres szobába lép";
    };
}