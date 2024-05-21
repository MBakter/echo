package view;

import java.io.File;

import javax.swing.*;

import java.awt.event.*;

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

        JPopupMenu jp = new JPopupMenu("RoomName");
        jp.add(new JLabel("Name: "+modelRoom.getName()));
        
        
        door.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(mouseEvent.getButton() == (MouseEvent.BUTTON3)){   
                    jp.show(door, 100, 100);
                    jp.setLocation(mouseEvent.getXOnScreen(), mouseEvent.getYOnScreen());
                }

            }
        });

    }

}
