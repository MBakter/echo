package controller;

import model.IVMRoom;
import model.items.IVMItems;
import model.items.IVMTransistor;

public interface ICommands {
    /*
     * Tárgy felvétele szobában a földről
     */
    public void pickUpItem(IVMItems item);

    /*
     * Tárgy eldobása inventoryból a szobába a földre
     */
    public void dropItem(IVMItems item);

    /*
     * Inventoryban lévő tárgy használata
     */
    public void useItem(IVMItems item);

    /*
     * Tranzisztor aktiválása
     */
    public void activateTransistor(IVMTransistor t);

    /*
     * Két tranzisztor párosítása
     */
    public void pairTransistor(IVMTransistor t1, IVMTransistor t2);

    /*
     * Szobába lépés
     */
    public void enterRoom(IVMRoom r);
}
