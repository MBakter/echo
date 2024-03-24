package model.player;

import java.util.ArrayList;
import java.util.List;

import model.ITimer;
import model.Room;
import model.items.IItem;

public abstract class Player implements ITimer {

    Player(){
        System.out.println("\n<<create>> \""+this.toString()+"\"");
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
    public void setRoom(Room r) { room = r; }

    public void move(Room r) {}

    /**
     * Alap RoomPoisoned függvény, eszméletét veszti a Player
     */
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
