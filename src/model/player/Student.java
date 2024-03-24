package model.player;

import model.Room;
import model.items.IItem;

public class Student extends Player {
    @Override
    public String toString(){
        return "Student@"+Integer.toString(this.hashCode()).substring(0, 4);
    }
    /**
     * A hallgató megpróbál az r szobába mozogni
     * 
     * @param   r   a szoba ahová mozogni akar a hallgató, ennek hívjuk meg az addStudent függvényét
     */

    public Student(){
        System.out.println("<<create>> Student");
    }
    public void move(Room r) {
        System.out.println("\t"+this+": current room is "+room);
        System.out.println(""+this+": addStudent("+this+") -> "+r);        
        boolean moveResult = r.addStudent(this);
        if(moveResult){
            room = r;
            System.out.println("\t"+this+": moving to "+r+" successful");
            System.out.println("\t"+r+": Students in room: "+r.getStudents());
        }            
        else{
            System.out.println("\t"+this+": moving to "+r+" failed");
        }            
        System.out.println("\t"+this+": current room is "+room);

    }
    /**
     * A hallgató megpróbálja megmenti magát a méregtől a tárgyaival
     */
    @Override
    public void RoomPoisoned() {

        System.out.println("\t"+this+": RoomPoisoned called");
        for (IItem iItem : itemList) 
            if(iItem.RoomPoisoned(this)) {
                System.out.println("\t"+this+": Saved from poison!");
                return;
            }

        state = EPlayerState.UNCONSCIOUS;
        System.out.println("\t"+this+": State set to: "+state);
      
    };
    /**
     * A szobáról lekerül a méreg, a hallgató itemei erről értesülnek
     */
    @Override
    public void RoomCleanFromPoison() {
        System.out.println("\t"+this+": RoomCleanFromPoison called");
        for (IItem iItem : itemList) {
            iItem.RoomCleanFromPoison(this);
        }
    }

    public void useItem(IItem i) {
        i.useItem(this);
    }

    /**
     * A hallgatót megtámadja egy oktató, tárgyaival megpróbálja menteni magát
     */
    public void TeacherAttacked() {
        System.out.println("\t"+this+": TeacherAttacked called!");
        for (IItem item : itemList) 
            if(item.TeacherAttackable(this)) {
                System.out.println(this + ": Survived attack");
                return;      
            }

        for (IItem item : itemList) 
            if(item.TeacherAttacked(this)) {
                System.out.println(this + ": Survived attack");
                return;      
            }
        
        state = EPlayerState.DEAD;
        System.out.println(this + ": setState -> " + state);
    }

}
