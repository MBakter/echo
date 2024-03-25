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
        
        /* if(state == EPlayerState.UNCONSCIOUS)
            timer.startTimer(this, 2); */
    }
    public Room getRoom() { return room; }
    public void setRoom(Room r) { 
        System.out.println("\t"+this+": setRoom called");
        room = r; 
    }

    public void move(Room r) {    }

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
        System.out.println("\t"+this+": addItem called");
        itemList.add(i);

        System.out.println(this.toString() + ": Item added: " + i.toString());
    }
    public void removeItem(IItem i)  {
        System.out.println(this.toString() + ": Item removed: " + i.toString());
        itemList.remove(i);
    }
    public void pickUp(IItem i) {
        System.out.println("\t"+this+": pickUp called");

        System.out.println(""+this+": pickUp("+this+") -> "+i); 
        i.pickUp(this);
        room.removeItem(i);
    }
    public void dropItem(IItem i) {
        System.out.println(this.toString() + ": dropItem(" + this.toString() + ")-> " + i.toString());
        i.dropItem(this);
        System.out.println(this.toString() + ": addItem(" + i.toString() + ")-> " + room.toString());
        room.addItem(i);
    }

    @Override
    public void timerEnd() {
        state = EPlayerState.ALIVE;
    }
}
