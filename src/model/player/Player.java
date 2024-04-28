package model.player;

import java.util.ArrayList;
import java.util.List;

import model.ERoomEffects;
import model.ITimer;
import model.Room;
import model.items.IItem;
import test.IPrintStat;

public abstract class Player implements ITimer, IPrintStat {
    private String name;
    public String getName(){
        return name;
    }
    protected Player(String s){
        name = s;
        ////System.out.println("<<create>> \""+this.toString()+"\"");
    }
    protected Player(){
        ////System.out.println("<<create>> \""+this.toString()+"\"");
    }
    protected Room room;
    protected List<IItem> itemList = new ArrayList<>();
    protected EPlayerState state;
    protected controller.Timer timer;
    public List<IItem> getItems(){
        return this.itemList;
    }
    public EPlayerState getState() { return state; }
    public void setState(EPlayerState s) {
        state = s;
        ////System.out.println("\t"+this + ": state set to "+ state);
        
        /* if(state == EPlayerState.UNCONSCIOUS)
            timer.startTimer(this, 2); */
    }
    public void setState(String s) {
            state = EPlayerState.valueOf(s);
    }
    public Room getRoom() { return room; }
    public void setRoom(Room r) { 
        ////System.out.println("\t"+this+": setRoom called");
        room = r; 
    }

    public abstract void move(Room r);
    public abstract void forceMove(Room r);
    /**
     * Alap RoomPoisoned függvény, eszméletét veszti a Player
     */
    public void RoomPoisoned() {
        ////System.out.println("\t"+this+": RoomPoisoned called");
        state = EPlayerState.UNCONSCIOUS;
        ////System.out.println("\t"+this+": State set to: "+state);
    };
    public void RoomCleanFromPoison() {
        ////System.out.println("\t"+this+": RoomCleanFromPoison called");
    }
    public void addItem(IItem i) {
        ////System.out.println("\t"+this+": addItem called");
        itemList.add(i);

        ////System.out.println(this.toString() + ": Item added: " + i.toString());
    }
    public void removeItem(IItem i)  {
        ////System.out.println(this.toString() + ": Item removed: " + i.toString());
        itemList.remove(i);
    }

    public abstract void pickUp(IItem i);

    public void dropItem(IItem i) {
        ////System.out.println(this.toString() + ": dropItem(" + this.toString() + ")-> " + i.toString());
        i.dropItem(this);
        ////System.out.println(this.toString() + ": addItem(" + i.toString() + ")-> " + room.toString());
        room.addItem(i);
    }

    @Override
    public void timerEnd() {
        state = EPlayerState.ALIVE;
    }
    
    @Override
    public void printStat(String fasz) {
        //System.out.printf("%s room %\n", this.toString());
/*         System.out.printf("%s room %s%n", name, room.getName());
        System.out.printf("%s EPlayerState %s%n", name, state);
        System.out.printf("%s itemList", name);
        for (var item : itemList) {
            System.out.printf(" %s", item.getName());
        }
        System.out.printf("%n"); */
    }

    @Override
    public void statesOptions() {
        for (var e : EPlayerState.values()) {
            System.out.printf("\t%s%n", e);
        }
    }
}
