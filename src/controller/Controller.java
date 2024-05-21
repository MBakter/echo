package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.ERoomEffects;
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
    private static Timer timer = new Timer();
    private static IMainWindow View;

    static Student curPlayer = null;
    private static int actionCounter = 2;
    private static int studentMoveCounter = 0;
    public static Commands commands = new Commands();

    public static final int minStudentSize = 1;
    public static final int minTeacherSize = 1;
    public static final int minCleanerSize = 0;
    public static final int maxPlayerSize = 10;
    public static final String defaultMapName = "Mosquito.txt";

    public Controller(String mapDirectoryPath) {
        this.mapDirectoryPath = mapDirectoryPath; 
        mapName = defaultMapName;
        students.add(new Student("s0", timer));
        teachers.add(new Teacher("t0", timer));
    }

    public void startGameWithGUI(IMainWindow viewInterface) {
        View = viewInterface;
        // Map.generateFromFile(mapDirectoryPath);
        View.InitWindow();

    }

    public static void endGame(boolean victory) {
        endOfGame = true;
        System.out.println(victory == true ? "*VICTORY!*" : "-DEFEAT!-");
        View.endGame(victory);
    }

    //Return true if that was the last player and the game is over
    //To be used after current player performed an action, checks if CP is dead
    private static boolean checkPlayerDead() {
        if(curPlayer.getState() == EPlayerState.DEAD) {
            students.remove(curPlayer);
            View.RefreshView();
            View.showError("You Died!");
        }

        if(students.isEmpty()) {
            endGame(false);
            return true;
        }

        if(curPlayer.getState() == EPlayerState.DEAD) {
            studentMoveCounter++;
            if(studentMoveCounter >= students.size()) {
                for (Teacher teacher : teachers) {
                    TeacherMove(teacher);
                    if(endOfGame)
                        return true;
                }
                for (Cleaner cleaner : cleaners) {
                    CleanerMove(cleaner);
                }
                Map.randomMove();
                studentMoveCounter = 0;
            }

            StudentMove(students.get(studentMoveCounter));
        }
        return false;
    }

    //Return true if that was the last player and the game is over
    //To be used when a teacher move is up, checks all student states
    private static boolean checkDeadPlayers() {
        int j = -1;
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getState() == EPlayerState.DEAD) {
                j = i;
                if(students.get(i).equals(curPlayer)) {
                    View.RefreshView();
                    View.showError("You Died!");
                }
            }
        }
        if(j >= 0)
            students.remove(j);

        if(students.isEmpty()) {
            endGame(false);
            return true;
        }
        return false;
    }

    private static void StudentMove(Student s) {
        if(endOfGame)
            return;

        curPlayer = s;
        if (curPlayer.getState().equals(EPlayerState.UNCONSCIOUS))
            actionCounter = -2;
        else if (curPlayer.getState().equals(EPlayerState.ALIVE))
            actionCounter = 2;
        else if (curPlayer.getState().equals(EPlayerState.DEAD))
            actionCounter = -1;
        View.RefreshView();

    }

    private static void TeacherMove(Teacher t) {
        if(endOfGame)
            return;

        Random r = new Random();
        if (t.getRoom().getNeighbours().size() >= 2)
            t.move(t.getRoom().getNeighbours().get(r.nextInt(t.getRoom().getNeighbours().size() - 1)));
        else if (t.getRoom().getNeighbours().size() == 1)
            t.move(t.getRoom().getNeighbours().get(0));
        View.RefreshView();

        checkDeadPlayers();
    }

    private static void CleanerMove(Cleaner c) {
        if(endOfGame)
            return;

        Random r = new Random();
        if (c.getRoom().getNeighbours().size() >= 2)
            c.move(c.getRoom().getNeighbours().get(r.nextInt(c.getRoom().getNeighbours().size() - 1)));
        else if (c.getRoom().getNeighbours().size() == 1)
            c.move(c.getRoom().getNeighbours().get(0));
        View.RefreshView();
    }

    public static void PlayerMoved() {
        checkPlayerDead();

        actionCounter--;
        View.RefreshView();
    }

    public static boolean CanPlayerMove() {
        if(endOfGame)
            return false;

        if (actionCounter == 0) {
            View.showError("You ran out of moves!");
            return false;
        }else if(actionCounter == -1){
            View.showError("You are dead!");
            return false;
        }else if(actionCounter == -2){
            View.showError("You are unconscoius!");
            return false;
        }
        return true;
    }

    @Override
    public void EndTurn() {     
        checkPlayerDead();
        
        if(endOfGame)
            return;

        studentMoveCounter++;
        
        if(studentMoveCounter >= students.size()) {
            for (Teacher teacher : teachers) {
                TeacherMove(teacher);  
                if(endOfGame)
                    return;              
            }
            for (Cleaner cleaner : cleaners) {
                CleanerMove(cleaner);
            }
            if(endOfGame)
                return;
            Map.randomMove();
            studentMoveCounter = 0;
            timer.iterateTime();
        }

        if(students.size() > 0)
            StudentMove(students.get(studentMoveCounter));
    }

    private boolean isGameSet() {
        if (students.size() < 1 || teachers.size() < 1 || cleaners.size() < 0)
            return false;
        return true;
    }

    @Override
    public void startGame() {
        if (!isGameSet()) {
            View.showError("University will not be funded! Please add at least 1 student and 1 teacher");
        }
        if (!Map.generateFromFile(mapDirectoryPath + File.separator + mapName)) {
            View.showError("University has not been built yet! Please select a valid map");
        }

        Map.placeItems(timer);

        Random r = new Random();
        for (Student s : students) {
            s.move(Map.roomList.get(0));
        }
        for (Teacher t : teachers) {
            t.move(Map.roomList.get(r.nextInt(Map.roomList.size() - 2) + 1));
        }
        for (Cleaner c : cleaners) {
            c.move(Map.roomList.get(r.nextInt(Map.roomList.size() - 2) + 1));
        }

        StudentMove(students.get(0));

    }

    @Override
    public void resetGame() {
        if(endOfGame) {
            students.clear();
            teachers.clear();
            cleaners.clear();
            Map.clear();
            Map = new Labyrinth();
            actionCounter = 2;
            studentMoveCounter = 0;
            timer = new Timer();
            endOfGame = false;
        }
    }

    @Override
    public void setParameters(int studentNum, int teacherNum, int cleanerNum, String mapName) {
        students.clear();
        teachers.clear();
        cleaners.clear();
        for (int i = 0; i < studentNum; i++)
            students.add(new Student("S" + i, timer));

        for (int i = 0; i < teacherNum; i++)
            teachers.add(new Teacher("T" + i, timer));

        for (int i = 0; i < cleanerNum; i++)
            cleaners.add(new Cleaner("C" + i, timer));

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
