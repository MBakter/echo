package view;

import java.io.File;

import javax.swing.*;

import controller.IController;
import model.IVMRoom;
import model.Room;

public class VRoom implements IVRoom {
    public IVMRoom modelRoom;

    public VRoom(Room r) {
        modelRoom = r;
    }

    @Override
    public IVMRoom getModelRoom() {
        return modelRoom;
    }

    @Override
    public void draw(IVStudent curPlayer, JButton door, IController c) {
        System.out.println("VROOM DRAW");

        door.setIcon(new ImageIcon("textures" + File.separator + "Door.png"));
        //door.addActionListener(e -> { System.out.println("Move " + curPlayer.getModelStudent().toString() + " To " + modelRoom.toString() ); });
        door.addActionListener(e -> { c.getCommands().enterRoom((Room)modelRoom); });
    }

}
