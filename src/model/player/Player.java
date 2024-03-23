package model.player;

import java.util.List;

import model.ITimer;
import model.Room;
import model.items.IItem;

public abstract class Player implements ITimer {
    Player(){
        System.out.println("\""+this.toString()+"\" created");
    }
    protected Room room;
    protected List<IItem> itemList;
    protected EPlayerState state;
    protected controller.Timer timer;

    public EPlayerState getState() { return state; }
    public void setState(EPlayerState s) {}
    public Room getRoom() { return room; }

    public void move(Room r) {}
    public void RoomPoisoned() {}
    public void RoomCleanFromPoison() {}
    public void addItem(IItem i) {}
    public void removeItem(IItem i)  {}
    public void pickUp(IItem i) {}
    public void dropItem(IItem i) {}

    @Override
    public void timerEnd() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'timerEnd'");
    }
}
