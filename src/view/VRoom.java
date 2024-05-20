package view;

import java.awt.*;
import java.awt.GridBagLayout;

import javax.swing.*;
import javax.swing.JPanel;
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
    public JPanel draw(IVStudent curPlayer, GridBagConstraints c) {
        JPanel roomPanel = new JPanel(new GridBagLayout());
        roomPanel.setOpaque(false);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 3;
        c.insets = new Insets(58, 85, 0, 0);
        c.ipady = 0;
        c.weightx = 0;
        c.weighty = 0;

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 4; i++) {
                JButton door = new JButton();
                GridBagConstraints c2 = new GridBagConstraints();
                door.setPreferredSize(new Dimension(75, 150));
                c2.gridx = i;
                c2.gridy = j;
                roomPanel.add(door, c2);
            }
        }
        
        return roomPanel;
    }

}
