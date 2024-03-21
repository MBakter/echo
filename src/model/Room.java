package model;

import java.util.List;

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

    @Override
    public Room split() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'split'");
    }

    @Override
    public boolean merge(Room r) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'merge'");
    }
    
}
