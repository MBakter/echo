package view;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import java.awt.event.*;

import controller.IController;
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
        
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(mouseEvent.getButton() == MouseEvent.BUTTON1){
                    JPopupMenu jp = new JPopupMenu("Choose action");
                    jp.add(new JMenuItem("Drop item")).addActionListener(e -> {
                        c.getCommands().dropItem(modelTVSZ);
                    });
                    jp.show(label, 100, 100);
                    jp.setLocation(mouseEvent.getXOnScreen(), mouseEvent.getYOnScreen());
                }
                if(mouseEvent.getButton() == (MouseEvent.BUTTON3)){
                    JPopupMenu jp = new JPopupMenu("Stats");
                    jp.add(new JLabel("Health left: " + modelTVSZ.getHitpoints()));
                    jp.add(new JLabel("Fake: " + modelTVSZ.isFake()));
                    jp.show(label, 100, 100);
                    jp.setLocation(mouseEvent.getXOnScreen(), mouseEvent.getYOnScreen());
                }

            }
        });

    }

    @Override
    public boolean isPairable() {
        return false;
    }
  
}
