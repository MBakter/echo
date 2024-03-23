package model.player;

import model.Room;
import model.items.IItem;

public class Student extends Player {

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
            //System.out.println("Initial room set for sudent");
            System.out.println("Student \""+ this.toString() +"\"s initial room is set to: \"" + r.toString() +"\"");
            return;
        }
        //System.out.println("Student tries to move");
        System.out.println("Student \""+ this.toString() +"\" calls room \""+ r.toString() +".addStudent(this)\"");
        boolean moveResult = r.addStudent(this);
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
