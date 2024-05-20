package view;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import controller.IController;
import model.items.IVMMask;
import model.items.Mask;

public class VMask implements IVItems{
    IVMMask modelMask;
    public VMask(Mask m){
        modelMask = m;
    }

    @Override
    public void draw(IVStudent curPlayer, JButton btn, IController c) {
        System.out.println("VMASK DRAWG");

        btn.setIcon(new ImageIcon("textures" + File.separator + "Mask.png"));
        btn.addActionListener(e -> { c.getCommands().pickUpItem(modelMask); });
    }

    @Override
    public void draw(IVStudent curPlayer, JLabel label, IController c) {
        System.out.println("VMASK DRAW");

        label.setIcon(new ImageIcon("textures" + File.separator + "Mask.png"));
        //btn.addActionListener(e -> { c.getCommands().useItem((IItem)modelMask); })

        JPopupMenu jp = new JPopupMenu("Choose action");
        jp.add(new JMenuItem("Use item")).addActionListener(e -> {c.getCommands().useItem(modelMask);});
    }

    @Override
    public boolean isPairable() {
        return false;
    }
}
