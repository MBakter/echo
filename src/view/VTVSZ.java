package view;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

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
        System.out.println("VTVSZ DRAWG");

        btn.setIcon(new ImageIcon("textures" + File.separator + "TVSZRoom.png"));
        btn.addActionListener(e -> { c.getCommands().pickUpItem(modelTVSZ); });
    }

    @Override
    public void draw(IVStudent curPlayer, JLabel label, IController c) {
        System.out.println("VTVSZ DRAW");

        label.setIcon(new ImageIcon("textures" + File.separator + "TVSZ.png"));
        //btn.addActionListener(e -> { c.getCommands().useItem((IItem)modelTVSZ); });

        JPopupMenu jp = new JPopupMenu("Choose action");
        jp.add(new JMenuItem("Use item")).addActionListener(e -> {c.getCommands().useItem(modelTVSZ);});
    }

    @Override
    public boolean isPairable() {
        return false;
    }
  
}
