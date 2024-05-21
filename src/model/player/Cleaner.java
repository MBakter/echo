package model.player;

import model.*;
import model.items.IItem;

public class Cleaner extends Player implements IVMCleaner{
    public Cleaner(String s, ITimer t) {
        super(s, t);
    }
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

    /*
     * Magát ne tessékelje ki...
     */
    @Override
    public void getOut() {
        return;
    }
    @Override
    public EPlayerState getPlayerState() {
        return state;
    }
}
