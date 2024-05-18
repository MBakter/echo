package model.player;

import java.util.ArrayList;

import model.ITimer;
import model.Room;
import model.items.IItem;
import view.VItem;

public class Teacher extends Player implements IVMTeacher {

    public Teacher(String s, ITimer t) {
        super(s,t);
    }
    public Teacher(){super();}
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
        for (Room r : room.getNeighbours()) {
            boolean success = move(r);
            if (success) {
                return;
            }
        }
    }
    @Override
    public EPlayerState getPlayerState() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerState'");
    }
    @Override
    public ArrayList<VItem> getItems() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getItems'");
    }
    @Override
    public int getTime() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTime'");
    }

}
