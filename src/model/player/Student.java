package model.player;

import model.Room;
import model.items.IItem;

public class Student extends Player {
    @Override
    public String toString(){
        return "Student@"+Integer.toString(this.hashCode()).substring(0, 4);
    }
    public Student(){
        //System.out.println("Student created");
    }
    /**
     * A hallgató megpróbál az r szobába mozogni
     * 
     * @param   r   a szoba ahová mozogni akar a hallgató, ennek hívjuk meg az addStudent függvényét
     */
    public void move(Room r) {
         if(room == null){
            room = r;
            System.out.println("\t"+this+": room initially set to "+r);
            return;
        } 
        System.out.println("\t"+this+": current room is "+room);
        System.out.println(this+": addStudent("+this+") -> "+r);        
        boolean moveResult = r.addStudent(this);
        System.out.println("\t"+this+": current room is "+room);
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
