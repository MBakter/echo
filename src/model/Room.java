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
    private List<ERoomEffects> effects = new ArrayList<ERoomEffects>();
    private List<IItem> itemList;
    private List<Student> studentList;
    private List<Teacher> teacherList;
    private List<Room> neighbouringRooms;

    public Room(){
        //System.out.println("Room created");
        System.out.println("<<create>> \""+this.toString()+"\"");
    }

    /**
     * Megpróbálja a jelenlegi szobába mozgatni az adott hallgatót.
     * 
     * @param s A hallgató, aki mozogni próbál a jelenlegi szobába
     * @return  Sikerült-e mozogni a szobába
     */
    public boolean addStudent(Student s) { 
        if(effects.contains(ERoomEffects.CURSED)){
            System.out.println("\tStudent \"" + s.toString() + "\" could not move to cursed room \"" + this.toString() + "\"");
            return false;
        }
        if(s.getRoom().effects.contains(ERoomEffects.CURSED)){
            System.out.println("\tStudent \"" + s.toString() + "\" could not move from cursed room \"" + s.getRoom().toString() + "\"");
            return false;
        }        
        return false;
    }
    public boolean removeStudent(Student s) { return false; }
    public boolean addTeacher(Teacher t) { return false; }
    public boolean removeTeacher(Teacher t) { return false; }
    public List<Teacher> getTeachers() { return teacherList; }
    public void addNeighbour(Room r) {}
    public void removeNeighbour(Room r) {}
    public List<Room> getNeighbours() { return neighbouringRooms; }
    public void addItem(IItem i) {}
    public void removeItem(IItem i) {}
    public void addEffect(ERoomEffects e) {
        effects.add(e);
        System.out.println("\tNew effect added to room \"" + this.toString() + "\": " + e.toString());
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
