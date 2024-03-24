package model.player;

import java.util.ArrayList;
import java.util.List;

import model.ITimer;
import model.Room;
import model.items.IItem;

public abstract class Player implements ITimer {

    Player(){
        System.out.println("<<create>> \""+this.toString()+"\"");
    }

    protected Room room;
    protected List<IItem> itemList = new ArrayList<>();
    protected EPlayerState state;
    protected controller.Timer timer;

    public EPlayerState getState() { return state; }
    public void setState(EPlayerState s) {
        state = s;
        System.out.println("\t"+this + ": state set to "+ state);
    }
    public Room getRoom() { return room; }

    public void move(Room r) {}
    public void RoomPoisoned() {
        System.out.println("\t"+this+": RoomPoisoned called");
        state = EPlayerState.UNCONSCIOUS;
        System.out.println("\t"+this+": State set to: "+state);
    };
    public void RoomCleanFromPoison() {
        System.out.println("\t"+this+": RoomCleanFromPoison called");
    }
    public void addItem(IItem i) {
        itemList.add(i);
    }
    public void removeItem(IItem i)  {}
    public void pickUp(IItem i) {}
    public void dropItem(IItem i) {}

    @Override
    public void timerEnd() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'timerEnd'");
    }
}
