package model.player;

import java.util.List;

import model.ITimer;
import model.Room;
import model.items.IItem;

public abstract class Player implements ITimer {
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
    public void setRoom (Room r){
        room = r;
    }
    public void addItem(IItem i) {
        System.out.print("addItem()->Player");
    }
    public void removeItem(IItem i)  {
        System.out.print("removeItem()->Player");
    }
    public void pickUp(IItem i) {}
    public void dropItem(IItem i) {
        System.out.print("dropItem()->Player");
        i.dropItem(this);
        room.addItem(i);
    }

    @Override
    public void timerEnd() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'timerEnd'");
    }
}
