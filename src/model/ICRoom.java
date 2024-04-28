package model;

import java.util.List;

public interface ICRoom {
    public void addNeighbour(Room r);
    public void removeNeighbour(Room r);
    public List<Room> getNeighbours();
    public Room split();
    public boolean merge(Room r);
    public void stepRoom();
}
