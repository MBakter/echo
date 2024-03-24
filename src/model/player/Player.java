package model.player;

import java.util.ArrayList;
import java.util.List;

import model.ITimer;
import model.Room;
import model.items.IItem;

public abstract class Player implements ITimer {
    protected Room room;
    protected List<IItem> itemList = new ArrayList<>();
    protected EPlayerState state;
    protected controller.Timer timer;

    public EPlayerState getState() { return state; }
    public void setState(EPlayerState s) {}
    public Room getRoom() { return room; }
    public void setRoom(Room r) { room = r; }

    public void move(Room r) {}
    public void RoomPoisoned() {}
    public void RoomCleanFromPoison() {}
    public void addItem(IItem i) {
        itemList.add(i);
    }
    public void removeItem(IItem i)  {}
    public void pickUp(IItem i) {
        i.pickUp(this);
    }
    public void dropItem(IItem i) {}

    @Override
    public void timerEnd() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'timerEnd'");
    }
}
