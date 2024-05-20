package controller;

import model.IVMRoom;
import model.player.IVMStudent;

public interface ICommands {
    public void pickUpItem(int index);
    public void dropItem(int index);
    public void useItem(int index);
    public void activateTransistor(int index);
    public void pairTransistor(int index1, int index2);
    public void enterRoom(int index);
    public void enterRoom(IVMRoom r);

}
