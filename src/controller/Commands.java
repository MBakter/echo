package controller;

import view.IVItems;
import view.IVStudent;
import view.VTransistor;
import model.IVMRoom;
import model.Room;
import model.items.*;
import model.player.IVMStudent;

public class Commands implements ICommands{

/*     //public Controller controller;

    public Commands(Controller c){
        //controller = c;
    }

    public Commands() { }

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

    @Override
    public void enterRoom(IVMRoom r) {
        if(!Controller.CanPlayerMove())
            return;
        
        System.out.println("Before move: " + Controller.curPlayer.getRoom().toString());
        Controller.curPlayer.move((Room)r);
        System.out.println("After move: " + Controller.curPlayer.getRoom().toString());
        Controller.PlayerMoved();
    } */

    @Override
    public void pickUpItem(IItem item) {
        Controller.curPlayer.pickUp(item);
    }

    @Override
    public void dropItem(IItem item) {
        Controller.curPlayer.dropItem(item);
    }

    @Override
    public void useItem(IItem item) {
        Controller.curPlayer.useItem(item);
    }

    @Override
    public void activateTransistor(Transistor t) {
        t.ActivateTransistor();
    }

    @Override
    public void pairTransistor(Transistor t1, Transistor t2) {
        t1.PairTransistor(t2);
    }

    @Override
    public void enterRoom(Room r) {
        if(!Controller.CanPlayerMove())
            return;
        
        System.out.println("Before move: " + Controller.curPlayer.getRoom().toString());
        Controller.curPlayer.move(r);
        System.out.println("After move: " + Controller.curPlayer.getRoom().toString());
        Controller.PlayerMoved();
    }
}
