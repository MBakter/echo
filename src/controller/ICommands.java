package controller;

import model.Room;
import model.items.IItem;
import model.items.Transistor;

public interface ICommands {
    public void pickUpItem(IItem item);
    public void dropItem(IItem item);
    public void useItem(IItem item);
    public void activateTransistor(Transistor t);
    public void pairTransistor(Transistor t1, Transistor t2);
    public void enterRoom(Room r);
    //public void enterRoom(IVMRoom r);

}
