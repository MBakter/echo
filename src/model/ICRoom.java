package model;

import java.util.List;

public interface ICRoom {
    /*
     * Szomszéd hozzáadása
     */
    public void addNeighbour(Room r);
    /*
     * Szomszéd törlése
     */
    public void removeNeighbour(Room r);
    /*
     * Szomszédok lekérdezése
     */
    public List<Room> getNeighbours();
    /*
     * Szoba splittelése
     */
    public Room split();
    /*
     * Jelenlegi szoba mergelése a paraméterben adottal
     */
    public boolean merge(Room r);
    /*
     * A szoba léptetése
     */
    public void stepRoom();
}
