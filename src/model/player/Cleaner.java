package model.player;

import model.Room;
import model.items.IItem;

public class Cleaner extends Player {
    @Override
    public String toString(){
        return "Cleaner@"+Integer.toString(this.hashCode()).substring(0, 4);
    }

        /**
     * Az oktató megpróbál az r szobába mozogni
     * 
     * @param   r   a szoba ahová mozogni akar az okató, ennek hívjuk meg az addTeacher függvényét
     */
    public void move(Room r) {
        

    }

    @Override
    public void pickUp(IItem i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pickUp'");
    }
}
