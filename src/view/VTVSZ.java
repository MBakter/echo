package view;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import java.awt.event.*;

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

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                JPopupMenu jp = new JPopupMenu("Choose action");
                jp.add(new JMenuItem("Use item")).addActionListener(e -> {
                    c.getCommands().useItem(modelTVSZ);
                });

                jp.show(label, 100, 100);
                jp.setLocation(mouseEvent.getXOnScreen(), mouseEvent.getYOnScreen());
            }
        });

    }

    @Override
    public boolean isPairable() {
        return false;
    }
  
}
