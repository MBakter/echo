package model;

import java.util.ArrayList;

import view.*;

public interface IVMRoom {
    public ArrayList<IVStudent> getStudentList();
    public ArrayList<IVStudent> getTeacherList();
    public ArrayList<IVStudent> getCleanerList();
    public ArrayList<IVStudent> getNeighBourList();
    public ArrayList<IVStudent> getRoomState();
}
