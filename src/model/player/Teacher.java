package model.player;

import model.Room;

public class Teacher extends Player {
    @Override
    public String toString(){
        return "Teacher@"+this.hashCode()/10000;
    }

        /**
     * Az oktató megpróbál az r szobába mozogni
     * 
     * @param   r   a szoba ahová mozogni akar az okató, ennek hívjuk meg az addTeacher függvényét
     */
    public void move(Room r) {
        r.addTeacher(this);
        System.out.println("Teacher called room's addTeacher");
    }

}
