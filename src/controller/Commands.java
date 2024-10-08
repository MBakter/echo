package controller;

import model.IVMRoom;
import model.Room;
import model.items.*;

public class Commands implements ICommands {
    @Override
    public void pickUpItem(IVMItems ivmi) {
        if (!Controller.CanPlayerMove())
            return;

        IItem item = (IItem) ivmi;
        Controller.curPlayer.pickUp(item);

        Controller.PlayerMoved();
    }

    @Override
    public void dropItem(IVMItems ivmi) {
        if (!Controller.CanPlayerMove())
            return;

        IItem item = (IItem) ivmi;
        Controller.curPlayer.dropItem(item);

        Controller.PlayerMoved();
    }

    @Override
    public void useItem(IVMItems ivmi) {
        if (!Controller.CanPlayerMove())
            return;

        IItem item = (IItem) ivmi;
        Controller.curPlayer.useItem(item);

        Controller.PlayerMoved();
    }

    @Override
    public void activateTransistor(IVMTransistor ivmt) {
        if (!Controller.CanPlayerMove())
            return;

        Transistor t = (Transistor) ivmt;
        t.ActivateTransistor();

        Controller.PlayerMoved();
    }

    @Override
    public void pairTransistor(IVMTransistor ivmt1, IVMTransistor ivmt2) {
        if (!Controller.CanPlayerMove())
            return;

        Transistor t1 = (Transistor) ivmt1;
        Transistor t2 = (Transistor) ivmt2;
        t1.PairTransistor(t2);
        Controller.PlayerMoved();
    }

    @Override
    public void enterRoom(IVMRoom ivmr) {
        if (!Controller.CanPlayerMove())
            return;

        Room r = (Room) ivmr;

        System.out.println("Before move: " + Controller.curPlayer.getRoom().toString());
        Controller.curPlayer.move(r);
        System.out.println("After move: " + Controller.curPlayer.getRoom().toString());

        Controller.PlayerMoved();
    }
}
