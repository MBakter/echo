package view;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import controller.IController;
import model.items.IVMLogarlec;
import model.items.Logarlec;

public class VLogarlec implements IVItems{
    IVMLogarlec modelLogarlec;
    public VLogarlec(Logarlec l){
        modelLogarlec = l;
    }

    @Override
    public void draw(IVStudent curPlayer, JButton btn, IController c) {
        System.out.println("VLOGARLEC DRAWG");

        btn.setIcon(new ImageIcon("textures" + File.separator + "LogarlecRoom.png"));
        btn.addActionListener(e -> { c.getCommands().pickUpItem(modelLogarlec); });
    }

    @Override
    public void draw(IVStudent curPlayer, JLabel label, IController c) {
        System.out.println("VLOGARLEC DRAW");

        label.setIcon(new ImageIcon("textures" + File.separator + "Logarlec.png"));
        //btn.addActionListener(e -> { c.getCommands().useItem((IItem)modelLogarlec); })
    }

    @Override
    public boolean isPairable() {
        return false;
    }
}
