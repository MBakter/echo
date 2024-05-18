package model.player;

import java.util.ArrayList;

import model.ITimer;
import model.Room;
import model.items.IItem;
import view.IVItems;

public class Student extends Player implements IVMStudent{
    public Student(String s, ITimer t) {
        super(s,t);
    }
    public Student(){super();}
    public boolean move(Room r) {
        boolean moveResult = r.add(this);
        if (moveResult) {
            room = r;
        }
        return moveResult;
    }
    public void forceMove(Room r){
        room = r;
        r.fAdd(this);
    }

    @Override
    public void RoomPoisoned() {
        for (IItem iItem : itemList)
            if (iItem.RoomPoisoned(this))
                return;

        super.RoomPoisoned();
    };

    @Override
    public void RoomCleanFromPoison() {
        for (IItem iItem : itemList) {
            iItem.RoomCleanFromPoison(this);
        }
        super.RoomCleanFromPoison();
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

    public void useItem(IItem i) {
        i.useItem(this);
    }

    public void TeacherAttacked() {
        for (IItem item : itemList)
            if (item.TeacherAttackable(this)) {
                return;
            }

        for (IItem item : itemList)
            if (item.TeacherAttacked(this)) {
                return;
            }

        state = EPlayerState.DEAD;
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
    public ArrayList<IVItems> getItemList() {
        ArrayList<IVItems> vItemList = new ArrayList<>();
        for (IItem item : itemList) {
            
        }
    }
    @Override
    public EPlayerState getPlayerState() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerState'");
    }
    @Override
    public int getTime() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTime'");
    }

}
