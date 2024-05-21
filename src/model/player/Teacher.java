package model.player;

import java.util.ArrayList;

import model.ITimer;
import model.Room;
import model.items.IItem;
import view.IVItems;

public class Teacher extends Player implements IVMTeacher {

    public Teacher(String s, ITimer t) {
        super(s,t);
    }
public Teacher(){};
    public boolean move(Room r) {
        boolean moveResult = r.add(this);
        if(moveResult){
            room = r;
        }
        return moveResult;
    }

    public void forceMove(Room r){
        room = r;
        r.fAdd(this);
    }
    
    public boolean pickUp(IItem i) {
        if(room == null){
            i.pickUp(this);
            return true;
        }
        boolean success = room.removeItem(i);
        if(success) {
            i.pickUp(this);
        }
        return success;
    }

    @Override
    public void getOut() {
        for (Room r : room.getNeighbours()) {
            boolean success = move(r);
            if (success) {
                return;
            }
        }
    }
    @Override
    public EPlayerState getPlayerState() {
        return state;
    }
    @Override
    public ArrayList<IVItems> getItems() {
        ArrayList<IVItems> vItemList = new ArrayList<>();
        for (IItem item : itemList) {
            item.acceptView(vItemList);
        }
        return vItemList;
    }
    @Override
    public int getTime() {
        return timer.getETA(this);
    }

}
