package model.player;

import model.Room;

public class Teacher extends Player {
    @Override
    public String toString(){
        return "Teacher@"+Integer.toString(this.hashCode()).substring(0, 4);
    }

        /**
     * Az oktató megpróbál az r szobába mozogni
     * 
     * @param   r   a szoba ahová mozogni akar az okató, ennek hívjuk meg az addTeacher függvényét
     */
    public void move(Room r) {
        System.out.println("\t"+this+": current room is "+room);
        System.out.println(""+this+": addTeacher("+this+") -> "+r);        
        boolean moveResult = r.addTeacher(this);
        if(moveResult){
            room = r;
            System.out.println("\t"+this+": moving to "+r+" successful");
            System.out.println("\t"+r+": Teachers in room: "+r.getTeachers());
        }            
        else{
            System.out.println("\t"+this+": moving to "+r+" failed");
        }            
        System.out.println("\t"+this+": current room is "+room);

    }

}
