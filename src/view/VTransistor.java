package view;

import java.awt.GridBagConstraints;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.IController;
import model.items.IItem;
import model.items.IVMTransistor;
import model.items.Transistor;

public class VTransistor implements IVItems{
    IVMTransistor modelTransistor;
    public VTransistor(Transistor t){
        modelTransistor = t;
    }

    @Override
    public void draw(IVStudent curPlayer, JButton btn, IController c) {
        System.out.println("VTRANSISTOR DRAW");

        btn.setIcon(new ImageIcon("textures" + File.separator + "Sponge.png"));
        btn.addActionListener(e -> { c.getCommands().useItem((IItem)modelTransistor); });
        btn.addActionListener(e -> {/* TODO popup gomb transistorhoz */});
    }

}
