package controller;

import model.IVMRoom;
import model.items.IVMItems;
import model.items.IVMTransistor;


public interface ICommands {
    public void pickUpItem(IVMItems item);
    public void dropItem(IVMItems item);
    public void useItem(IVMItems item);
    public void activateTransistor(IVMTransistor t);
    public void pairTransistor(IVMTransistor t1, IVMTransistor t2);
    public void enterRoom(IVMRoom r);
    //public void enterRoom(IVMRoom r);

}
