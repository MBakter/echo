package view;

import java.awt.GridBagConstraints;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

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

        btn.setIcon(new ImageIcon("textures" + File.separator + "Sponge.png"));
        btn.addActionListener(e -> { c.getCommands().useItem((IItem)modelTVSZ); });
    }
  
}
