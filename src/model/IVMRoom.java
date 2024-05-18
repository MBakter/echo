package model;

import java.util.ArrayList;

import view.*;

public interface IVMRoom {
    public ArrayList<IVStudent> getStudentList();
    public ArrayList<IVTeacher> getTeacherList();
    public ArrayList<IVCleaner> getCleanerList();
    public ArrayList<IVRoom> getNeighBourList();
    public ArrayList<ERoomEffects> getRoomState();
    public ArrayList<IVItems> getRoomItems();
}
