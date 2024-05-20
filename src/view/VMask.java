package view;

import java.awt.GridBagConstraints;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.IController;
import model.items.IItem;
import model.items.IVMMask;
import model.items.Mask;

public class VMask implements IVItems{
    IVMMask modelMask;
    public VMask(Mask m){
        modelMask = m;
    }

    @Override
    public void draw(IVStudent curPlayer, JButton btn, IController c) {
        System.out.println("VMASK DRAW");

        btn.setIcon(new ImageIcon("textures" + File.separator + "Mask.png"));
        btn.addActionListener(e -> { c.getCommands().useItem((IItem)modelMask); });
    }

}
