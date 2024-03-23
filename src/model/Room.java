package model;

import java.util.ArrayList;
import java.util.List;

import model.items.IItem;
import model.player.Student;
import model.player.Teacher;

public class Room implements IRoomManager {

    public String toString(){
        return "Room@"+Integer.toString(this.hashCode()).substring(0, 4);
    }

    private int maxPlayer;
    private List<ERoomEffects> effects = new ArrayList<>();
    private List<IItem> itemList = new ArrayList<>();
    private List<Student> studentList = new ArrayList<>();
    private List<Teacher> teacherList = new ArrayList<>();
    private List<Room> neighbouringRooms = new ArrayList<>();

    public Room(){
        //System.out.println("Room created");
        System.out.println("<<create>> \""+this+"\"");
    }

    /**
     * Megpróbálja a jelenlegi szobába mozgatni az adott hallgatót.
     * 
     * @param s A hallgató, aki mozogni próbál a jelenlegi szobába
     * @return  Sikerült-e mozogni a szobába
     */
    public boolean addStudent(Student s) { 
        if(s.getRoom() == null){
            studentList.add(s);
            return true;
        }
        if(effects.contains(ERoomEffects.CURSED) || s.getRoom().effects.contains(ERoomEffects.CURSED)){
            return false;
        }

        if(effects.contains(ERoomEffects.POISONED)){
            studentList.add(s);
            s.RoomPoisoned();
            return true;
        }

        return false;
    }
    public boolean removeStudent(Student s) { return false; }
    public boolean addTeacher(Teacher t) { return false; }
    public boolean removeTeacher(Teacher t) { return false; }
    public List<Student> getStudents() { return studentList; }
    public List<Teacher> getTeachers() { return teacherList; }
    public void addNeighbour(Room r) {}
    public void removeNeighbour(Room r) {}
    public List<Room> getNeighbours() { return neighbouringRooms; }
    public void addItem(IItem i) {}
    public void removeItem(IItem i) {}
    public void addEffect(ERoomEffects e) {
        effects.add(e);
        System.out.println("\t"+this+": Effect added: "+e);
        if(!studentList.isEmpty()){
            for (Student s : studentList) {
                s.RoomPoisoned();
            }
        }
        if(!teacherList.isEmpty()){
            for (Teacher t : teacherList) {
                t.RoomPoisoned();
            }
        }
    }
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
