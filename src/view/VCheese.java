package view;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;

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
        System.out.println("VCHEESE DRAW");

        btn.setIcon(new ImageIcon("textures" + File.separator + "Sponge.png"));
        btn.addActionListener(e -> { c.getCommands().useItem((IItem)modelCheese); });
    }

}
