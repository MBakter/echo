package view;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import java.awt.event.*;

import controller.IController;
import model.items.IVMLogarlec;
import model.items.Logarlec;

public class VLogarlec implements IVItems{
    IVMLogarlec modelLogarlec;
    public VLogarlec(Logarlec l){
        modelLogarlec = l;
    }

    @Override
    public void draw(IVStudent curPlayer, JButton btn, IController c) {
        System.out.println("VLOGARLEC DRAWG");

        btn.setIcon(new ImageIcon("textures" + File.separator + "LogarlecRoom.png"));
        btn.addActionListener(e -> { c.getCommands().pickUpItem(modelLogarlec); });
    }

    @Override
    public void draw(IVStudent curPlayer, JLabel label, IController c) {
        System.out.println("VLOGARLEC DRAW");

        label.setIcon(new ImageIcon("textures" + File.separator + "Logarlec.png"));
        
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(mouseEvent.getButton() == MouseEvent.BUTTON1){
                    JPopupMenu jp = new JPopupMenu("Choose action");
                    jp.add(new JMenuItem("Drop item")).addActionListener(e -> {
                        c.getCommands().dropItem(modelLogarlec);
                    });
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
