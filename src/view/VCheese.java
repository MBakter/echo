package view;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import controller.IController;
import model.items.Cheese;
import model.items.IItem;
import model.items.IVMCheese;

public class VCheese implements IVItems{
    IVMCheese modelCheese;
    public VCheese(Cheese c){
        modelCheese = c;
    }

    @Override
    public void draw(IVStudent curPlayer, JButton btn, IController c) {
        System.out.println("VCHEESE DRAWG");

        btn.setIcon(new ImageIcon("textures" + File.separator + "CheeseRoom.png"));
        btn.addActionListener(e -> { c.getCommands().pickUpItem(modelCheese); });
    }

    @Override
    public void draw(IVStudent curPlayer, JLabel label, IController c) {
        System.out.println("VCHEESE DRAW");

        label.setIcon(new ImageIcon("textures" + File.separator + "Cheese.png"));
        //btn.addActionListener(e -> { c.getCommands().useItem((IItem)modelCheese); });

        JPopupMenu jp = new JPopupMenu("Choose action");
        jp.add(new JMenuItem("Use item")).addActionListener(e -> {c.getCommands().useItem(modelCheese);});
    }

    @Override
    public boolean isPairable() {
        return false;
    }

}
