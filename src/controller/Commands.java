package controller;

import model.IVMRoom;
import model.Room;
import model.items.*;

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
    public void pickUpItem(IVMItems ivmi) {
        if(!Controller.CanPlayerMove())
            return;

        IItem item = (IItem) ivmi;
        Controller.curPlayer.pickUp(item);
        
        Controller.PlayerMoved();
    }

    @Override
    public void dropItem(IVMItems ivmi) {
        if(!Controller.CanPlayerMove())
            return;

        IItem item = (IItem) ivmi;
        Controller.curPlayer.dropItem(item);

        Controller.PlayerMoved();
    }

    @Override
    public void useItem(IVMItems ivmi) {
        if(!Controller.CanPlayerMove())
            return;

        IItem item = (IItem) ivmi;
        Controller.curPlayer.useItem(item);

        Controller.PlayerMoved();
    }

    @Override
    public void activateTransistor(IVMTransistor ivmt) {
        if(!Controller.CanPlayerMove())
            return;

        Transistor t =  (Transistor) ivmt;
        t.ActivateTransistor();

        Controller.PlayerMoved();
    }

    @Override
    public void pairTransistor(IVMTransistor ivmt1, IVMTransistor ivmt2) {
        if(!Controller.CanPlayerMove())
            return;

        Transistor t1 =  (Transistor) ivmt1;
        Transistor t2 =  (Transistor) ivmt2;
        t1.PairTransistor(t2);
        Controller.PlayerMoved();
    }

    @Override
    public void enterRoom(IVMRoom ivmr) {
        if(!Controller.CanPlayerMove())
            return;

        Room r = (Room) ivmr;
        
        System.out.println("Before move: " + Controller.curPlayer.getRoom().toString());
        Controller.curPlayer.move(r);
        System.out.println("After move: " + Controller.curPlayer.getRoom().toString());

        Controller.PlayerMoved();
    }
}
