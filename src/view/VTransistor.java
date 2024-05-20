package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import java.awt.event.*;

import java.io.File;
import java.util.ArrayList;

import controller.IController;
import model.items.IVMTransistor;
import model.items.Transistor;

public class VTransistor implements IVItems{
    IVMTransistor modelTransistor;
    public VTransistor(Transistor t){
        modelTransistor = t;
    }

    @Override
    public void draw(IVStudent curPlayer, JButton btn, IController c) {
        System.out.println("VTRANSISTOR DRAWG");

        btn.setIcon(new ImageIcon("textures" + File.separator + "TransistorRoom.png"));
        btn.addActionListener(e -> { c.getCommands().pickUpItem(modelTransistor); });
    }
    @Override
    public void draw(IVStudent curPlayer, JLabel label, IController c) {
        System.out.println("VTRANSISTOR DRAW");

        label.setIcon(new ImageIcon("textures" + File.separator + "Transistor.png"));
        //label.addActionListener(e -> { c.getCommands().useItem((IItem)modelTransistor); });
        //label.addActionListener(e -> {/* TODO popup gomb transistorhoz */});

        ArrayList<IVMTransistor> transistors = new ArrayList<>();
        for (IVItems item : curPlayer.getModelStudent().getItemList()) {
            if(item.isPairable() && !item.equals(this)){
                transistors.add((((VTransistor)item).modelTransistor));
            }
        }

        JPopupMenu jp = new JPopupMenu("Choose action");
        JMenu subMenu = new JMenu("Pair with...");
        for(int i = 0; i < transistors.size(); i++){
            final int idx = i;
            subMenu.add(new JMenuItem("Transistor " + i)).addActionListener(e -> { c.getCommands().pairTransistor(modelTransistor, transistors.get(idx));});
        }
        //jp.add(new JMenuItem("Activate")).addActionListener(e -> {c.getCommands().useItem(modelTransistor);});
        //jp.add(subMenu);

        jp.show(label, 100, 100);

                label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                JPopupMenu jp = new JPopupMenu("Choose action");
                jp.add(new JMenuItem("Use item")).addActionListener(e -> {
                    c.getCommands().activateTransistor(modelTransistor);
                });
                jp.add(subMenu);
                jp.show(label, 100, 100);
                jp.setLocation(mouseEvent.getXOnScreen(), mouseEvent.getYOnScreen());
            }
        });
    }

    @Override
    public boolean isPairable() {
        return true;
    }



}
