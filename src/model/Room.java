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

    /**
     * Teszthez kiírjuk a <<create>>-et és beállítjuk az alap méretet
     */
    public Room(){
        //System.out.println("Room created");
        System.out.println("\n<<create>> \""+this+"\"");
        setMax(5);
    }

    /**
     * Megpróbálja a jelenlegi szobába mozgatni az adott hallgatót.
     * 
     * @param s A hallgató, aki mozogni próbál a jelenlegi szobába
     * @return  Sikerült-e mozogni a szobába
     */
    public boolean addStudent(Student s) { 

        int playersInRoom = teacherList.size()+studentList.size();

        // Initialize student's room
        if(s.getRoom() == null){
            studentList.add(s);
            return true;
        }
        // Enter or leave cursed room
        else if(effects.contains(ERoomEffects.CURSED) || s.getRoom().effects.contains(ERoomEffects.CURSED)
        || maxPlayer == playersInRoom){
            return false;
        }
        // Leave poisoned room to not poisoned
        else if(s.getRoom().effects.contains(ERoomEffects.POISONED) && !effects.contains(ERoomEffects.POISONED)){
            s.getRoom().removeStudent(s);
            studentList.add(s);
            s.RoomCleanFromPoison();;
            checkAttacks();
            return true;
        }
        // Enter poisoned
        else if(effects.contains(ERoomEffects.POISONED)){
            s.getRoom().removeStudent(s);
            studentList.add(s);
            s.RoomPoisoned();
            checkAttacks();
            return true;
        }

        // Both rooms clear of effects
        s.getRoom().removeStudent(s);
        studentList.add(s);
        checkAttacks(); 
        return true;
    }
    /**
     * Megnézzük, hogy egy szobába került-e hallgató és oktató
     */
    private void checkAttacks(){
        if(!studentList.isEmpty() && !teacherList.isEmpty()){
            for (Student s : studentList) {
                s.TeacherAttacked();
            }
        }
    }

    /**
     * Hallgató eltávolítása a szoba listájából
     * 
     * @param s Keresett hallgató
     * @return  Benne volt-e a hallgató a listában
     */
    public boolean removeStudent(Student s) { 
        boolean contains = studentList.contains(s);
        if(contains){
            studentList.remove(s);
            System.out.println("\t"+this+": "+s+" removed");
        }

        return contains;
     }
    
    /**
     * Megpróbálja a jelenlegi szobába mozgatni az adott oktatót.
     * 
     * @param t Az oktató, aki mozogni próbál a jelenlegi szobába
     * @return  Sikerült-e mozogni a szobába
     */
    public boolean addTeacher(Teacher t) { 

        int playersInRoom = teacherList.size()+studentList.size();

        // Initialize Teacher's room
        if(t.getRoom() == null){
            teacherList.add(t);
            return true;
        }
        // Enter or leave cursed room
        else if(effects.contains(ERoomEffects.CURSED) || t.getRoom().effects.contains(ERoomEffects.CURSED)
        || playersInRoom == maxPlayer){
            return false;
        }
        // Leave poisoned room to not poisoned
        else if(t.getRoom().effects.contains(ERoomEffects.POISONED) && !effects.contains(ERoomEffects.POISONED)){
            t.getRoom().removeTeacher(t);
            teacherList.add(t);
            t.RoomCleanFromPoison();;
            checkAttacks();
            return true;
        }
        // Enter poisoned
        else if(effects.contains(ERoomEffects.POISONED)){
            t.getRoom().removeTeacher(t);
            teacherList.add(t);
            t.RoomPoisoned();
            checkAttacks();
            return true;
        }

        // Both rooms clear
        t.getRoom().removeTeacher(t);
        teacherList.add(t);
        checkAttacks(); 
        return true;
     }

    /**
    * Oktató eltávolítása a szoba listájából
    * 
    * @param s Keresett oktató
    * @return  Benne volt-e az oktató a listában
    */
    public boolean removeTeacher(Teacher t) { 
        boolean contains = teacherList.contains(t);
        if(contains){
            teacherList.remove(t);
            System.out.println("\t"+this+": "+t+" removed");
        }

        return contains;
     }
    public List<Student> getStudents() { return studentList; }
    public List<Teacher> getTeachers() { return teacherList; }
    public void addNeighbour(Room r) {}
    public void removeNeighbour(Room r) {}
    public List<Room> getNeighbours() { return neighbouringRooms; }
    public void addItem(IItem i) {}
    public void removeItem(IItem i) {}

    /**
     * Effektus rakása a szobára, effektus aktiválódása esemény indítása
     * 
     * @param e Az effektus, amit a szobára teszünk
     */
    public void addEffect(ERoomEffects e) {
        effects.add(e);
        System.out.println("\t"+this+": Effects after addition: "+effects);
        if(e == ERoomEffects.POISONED){
            for (Student s : studentList) {
                s.RoomPoisoned();
            }
            for (Teacher t : teacherList) {
                t.RoomPoisoned();
            }
        }
    }
    /**
     * Effektus eltávolítása a szobáról, effektus eltávolítása esemény indítása
     * 
     * @param e Az effektus, amit a szobáról leveszünk
     */
    public void removeEffect(ERoomEffects e) {
        System.out.println("\t"+this+": Effects before removal: "+effects);
        effects.remove(e);
        System.out.println("\t"+this+": Effects after removal: "+effects);
        if(!effects.contains(ERoomEffects.POISONED)){
            for (Student s : studentList) {
                s.RoomCleanFromPoison();
            }
            for (Teacher t : teacherList) {
                t.RoomCleanFromPoison();
            }
        }
    }

    public int getMax(){return maxPlayer;}
    public void setMax(int n){
        maxPlayer = n;
        System.out.println("\t"+this+": Max size set to: "+n);
    }

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
