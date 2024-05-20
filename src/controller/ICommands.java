package controller;

public interface ICommands {
    public void pickUpItem(int index);
    public void dropItem(int index);
    public void useItem(int index);
    public void activateTransistor(int index);
    public void pairTransistor(int index1, int index2);
    public void enterRoom(int index);

}
