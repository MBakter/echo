package model.player;

import model.Room;
import model.items.IItem;

public class Student extends Player {

    public Student(){
        System.out.println("<<create>> Student");
    }
    public void move(Room r) {

    }

    public void TeacherAttacked() {
        for (IItem item : itemList) 
            if(item.TeacherAttackable(this)) 
                return;      

        for (IItem item : itemList) 
            if(item.TeacherAttacked(this))
                return;
        
        state = EPlayerState.DEAD;
    }

    public void TeacherAttackable() {

    }

}
