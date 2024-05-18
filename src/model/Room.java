package model;

import java.lang.ref.Cleaner.Cleanable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.items.IItem;
import model.player.Cleaner;
import model.player.Player;
import model.player.Cleaner;
import model.player.EPlayerState;
import model.player.Student;
import model.player.Teacher;
import test.IPrintStat;

public class Room implements ICRoom, IVRoom, IPrintStat, IVMRoom {
    private int maxPlayer;
    private int stickyCounter = 5;
    private String name;
    private List<ERoomEffects> effects = new ArrayList<>();
    private List<IItem> itemList = new ArrayList<>();
    private List<Student> studentList = new ArrayList<>();
    private List<Teacher> teacherList = new ArrayList<>();
    private List<Cleaner> cleanerList = new ArrayList<>();
    private List<Room> neighbouringRooms = new ArrayList<>();

    public Room() {

    }

    public Room(String name) {
        maxPlayer = 5;
        this.name = name;
    }

    public void setMax(int n) {
        maxPlayer = n;
    }

    public Room(int maxPlayer) {
        this.maxPlayer = maxPlayer;
    }

    /**
     * Visszaadja a szoba nevét
     *
     * @return A szoba neve
     */
    public String getName() {
        return name;
    }

    /**
     * Visszaadja a szobában lévő oktatokat
     *
     * @return A szobában lévő oktatók listája
     */
    public List<Teacher> getTeachers() {
        return teacherList;
    }

    /**
     * Visszaadja a szobában lévő hallgatókat
     *
     * @return A szobában lévő oktatók listája
     */
    public List<Student> getStudents() {
        return studentList;
    }

    /**
     * Visszaadja a szobában lévő tisztítókat
     *
     * @return A szobában lévő oktatók listája
     */
    public List<Cleaner> getCleaners() {
        return cleanerList;
    }

    /**
     * Hallgato hozzaadasa a szobahoz
     *
     * @param s Hallgato
     * @return Sikerult-e a hozzaadas
     */
    public boolean add(Student s) {
        boolean success = tryAdd(s);
        if (!success)
            return false;
        studentList.add(s);
        if(s.getRoom() != null)
            s.getRoom().remove(s);
        for (int i = 0; i < teacherList.size(); i++) {
            s.TeacherAttacked();
        }
        return true;

    }

    // Forced
    public void fAdd(Student s) {
        studentList.add(s);
        if(s.getRoom() != null)
            s.getRoom().remove(s);
    }

    /**
     * Hallgató eltávolítása a szoba listájából
     *
     * @param s Keresett hallgató
     * @return Benne volt-e a hallgató a listában
     */
    public boolean remove(Student s) {
        boolean contains = studentList.contains(s);
        if (contains) {
            studentList.remove(s);
        }

        return contains;
    }

    /**
     * Megpróbálja a jelenlegi szobába mozgatni az adott oktatót.
     *
     * @param t Az oktató, aki mozogni próbál a jelenlegi szobába
     * @return Sikerült-e mozogni a szobába
     */
    public boolean add(Teacher t) {
        boolean success = tryAdd(t);
        if (!success)
            return false;
        teacherList.add(t);
        if(t.getRoom() != null)
            t.getRoom().remove(t);
        for (Student s : studentList) {
            s.TeacherAttacked();
        }
        return true;
    }

    // forced
    public void fAdd(Teacher t) {
        teacherList.add(t);
        if(t.getRoom() != null)
            t.getRoom().remove(t);
    }

    /**
     * Oktató eltávolítása a szoba listájából
     *
     * @param s Keresett oktató
     * @return Benne volt-e az oktató a listában
     */
    public boolean remove(Teacher t) {
        boolean contains = teacherList.contains(t);
        if (contains) {
            teacherList.remove(t);
        }

        return contains;
    }

    /**
     * Megpróbálja a jelenlegi szobába mozgatni az adott takaritot.
     *
     * @param c A takarito, aki mozogni próbál a jelenlegi szobába
     * @return Sikerült-e mozogni a szobába
     */
    public boolean add(Cleaner c) {
        boolean success = tryAdd(c);
        if (!success)
            return false;
        cleanerList.add(c);
        if(c.getRoom() != null)
            c.getRoom().remove(c);
        if (effects.contains(ERoomEffects.POISONED))
            effects.add(ERoomEffects.STICKY);
        purifyRoom();
        for (Player p : getPlayers()) {
            p.getOut();
        }
        return true;
    }

    // Forced
    public void fAdd(Cleaner c) {
        cleanerList.add(c);
        if(c.getRoom() != null)
            c.getRoom().remove(c);
    }

    /**
     * Takarito eltávolítása a szoba listájából
     *
     * @param t Keresett takarito
     * @return Benne volt-e a takarito a listában
     */
    public boolean remove(Cleaner c) {
        boolean contains = cleanerList.contains(c);
        if (contains) {
            cleanerList.remove(c);
        }

        return contains;
    }

    /**
     * Hozzaadja a szobahoz a megadott szobat szomszednak
     *
     * @param r A szomszedos szoba
     */
    public void addNeighbour(Room r) {
        neighbouringRooms.add(r);
    }

    /**
     * Eltavolitja a szobat a szomszedok kozul
     *
     * @param r A szomszedos szoba
     */
    public void removeNeighbour(Room r) {
        neighbouringRooms.remove(r);
    }

    /**
     * Visszaadja a szobahoz tartozo szomszedos szobakat
     *
     * @return A szomszedos szobak listaja
     */
    public List<Room> getNeighbours() {
        return neighbouringRooms;
    }

    /**
     * Item hozzaadasa a szobahoz
     *
     * @param i Az item
     */
    public void addItem(IItem i) {
        itemList.add(i);
    }

    /**
     * Item eltavolitasa a szobabol
     *
     * @param i Az item
     * @return Sikerult-e az eltavolitas
     */
    public boolean removeItem(IItem i) {
        if (stickyCounter <= 0)
            return false;
        itemList.remove(i);
        return true;
    }

    /**
     * Effektus rakása a szobára, effektus aktiválódása esemény indítása
     *
     * @param e Az effektus, amit a szobára teszünk
     */
    public void addEffect(ERoomEffects e) {
        effects.add(e);
        if (e == ERoomEffects.POISONED) {
            List<Player> players = getPlayers();
            for (Player p : players) {
                p.RoomPoisoned();
            }
        }
    }

    public void addEffect(String eS) {
        if (eS.equals("TRANSISTOR_IN"))
            eS = "TRANSISTOR_INSIDE";
        ERoomEffects e;
        try {
            e = ERoomEffects.valueOf(eS);
            effects.add(e);
            if (e == ERoomEffects.POISONED) {
                for (Student s : studentList) {
                    s.RoomPoisoned();
                }
                for (Teacher t : teacherList) {
                    t.RoomPoisoned();
                }
            }
        } catch (Exception ex) {

        }
    }

    /**
     * Effektus eltávolítása a szobáról, effektus eltávolítása esemény indítása
     *
     * @param e Az effektus, amit a szobáról leveszünk
     */
    public void removeEffect(ERoomEffects e) {
        effects.remove(e);
        if (!effects.contains(ERoomEffects.POISONED)) {
            List<Player> players = getPlayers();
            for (Player p : players) {
                p.RoomCleanFromPoison();
            }
        }
    }

    /**
     * Megpróbálja a szobát ketté választani.
     *
     * @return az új szoba, ha sikerült ketté választani, egyébként null
     */
    public Room split() {
        if (getPlayers().size() > 0)
            return null;
        if (effects.contains(ERoomEffects.TRANSISTOR_INSIDE))
            return null;
        Room newRoom = new Room(maxPlayer);
        newRoom.addNeighbour(this);
        this.addNeighbour(newRoom);
        return newRoom;
    }

    /**
     * Megpróbálja a szobát összevonni egy másik szobával.
     *
     * @param r a másik szoba
     * @return Sikerült-e összevonni a két szobát
     */
    public boolean merge(Room r) {
        if (getPlayers().size() > 0 || r.getPlayers().size() > 0)
            return false;
        if (effects.contains(ERoomEffects.TRANSISTOR_INSIDE) || r.effects.contains(ERoomEffects.TRANSISTOR_INSIDE))
            return false;
        List<Room> nb = r.getNeighbours();
        List<IItem> items = r.itemList;
        for (Room n : nb)
            addNeighbour(n);
        for (IItem i : items)
            addItem(i);
        return true;
    }

    public String toString() {
        return "Room@" + Integer.toString(this.hashCode()).substring(0, 4);
    }

    /**
     * A szoba léptetése
     */
    public void stepRoom() {
        for (int i = 0; i < teacherList.size(); i++)
            for (Student s : studentList) {
                s.TeacherAttacked();
            }
    }

    public void purifyRoom() {
        while (effects.contains(ERoomEffects.POISONED)) {
            removeEffect(ERoomEffects.POISONED);
        }
        for (Player p : getPlayers()) {
            p.RoomCleanFromPoison();
        }
    }

    public boolean equals(Room r) {
        return this.hashCode() == r.hashCode();
    }

    /**
     * Visszaadja a szobában lévő játékosok számát
     *
     * @return A szobában lévő játékosok száma
     */
    private int getPlayersCount() {
        return studentList.size() + teacherList.size() + cleanerList.size();
    }

    /**
     * Visszaadja a szobában lévő összes játékost
     *
     * @return A szobában lévő játékosok listája
     */
    private List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        players.addAll(studentList);
        players.addAll(teacherList);
        players.addAll(cleanerList);
        return players;
    }

    /**
     * Megpróbálja a jelenlegi szobába mozgatni az adott jatekost.
     *
     * @param p A jatekos, aki mozogni próbál a jelenlegi szobába
     * @return Sikerült-e mozogni a szobába
     */
    private boolean tryAdd(Player p) {
        // TODO Ha tele van, de meg nincs szobaja?
        if (p.getRoom() == null)
            return true;
        // If one of rooms is cursed
        if (effects.contains(ERoomEffects.CURSED) || p.getRoom().effects.contains(ERoomEffects.CURSED))
            return false;
        // If player limit is reached
        if (maxPlayer > getPlayersCount())
            return false;

        // If the previous room is not poisoned, but the new one is
        if (effects.contains(ERoomEffects.POISONED) && !p.getRoom().effects.contains(ERoomEffects.POISONED))
            p.RoomCleanFromPoison();
        // If the previous room is poisoned, but the new one is not
        if (p.getRoom().effects.contains(ERoomEffects.POISONED) && !effects.contains(ERoomEffects.POISONED))
            p.RoomPoisoned();

        if (effects.contains(ERoomEffects.STICKY) && stickyCounter > 0) {
            stickyCounter--;
        }

        return true;
    }

    @Override
    public void printStat(String faszom) {
        System.out.printf("%s effects", name);
        for (var effect : effects) {
            System.out.printf(" %s", effect);
        }
        System.out.printf("%n");

        System.out.printf("%s itemList", name);
        for (var item : itemList) {
            System.out.printf(" %s", item.getName());
        }
        System.out.printf("%n");

        System.out.printf("%s studentList", name);
        for (var student : studentList) {
            System.out.printf(" %s", student.getName());
        }
        System.out.printf("%n");

        System.out.printf("%s teacherList", name);
        for (var teacher : teacherList) {
            System.out.printf(" %s", teacher.getName());
        }
        System.out.printf("%n");

        // TODO klíner
        System.out.printf("%s cleanerList", name);
        for (var teacher : teacherList) {
            System.out.printf(" %s", teacher.getName());
        }
        System.out.printf("%n");

        System.out.printf("%s neighbouringRooms", name);
        for (var neighbour : neighbouringRooms) {
            System.out.printf(" %s", neighbour.getName());
        }
        System.out.printf("%n");

        System.out.printf("%s sticky %s%n", name, false);
        System.out.printf("%s cleaned %s%n", name, false);
        // TODO kieg
        int numOfVisitors = teacherList.size() + studentList.size() + 0;
        System.out.printf("%s numOfVisitors %d%n", name, numOfVisitors);
    }

    @Override
    public void statesOptions() {
        for (var e : ERoomEffects.values()) {
            System.out.printf("\t%s%n", e);
        }
    }

}
