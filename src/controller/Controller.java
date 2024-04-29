package controller;

import java.util.ArrayList;
import java.util.List;

import model.player.*;

public class Controller {
    private static List<Player> players = new ArrayList<Player>();
    private static Labyrinth Map = new Labyrinth();
    private static boolean endOfGame = false;
    private static final String mapDirectoryPath = "maps";
    private static final Timer t = new Timer();
    private static void initGame() {
        System.out.println("Üdvözöllek a mátrixban");
        System.out.println("Mennyi a játékos, mekkora a map? ");
        players.add(new Student("s",t));
        players.add(new Teacher("t",t));
        players.add(new Cleaner("c",t));
        //Map.generateFromFile("map1.txt");
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

        while(true) {
            for (Player curPlayer : players) {
                if(curPlayer instanceof Student) 
                    StudentMove((Student)curPlayer);
                if(curPlayer instanceof Teacher)
                    TeacherMove((Teacher)curPlayer);
                if(curPlayer instanceof Cleaner)
                    CleanerMove((Cleaner)curPlayer);
                Map.randomMove();
            }
            if(endOfGame)
                break;
        }
    }

    public static void main(String[] args) {
        initGame();
        GameCycle();
    } 
}
