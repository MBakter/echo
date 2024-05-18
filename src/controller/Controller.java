package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import model.items.IItem;
import model.player.*;
import view.*;

public class Controller implements IController {
    private static List<Student> students = new ArrayList<>();
    private static List<Teacher> teachers = new ArrayList<>();
    private static List<Cleaner> cleaners = new ArrayList<>();

    private static Labyrinth Map = new Labyrinth();
    private static boolean endOfGame = false;
    private final String mapDirectoryPath;
    private String mapName;
    private static final Timer timer = new Timer();
    private IMainWindow View;
    
    private static Student curPlayer = null;

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
        //List all options
    }

    private static void TeacherMove(Teacher t) {

    }

    private static void CleanerMove(Cleaner c) {

    }

    private static void GameCycle() {

        for (Student s : students) {
            curPlayer = s;
        }

        for (Teacher t : teachers) {
            
        }

        for (Cleaner c : cleaners) {
            
        }
        //while(true) {
            //TODO: Separate list moves
            /* for (Player curPlayer : players) {
                if(curPlayer instanceof Student) 
                    StudentMove((Student)curPlayer);
                if(curPlayer instanceof Teacher)
                    TeacherMove((Teacher)curPlayer);
                if(curPlayer instanceof Cleaner)
                    CleanerMove((Cleaner)curPlayer);
                Map.randomMove();
            } */
           // if(endOfGame)
            //    break;
        //}
    }

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

        GameCycle();
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
        for (IItem item : curPlaye) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getVItemsOfCP'");
        }
    }
}
