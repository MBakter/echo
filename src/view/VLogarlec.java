package view;

import java.awt.GridBagConstraints;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.IController;
import model.items.IItem;
import model.items.IVMLogarlec;
import model.items.Logarlec;

public class VLogarlec implements IVItems{
    IVMLogarlec modelLogarlec;
    public VLogarlec(Logarlec l){
        modelLogarlec = l;
    }

    @Override
    public void draw(IVStudent curPlayer, JButton btn, IController c) {
        System.out.println("VLOGARLEC DRAW");

        btn.setIcon(new ImageIcon("textures" + File.separator + "Mask.png"));
        btn.addActionListener(e -> { c.getCommands().useItem((IItem)modelLogarlec); });
    }

}
