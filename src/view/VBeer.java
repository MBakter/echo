package view;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import controller.IController;
import model.items.Beer;
import model.items.IVMBeer;

public class VBeer implements IVItems{
    IVMBeer modelBeer;
    public VBeer(Beer b){
        modelBeer = b;
    }

    @Override
    public void draw(IVStudent curPlayer, JButton btn, IController c) {
        System.out.println("VBEER DRAWG");

        btn.setIcon(new ImageIcon("textures" + File.separator + "BeerRoom.png"));
        btn.addActionListener(e -> { c.getCommands().pickUpItem(modelBeer); });
    }

    @Override
    public void draw(IVStudent curPlayer, JLabel label, IController c) {
        System.out.println("VBEER DRAW");

        label.setIcon(new ImageIcon("textures" + File.separator + "Beer.png"));
        //btn.addActionListener(e -> { c.getCommands().useItem((IItem)modelBeer); });

        JPopupMenu jp = new JPopupMenu("Choose action");
        jp.add(new JMenuItem("Use item")).addActionListener(e -> {c.getCommands().useItem(modelBeer);});
    }

    @Override
    public boolean isPairable() {
        return false;
    }
}
