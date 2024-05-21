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
import model.items.IVMPurifier;
import model.items.Purifier;

public class VPurifier implements IVItems{
    IVMPurifier modelPurifier;
    public VPurifier(Purifier p){
        modelPurifier = p;
    }

    @Override
    public void draw(IVStudent curPlayer, JButton btn, IController c) {
        System.out.println("VPURIFIER DRAWG");

        btn.setIcon(new ImageIcon("textures" + File.separator + "PurifierRoom.png"));
        btn.addActionListener(e -> { c.getCommands().pickUpItem(modelPurifier); });
    }

    @Override
    public void draw(IVStudent curPlayer, JLabel label, IController c) {
        System.out.println("VPURIFIER DRAW");

        label.setIcon(new ImageIcon("textures" + File.separator + "Purifier.png"));
        //btn.addActionListener(e -> { c.getCommands().useItem((IItem)modelPurifier); });

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(mouseEvent.getButton() == MouseEvent.BUTTON1){
                    JPopupMenu jp = new JPopupMenu("Choose action");
                    jp.add(new JMenuItem("Use item")).addActionListener(e -> {
                        c.getCommands().useItem(modelPurifier);
                    });
                    jp.add(new JMenuItem("Drop item")).addActionListener(e -> {
                        c.getCommands().dropItem(modelPurifier);
                    });
                    jp.show(label, 100, 100);
                    jp.setLocation(mouseEvent.getXOnScreen(), mouseEvent.getYOnScreen());
                }
                if(mouseEvent.getButton() == MouseEvent.BUTTON3){
                    JPopupMenu jp = new JPopupMenu("Choose action");
                    jp.add(new JLabel("Used: +" + modelPurifier.isUsed()));
    
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
