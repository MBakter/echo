package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.items.IItem;
import model.player.Student;
import model.player.Teacher;
import test.IPrintStat;

public class Room implements IRoomManager, IPrintStat {
    public Room(String s){
        name = s;
    }
    public String toString(){
        return "Room@"+Integer.toString(this.hashCode()).substring(0, 4);
    }
    private String name;
    public String getName(){
        return name;
    }
    private int maxPlayer;
    private List<ERoomEffects> effects = new ArrayList<>();
    private List<IItem> itemList = new ArrayList<>();
    private List<Student> studentList = new ArrayList<>();
    private List<Teacher> teacherList = new ArrayList<>();
    private List<Room> neighbouringRooms = new ArrayList<>();

    public List<ERoomEffects> getEffects(){
        return effects;
    }
    public List<IItem>  getItems(){
        return itemList;
    }
    // TODO
/*     public List<Cleaner> getCleaners(){
        return null;
    } */


    /**
     * Teszthez kiírjuk a <<create>>-et és beállítjuk az alap méretet
     */
    public Room(){
        //////System.out.println("Room created");
        //////System.out.println("<<create>> \""+this+"\"");
        setMax(5);
    }

    /**
     * Megpróbálja a jelenlegi szobába mozgatni az adott hallgatót.
     * 
     * @param s A hallgató, aki mozogni próbál a jelenlegi szobába
     * @return  Sikerült-e mozogni a szobába
     */
    public boolean addStudent(Student s) { 
        //////System.out.println("\t"+this+": addStudent called");
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
            ////System.out.println("\t"+this+": "+s+" removed");
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
            ////System.out.println("\t"+this+": "+t+" removed");
        }

        return contains;
     }
    public List<Student> getStudents() { return studentList; }
    public List<Teacher> getTeachers() { return teacherList; }
    public void addNeighbour(Room r) {
        neighbouringRooms.add(r);
    }
    public void removeNeighbour(Room r) {}
    public List<Room> getNeighbours() { return neighbouringRooms; }
    public void addItem(IItem i) {
        ////System.out.println("\t"+this+": Item Added: "+i);
    }
    public void removeItem(IItem i) {}

    /**
     * Effektus rakása a szobára, effektus aktiválódása esemény indítása
     * 
     * @param e Az effektus, amit a szobára teszünk
     */
    public void addEffect(ERoomEffects e) {
        effects.add(e);
        ////System.out.println("\t"+this+": Effects after addition: "+effects);
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
        ////System.out.println("\t"+this+": Effects before removal: "+effects);
        effects.remove(e);
        ////System.out.println("\t"+this+": Effects after removal: "+effects);
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
        ////System.out.println("\t"+this+": Max size set to: "+n);
    }

    /**
     * Megpróbálja a szobát ketté választani.
     *
     * @return  az új szoba, ha sikerült ketté választani, egyébként null
     */
    @Override
    public Room split() {
        if(studentList.size() > 0 || teacherList.size() > 0) {
            ////System.out.println("\tRoom \"" + this.toString() + "\" cannot split because it is not empty");
            return null;
        }
        if(effects.contains(ERoomEffects.TRANSISTOR_INSIDE)){
            ////System.out.println("\tRoom \"" + this.toString() + "\" cannot split because transistor is inside");
            return null;
        }
        Room newRoom = new Room();
        newRoom.maxPlayer = this.maxPlayer;
        newRoom.addNeighbour(this);
        this.addNeighbour(newRoom);
        ////System.out.println("\tRoom \"" + this.toString() + "\" successfully split");
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
            ////System.out.println("\tRoom \"" + this.toString() + "\" and \"" + r.toString() + "\" cannot merge because one is not empty");
            return false;
        }
        if(effects.contains(ERoomEffects.TRANSISTOR_INSIDE) || r.effects.contains(ERoomEffects.TRANSISTOR_INSIDE)){
            ////System.out.println("\tRoom \"" + this.toString() + "\" and \"" + r.toString() + "\" cannot merge because transistor is inside");
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
        ////System.out.println("\tRoom \"" + this.toString() + "\" and \"" + r.toString() + "\" successfully merged");
        return true;
    }

    @Override
    public void PrintStat(String faszom) {
        System.out.printf("%s effects",name);
        for (var effect : effects) {
            System.out.printf(" %s", effect);
        }
        System.out.printf("%n");

        System.out.printf("%s itemList",name);
        for (var item : itemList) {
            System.out.printf(" %s", item.getName());
        }
        System.out.printf("%n");

        System.out.printf("%s studentList",name);
        for (var student : studentList) {
            System.out.printf(" %s", student.getName());
        }
        System.out.printf("%n");

        System.out.printf("%s teacherList",name);
        for (var teacher : teacherList) {
            System.out.printf(" %s", teacher.getName());
        }
        System.out.printf("%n");
        
        // TODO klíner
        System.out.printf("%s cleanerList",name);
        for (var teacher : teacherList) {
            System.out.printf(" %s", teacher.getName());
        }
        System.out.printf("%n");

        System.out.printf("%s neighbouringRooms",name);
        for (var neighbour : neighbouringRooms) {
            System.out.printf(" %s", neighbour.getName());
        }
        System.out.printf("%n");
        
        System.out.printf("%s sticky %s%n",name,false);
        System.out.printf("%s cleaned %s%n",name,false);
        // TODO kieg
        int numOfVisitors = teacherList.size()+studentList.size() + 0;
        System.out.printf("%s numOfVisitors %d%n",name, numOfVisitors);
    }

}
