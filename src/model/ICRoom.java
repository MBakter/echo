package model;

public interface ICRoom {
    public void addNeighbour(Room r);
    public void removeNeighbour(Room r);
    public Room split();
    public boolean merge(Room r);
    public void stepRoom();
}
