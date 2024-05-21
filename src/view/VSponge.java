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
import model.items.IVMSponge;
import model.items.Sponge;

public class VSponge implements IVItems {
    IVMSponge modelSponge;

    public VSponge(Sponge s) {
        modelSponge = s;
    }

    @Override
    public void draw(IVStudent curPlayer, JButton btn, IController c) {
        System.out.println("VSPONGE DRAWG");

        btn.setIcon(new ImageIcon("textures" + File.separator + "SpongeRoom.png"));
        btn.addActionListener(e -> {
            c.getCommands().pickUpItem(modelSponge);
        });
    }

    @Override
    public void draw(IVStudent curPlayer, JLabel label, IController c) {
        System.out.println("VSPONGE DRAW");

        label.setIcon(new ImageIcon("textures" + File.separator + "Sponge.png"));
        // btn.addActionListener(e -> { c.getCommands().useItem((IItem)modelSponge); });
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
                    JPopupMenu jp = new JPopupMenu("Choose action");
                    jp.add(new JMenuItem("Use item")).addActionListener(e -> {
                        c.getCommands().useItem(modelSponge);
                    });
                    jp.add(new JMenuItem("Drop item")).addActionListener(e -> {
                        c.getCommands().dropItem(modelSponge);
                    });

                    jp.show(label, 100, 100);
                    jp.setLocation(mouseEvent.getXOnScreen(), mouseEvent.getYOnScreen());
                }
                if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
                    JPopupMenu jp = new JPopupMenu("Stats");
                    jp.add(new JMenuItem("Functional: "+modelSponge.isFunctional()));
                    jp.add(new JMenuItem("Time: "+modelSponge.getTime()));

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
