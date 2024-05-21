package model.player;

import java.util.ArrayList;

import model.ITimer;
import model.Room;
import model.items.IItem;
import view.IVItems;
import view.IVRoom;
import view.VRoom;

public class Student extends Player implements IVMStudent{

    private static int TIME = 4;
    public Student(){};

    public Student(String s, ITimer t) {
        super(s,t);
    }

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
            i.pickUp(this);
            return true;
        }
        boolean success = room.removeItem(i);
        if(success) {
            i.pickUp(this);
        }
        return success;
    }

    public void useItem(IItem i) {
        i.useItem(this);
    }

    public void TeacherAttacked() {
        System.out.println("\nCALLED\n");
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
            item.acceptView(vItemList);
        }
        return vItemList;
    }
    @Override
    public EPlayerState getPlayerState() {
        return state;
    }
    @Override
    public int getTime() {
        return TIME;
    }
    @Override
    public IVRoom getVRoom(){
            return new VRoom(room);
    }

}
