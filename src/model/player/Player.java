package model.player;

import java.util.ArrayList;
import java.util.List;
import model.*;
import model.items.IItem;
import test.IPrintStat;

public abstract class Player implements ITimedEntity, IPrintStat {

    protected Room room;
    protected List<IItem> itemList = new ArrayList<>();
    protected EPlayerState state;
    protected ITimer timer;
    private String name;
    private final int TIME = 3;
    public Player(){

    }
    Player(String s, ITimer t) {
        name = s;
        state = EPlayerState.ALIVE;
        t.addEntity(this);
        timer = t;
    }

    public String getName() {
        return name;
    }

    public EPlayerState getState() {
        return state;
    }

    public void setState(EPlayerState s) {
        state = s;
        if(s == EPlayerState.UNCONSCIOUS) {
            timer.startTimer(this, TIME);
        }
    }

    public void setState(String s) {
        state = EPlayerState.valueOf(s);
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room r) {
        room = r;
    }

    public abstract void getOut();

    public abstract boolean move(Room r);

    public abstract void forceMove(Room r);

    public void RoomPoisoned() {
        state = EPlayerState.UNCONSCIOUS;
        timer.startTimer(this, TIME);
        ArrayList<IItem> myItems = new ArrayList<>();
        myItems.addAll(itemList);
        for (IItem i : myItems) {
            dropItem(i);
        }
    };

    public void RoomCleanFromPoison() {
        if (state == EPlayerState.UNCONSCIOUS) {
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
        if (room == null || room.getRoomItems().size() >= 10)
            return;
        room.addItem(i);
        i.dropItem(this);
    }

    public void useItem(IItem i) {
        return;
    }

    @Override
    public void timerEnd() {
        RoomCleanFromPoison();
    }

    @Override
    public void printStat(String asd) {
        String roomName = "";
        if(room != null)
            roomName = room.getName();
        System.out.printf("%s room %s%n", name, roomName);
        System.out.printf("%s EPlayerState %s%n", name, state);
        System.out.printf("%s itemList", name);
        for (var item : itemList) {
            System.out.printf(" %s", item.getName());
        }
        System.out.printf("%n");
    }

    @Override
    public void statesOptions() {
        for (var e : EPlayerState.values()) {
            System.out.printf("\t%s%n", e);
        }
    }
}
