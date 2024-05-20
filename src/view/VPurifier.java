package view;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import controller.IController;
import model.Room;
import model.items.IItem;
import model.items.IVMPurifier;
import model.items.Purifier;

public class VPurifier implements IVItems{
    IVMPurifier modelPurifier;
    public VPurifier(Purifier p){
        modelPurifier = p;
    }

    @Override
    public void draw(IVStudent curPlayer, JButton btn, IController c) {
        System.out.println("VPURIFIER DRAW");

        btn.setIcon(new ImageIcon("textures" + File.separator + "Purifier.png"));
        btn.addActionListener(e -> { c.getCommands().useItem((IItem)modelPurifier); });
    }

}
