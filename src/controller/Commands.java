package controller;

import view.IVItems;
import view.IVStudent;
import view.VTransistor;
import model.items.*;

public class Commands implements ICommands{

    //public Controller controller;

    public Commands(Controller c){
        //controller = c;
    }

    @Override
    public void pickUpItem(int index) {
        Controller.curPlayer.pickUp(Controller.curPlayer.getRoom().getItemList().get(index));
    }

    @Override
    public void dropItem(int index) {
        Controller.curPlayer.dropItem(Controller.curPlayer.getItems().get(index));
    }

    @Override
    public void useItem(int index) {
        Controller.curPlayer.useItem(Controller.curPlayer.getItems().get(index));
    }

    @Override
    public void activateTransistor(int index) {        
        try {
            Transistor t = (Transistor) Controller.curPlayer.getItems().get(index);
            t.ActivateTransistor();
        } catch (Exception e) {
            System.out.println("Not a transistor!!!");
        }
        
    }

    @Override
    public void pairTransistor(int index1, int index2) {

        try {
            Transistor t1 = (Transistor) Controller.curPlayer.getItems().get(index1);
            Transistor t2 = (Transistor) Controller.curPlayer.getItems().get(index2);
            t1.PairTransistor(t2);
        } catch (Exception e) {
            System.out.println("Not a transistor!!!");
        }
    }

    @Override
    public void enterRoom(int index) {
        Controller.curPlayer.move(Controller.curPlayer.getRoom().getNeighbours().get(index));
    }

}
