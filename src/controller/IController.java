package controller;

public interface IController {
    void startGame();
    void setParameters(int studentNum, int teacherNum, int cleanerNum, String mapName);
    String getMapFolderLocation();
    int getStudentNum();
    int getTeacherNum();
    int getCleanerNum();
    String getMapName();
}
