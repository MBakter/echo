package view;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import controller.IController;
import model.items.IItem;
import model.items.IVMTVSZ;
import model.items.TVSZ;

public class VTVSZ implements IVItems{
    IVMTVSZ modelTVSZ;
    public VTVSZ(TVSZ t){
        modelTVSZ = t;
    }

    @Override
    public void draw(IVStudent curPlayer, JButton btn, IController c) {
        System.out.println("VTVSZ DRAW");

        btn.setIcon(new ImageIcon("textures" + File.separator + "TVSZ.png"));
        btn.addActionListener(e -> { c.getCommands().useItem((IItem)modelTVSZ); });
    }

    @Override
    public void drawOnGround(IVStudent curPlayer, JButton btn, IController c) {
        System.out.println("VTVSZ DRAWG");

        btn.setIcon(new ImageIcon("textures" + File.separator + "TVSZ.png"));
        btn.addActionListener(e -> { c.getCommands().pickUpItem((IItem)modelTVSZ); });
    }
  
}
