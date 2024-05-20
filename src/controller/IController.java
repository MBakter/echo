package controller;

import java.util.ArrayList;

import view.IVItems;
import view.IVStudent;

public interface IController {
    void startGame();
    void setParameters(int studentNum, int teacherNum, int cleanerNum, String mapName);
    String getMapFolderLocation();
    int getStudentNum();
    int getTeacherNum();
    int getCleanerNum();
    String getMapName();

    IVStudent getCP();
    ArrayList<IVItems> getVItemsOfCP();
    ICommands getCommands();
}
