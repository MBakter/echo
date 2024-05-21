package model.player;

import java.util.ArrayList;

import view.IVItems;
import view.IVRoom;

public interface IVMStudent {
    /*
     * View interfaceű, itemeink listája
     */
    public ArrayList<IVItems> getItemList();
    /*
     * Játékos állapotának lekérdezése
     */
    public EPlayerState getPlayerState();
    /*
     * Szoba viewos interfaceként való visszaadása
     */
    public IVRoom getVRoom();
    /*
     * Hátralévő idő (meddig eszméletlen)
     */
    public int getTime();
}
