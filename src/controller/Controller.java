package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.player.*;
import view.IMainWindow;
import view.IVItems;
import view.IVStudent;
import view.VStudent;

public class Controller implements IController {
    private static List<Student> students = new ArrayList<>();
    private static List<Teacher> teachers = new ArrayList<>();
    private static List<Cleaner> cleaners = new ArrayList<>();

    private static Labyrinth Map = new Labyrinth();
    private static boolean endOfGame = false;
    private final String mapDirectoryPath;
    private String mapName;
    private static final Timer timer = new Timer();
    private static IMainWindow View;

    static Student curPlayer = null;
    private static int actionCounter = 2; 
    private static int studentMoveCounter = 0;
    public static Commands commands = new Commands();

    public Controller(String mapDirectoryPath) {
        this.mapDirectoryPath = mapDirectoryPath; 
        mapName = "default.txt";
        students.add(new Student("s0", timer));
        teachers.add(new Teacher("t0", timer));
    }

    public void startGameWithGUI(IMainWindow viewInterface) {
        View = viewInterface;
        //Map.generateFromFile(mapDirectoryPath);
        View.InitWindow();

    }

    public static void endGame(boolean victory) {
        endOfGame = true;
        System.out.println(victory == true ? "*VICTORY!*" : "-DEFEAT!-");
    }

    private static void StudentMove(Student s) {
        curPlayer = s;
        actionCounter = 2;
        View.RefreshView();
    }

    private static void TeacherMove(Teacher t) {
        Random r = new Random();
        if (t.getRoom().getNeighbours().size()>=2)
        t.move(t.getRoom().getNeighbours().get(r.nextInt(t.getRoom().getNeighbours().size()-1)));
        else if(t.getRoom().getNeighbours().size()==1)
        t.move(t.getRoom().getNeighbours().get(0));
        View.RefreshView();
    }

    private static void CleanerMove(Cleaner c) {
        Random r = new Random();
        if (c.getRoom().getNeighbours().size()>=2)
        c.move(c.getRoom().getNeighbours().get(r.nextInt(c.getRoom().getNeighbours().size()-1)));
        else if(c.getRoom().getNeighbours().size()==1)
        c.move(c.getRoom().getNeighbours().get(0));
        View.RefreshView();
    }

    public static void PlayerMoved() {
        actionCounter--;
        View.RefreshView();
    }

    public static boolean CanPlayerMove() {
        if(actionCounter == 0) {
            View.showError("You ran out of moves!");
            return false;
        }
        return true;
    }

    @Override
    public void EndTurn() {
        studentMoveCounter++;
        
        if(studentMoveCounter == students.size()) {
            for (Teacher teacher : teachers) {
                TeacherMove(teacher);
            }
            for (Cleaner cleaner : cleaners) {
                CleanerMove(cleaner);
            }
            studentMoveCounter = 0;
        }
        StudentMove(students.get(studentMoveCounter));
    }

    /* private static void GameCycle() {
        for (Student student : students) {
            StudentMove(student);
        }
        for (Teacher teacher : teachers) {
            TeacherMove(teacher);
        }
        for (Cleaner cleaner : cleaners) {
            CleanerMove(cleaner);
        }
    } */

    private boolean isGameSet() {
        if(students.size() < 1 || teachers.size() < 1 || cleaners.size() < 0)
            return false;
        return true;
    }

    @Override
    public void startGame() {
        if(!isGameSet()) {
            View.showError("University will not be funded! Please add at least 1 student and 1 teacher");
        }
        if(!Map.generateFromFile(mapDirectoryPath + File.separator + mapName)) {
            View.showError("University has not been built yet! Please select a valid map");
        }

        Map.placeItems(timer);

        Random r = new Random();
        for (Student s : students) {
            s.forceMove(Map.roomList.get(0));
        }
        for (Teacher t : teachers) {
            t.forceMove(Map.roomList.get(r.nextInt(Map.roomList.size()-2) + 1));
        }
        for (Cleaner c : cleaners) {
            c.forceMove(Map.roomList.get(r.nextInt(Map.roomList.size()-2) + 1));
        }

        StudentMove(students.get(0));

/*         //TESZT***************************************
        Student s = new Student();
        Room r = new Room();
        Room r2 = new Room();
        Room r3 = new Room();
        r.addNeighbour(r2);
        r.addNeighbour(r3);
        r2.addNeighbour(r);
        r3.addNeighbour(r2);
        r.addItem(new TVSZ());
        r.addItem(new Purifier("Purifi"));
        r2.addItem(new Logarlec());
        s.setRoom(r);
        System.out.println("Room " + r.toString() + " created");
        System.out.println("Room " + r2.toString() + " created");
        System.out.println("Room " + r3.toString() + " created");
        System.out.println("Student " + s.toString() + " created");

        curPlayer = s;

        View.RefreshView(); */

    }

    @Override
    public void setParameters(int studentNum, int teacherNum, int cleanerNum, String mapName) {
        students.clear();
        teachers.clear();
        cleaners.clear();
        for (int i = 0; i < studentNum; i++) 
            students.add(new Student("s" + i, timer));

        for (int i = 0; i < teacherNum; i++) 
            teachers.add(new Teacher("s" + i, timer));

        for (int i = 0; i < cleanerNum; i++) 
            cleaners.add(new Cleaner("s" + i, timer));

        this.mapName = mapName;
    }

    @Override
    public int getStudentNum() {
        return students.size();    
    }

    @Override
    public int getTeacherNum() {
        return teachers.size();    
    }

    @Override
    public int getCleanerNum() {
        return cleaners.size();        
    }

    @Override
    public String getMapName() {
        return mapName;    
    }

    @Override
    public String getMapFolderLocation() {
        return mapDirectoryPath;
    }

    @Override
    public IVStudent getCP() {
        IVStudent s = new VStudent(curPlayer);
        return s;
    }

    @Override
    public ArrayList<IVItems> getVItemsOfCP() {
        return curPlayer.getItemList();
    }

    @Override
    public ICommands getCommands() {
        return commands;
    }

}
