package model.player;

import java.util.ArrayList;

import view.IVItems;

public interface IVMTeacher {
    /*
     * State lekérdezése
     */
    public EPlayerState getPlayerState();
    /*
     * View beli itemekként a teacher itemeinek listája
     */
    public ArrayList<IVItems> getItems();
    /*
     * Idő lekérdezése (meddig eszméletlen még)
     */
    public int getTime();
}
