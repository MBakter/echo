package model.player;

import model.ITimer;
import model.Room;
import model.items.IItem;

public class Student extends Player {
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

}
