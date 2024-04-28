package model.player;

import java.util.ArrayList;
import java.util.List;
import model.*;
import model.items.IItem;

public abstract class Player implements ITimer {
    protected Room room;
    protected List<IItem> itemList = new ArrayList<>();
    protected EPlayerState state;
    protected controller.Timer timer;

    Player(ITimer t) {
        state = EPlayerState.ALIVE;
        t.addItem(this);
    }

    public EPlayerState getState() {
        return state;
    }

    public void setState(EPlayerState s) {
        state = s;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room r) {
        room = r;
    }

    public abstract void getOut();
    public abstract boolean move(Room r);


    public void RoomPoisoned() {
        state = EPlayerState.UNCONSCIOUS;
        timer.startTimer(this, 3);
        for (IItem i : itemList) {
            dropItem(i);
        }
    };

    public void RoomCleanFromPoison() {
        if(state == EPlayerState.UNCONSCIOUS) {
            state = EPlayerState.ALIVE;
        }
    }

    public void addItem(IItem i) {
        itemList.add(i);
    }

    public void removeItem(IItem i) {
        itemList.remove(i);
    }

    public abstract boolean pickUp(IItem i);

    public void dropItem(IItem i) {
        i.dropItem(this);
        room.addItem(i);
    }

    @Override
    public void timerEnd() {
        RoomCleanFromPoison();
    }
}
