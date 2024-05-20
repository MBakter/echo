package view;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import controller.IController;
import model.items.Beer;
import model.items.IItem;
import model.items.IVMBeer;

public class VBeer implements IVItems{
    IVMBeer modelBeer;
    public VBeer(Beer b){
        modelBeer = b;
    }

    @Override
    public void draw(IVStudent curPlayer, JButton btn, IController c) {
        System.out.println("VBEER DRAW");

        btn.setIcon(new ImageIcon("textures" + File.separator + "Beer.png"));
        btn.addActionListener(e -> { c.getCommands().useItem((IItem)modelBeer); });
    }

    @Override
    public void drawOnGround(IVStudent curPlayer, JButton btn, IController c) {
        System.out.println("VBEER DRAWG");

        btn.setIcon(new ImageIcon("textures" + File.separator + "Beer.png"));
        btn.addActionListener(e -> { c.getCommands().pickUpItem((IItem)modelBeer); });
    }
}
