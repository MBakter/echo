package view;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import java.awt.event.*;

import controller.IController;
import model.items.IVMMask;
import model.items.Mask;

public class VMask implements IVItems {
    IVMMask modelMask;

    public VMask(Mask m) {
        modelMask = m;
    }

    @Override
    public void draw(IVStudent curPlayer, JButton btn, IController c) {
        System.out.println("VMASK DRAWG");

        btn.setIcon(new ImageIcon("textures" + File.separator + "MaskRoom.png"));
        btn.addActionListener(e -> {
            c.getCommands().pickUpItem(modelMask);
        });
    }

    @Override
    public void draw(IVStudent curPlayer, JLabel label, IController c) {
        System.out.println("VMASK DRAW");

        label.setIcon(new ImageIcon("textures" + File.separator + "Mask.png"));
        // btn.addActionListener(e -> { c.getCommands().useItem((IItem)modelMask); })

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(mouseEvent.getButton() == MouseEvent.BUTTON1){
                    JPopupMenu jp = new JPopupMenu("Choose action");
                    jp.add(new JMenuItem("Use item")).addActionListener(e -> {
                        c.getCommands().useItem(modelMask);
                    });
                    jp.add(new JMenuItem("Drop item")).addActionListener(e -> {
                        c.getCommands().dropItem(modelMask);
                    });
                    jp.show(label, 100, 100);
                    jp.setLocation(mouseEvent.getXOnScreen(), mouseEvent.getYOnScreen());
                }
                if(mouseEvent.getButton() == MouseEvent.BUTTON3){
                    JPopupMenu jp = new JPopupMenu("Choose action");
                    jp.add(new JLabel("Functional: " + modelMask.isFunctional()));
                    if(modelMask.getTime() > -1)
                        jp.add(new JLabel("Time: " + modelMask.getTime()));
                    jp.add(new JLabel("Fake: " + modelMask.isFake()));
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
