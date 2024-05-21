package controller;

import java.util.ArrayList;

import view.IVItems;
import view.IVStudent;

public interface IController {
    /*
     * Kontrollerből játék indítása
     */
    void startGame();

    /*
     * Játék befejezése
     */
    void endGame();

    /*
     * Játék elején játékosok számának megadása
     */
    void setParameters(int studentNum, int teacherNum, int cleanerNum, String mapName);

    /*
     * Hol tároljuk a pályákat (előre elkészített txtk)
     */
    String getMapFolderLocation();

    /*
     * Hány hallgató van a játékban
     */
    int getStudentNum();

    /*
     * Hány oktató van a játékban
     */
    int getTeacherNum();

    /*
     * Hány cleaner van a játékban
     */
    int getCleanerNum();

    /*
     * Kiválaszott map neve
     */
    String getMapName();

    /*
     * Jelenlegi játékos lekérdezése, Viewként
     */
    IVStudent getCP();

    /*
     * Jelenlegi játékos viewbeli itemei
     */
    ArrayList<IVItems> getVItemsOfCP();

    /*
     * Parancsok lekérése
     */
    ICommands getCommands();

    /*
     * Kör vége
     */
    void EndTurn();

    /*
     * Új játék kezdete
     */
    void resetGame();
}
