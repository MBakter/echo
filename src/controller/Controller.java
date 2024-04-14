package controller;

import java.util.List;

import model.player.*;

public class Controller {
    private static List<Player> players;
    private static Labyrinth Map;
    private static boolean endOfGame = false;

    private static void initGame() {
        System.out.println("Üdvözöllek a mátrixban");
        System.out.println("Mennyi a játékos, mekkora a map? ");
        players.add(new Student());
        players.add(new Teacher());
        players.add(new Cleaner());
        Map.generateFromFile("map1.txt");
    }

    public static void endGame(boolean victory) {

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
