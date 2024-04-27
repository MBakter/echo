package model;

import java.util.ArrayList;
import java.util.List;

import model.items.IItem;
import model.player.Cleaner;
import model.player.Player;
import model.player.Student;
import model.player.Teacher;

public class Room implements ICRoom, IVRoom {
    private int maxPlayer;
    private int stickyCounter = 5;
    private List<ERoomEffects> effects = new ArrayList<>();
    private List<IItem> itemList = new ArrayList<>();
    private List<Student> studentList = new ArrayList<>();
    private List<Teacher> teacherList = new ArrayList<>();
    private List<Cleaner> cleanerList = new ArrayList<>();
    private List<Room> neighbouringRooms = new ArrayList<>();

    public Room() {
        System.out.println("<<create>> \"" + this + "\"");
        maxPlayer = 5;
    }

    public Room(int maxPlayer) {
        System.out.println("<<create>> \"" + this + "\"");
        this.maxPlayer = maxPlayer;
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
        s.getRoom().remove(s);
        for (int i = 0; i < teacherList.size(); i++) {
            s.TeacherAttacked();
        }
        return true;

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
        t.getRoom().remove(t);
        for (Student s : studentList) {
            s.TeacherAttacked();
        }
        return true;
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
        c.getRoom().remove(c);
        while (effects.contains(ERoomEffects.POISONED)) {
            addEffect(ERoomEffects.STICKY);
            removeEffect(ERoomEffects.POISONED);
        }
        for (Player p : getPlayers()) {
            p.getOut();
        }
        return true;
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
        for (Room n : nb) {
            addNeighbour(n);
            n.removeNeighbour(r);
            if (!n.neighbouringRooms.contains(r))
                n.addNeighbour(r);
        }
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

}
