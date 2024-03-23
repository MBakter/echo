package model;

import java.util.List;

import model.items.IItem;
import model.player.Student;
import model.player.Teacher;

public class Room implements IRoomManager {

    private int maxPlayer;
    private List<ERoomEffects> effects;
    private List<IItem> itemList;
    private List<Student> studentList;
    private List<Teacher> teacherList;
    private List<Room> neighbouringRooms;

    public boolean addStudent(Student s) { return false; }
    public boolean removeStudent(Student s) { return false; }
    public boolean addTeacher(Teacher t) { return false; }
    public boolean removeTeacher(Teacher t) { return false; }
    public List<Teacher> getTeachers() { return teacherList; }
    public void addNeighbour(Room r) {}
    public void removeNeighbour(Room r) {}
    public List<Room> getNeighbours() { return neighbouringRooms; }
    public void addItem(IItem i) {}
    public void removeItem(IItem i) {}
    public void addEffect(ERoomEffects e) {}
    public void removeEffect(ERoomEffects e) {}

    /**
     * Megpróbálja a szobát ketté választani.
     *
     * @return  az új szoba, ha sikerült ketté választani, egyébként null
     */
    @Override
    public Room split() {
        if(studentList.size() > 0 || teacherList.size() > 0) {
            System.out.println("\tRoom \"" + this.toString() + "\" cannot split because it is not empty");
            return null;
        }
        if(effects.contains(ERoomEffects.TRANSISTOR_INSIDE)){
            System.out.println("\tRoom \"" + this.toString() + "\" cannot split because transistor is inside");
            return null;
        }
        Room newRoom = new Room();
        newRoom.maxPlayer = this.maxPlayer;
        newRoom.addNeighbour(this);
        this.addNeighbour(newRoom);
        System.out.println("\tRoom \"" + this.toString() + "\" successfully split");
        return newRoom;
    }

    /**
     * Megpróbálja a szobát összevonni egy másik szobával.
     *
     * @param r  a másik szoba
     * @return  Sikerült-e összevonni a két szobát
     */
    @Override
    public boolean merge(Room r) {
        if(studentList.size() > 0 || teacherList.size() > 0 || r.studentList.size() > 0 || r.teacherList.size() > 0){
            System.out.println("\tRoom \"" + this.toString() + "\" and \"" + r.toString() + "\" cannot merge because one is not empty");
            return false;
        }
        if(effects.contains(ERoomEffects.TRANSISTOR_INSIDE) || r.effects.contains(ERoomEffects.TRANSISTOR_INSIDE)){
            System.out.println("\tRoom \"" + this.toString() + "\" and \"" + r.toString() + "\" cannot merge because transistor is inside");
            return false;
        }
        List<Room> nb = r.getNeighbours();
        for (Room n : nb) {
            addNeighbour(n);
        }
        List<IItem> items = r.itemList;
        for (IItem i : items) {
            addItem(i);
        }
        System.out.println("\tRoom \"" + this.toString() + "\" and \"" + r.toString() + "\" successfully merged");
        return true;
    }

}
