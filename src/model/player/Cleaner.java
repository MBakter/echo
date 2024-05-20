package model.player;

import model.*;
import model.items.IItem;

public class Cleaner extends Player implements IVMCleaner{
    public Cleaner(String s, ITimer t) {
        super(s, t);
    }
    public Cleaner(){super();}
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
            itemList.add(i);
            i.pickUp(this);
            return true;
        }
        boolean success = room.removeItem(i);
        if(success) {
            itemList.add(i);
            i.pickUp(this);
        }
        return success;
    }

    @Override
    public void getOut() {
        if(room == null)
        return;
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
}
