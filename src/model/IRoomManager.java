package model;

public interface IRoomManager {
    public Room split();
    public boolean merge(Room r);
}
