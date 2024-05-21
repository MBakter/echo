package view;

import java.io.File;

import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import controller.IController;
import model.items.Cheese;
import model.items.IVMCheese;

public class VCheese implements IVItems{
    IVMCheese modelCheese;
    public VCheese(Cheese c){
        modelCheese = c;
    }

    @Override
    public void draw(IVStudent curPlayer, JButton btn, IController c) {
        System.out.println("VCHEESE DRAWG");

        btn.setIcon(new ImageIcon("textures" + File.separator + "CheeseRoom.png"));
        btn.addActionListener(e -> { c.getCommands().pickUpItem(modelCheese); });
    }

    @Override
    public void draw(IVStudent curPlayer, JLabel label, IController c) {
        System.out.println("VCHEESE DRAW");

        label.setIcon(new ImageIcon("textures" + File.separator + "Cheese.png"));
        //btn.addActionListener(e -> { c.getCommands().useItem((IItem)modelCheese); });

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent){
                if(mouseEvent.getButton() == MouseEvent.BUTTON1){
                    JPopupMenu jp = new JPopupMenu("Choose action");
                    jp.add(new JMenuItem("Use item")).addActionListener(e -> {c.getCommands().useItem(modelCheese);});
                    jp.add(new JMenuItem("Drop item")).addActionListener(e -> {
                        c.getCommands().dropItem(modelCheese);
                    });
                    jp.show(label, 100, 100);
                    jp.setLocation(mouseEvent.getXOnScreen(), mouseEvent.getYOnScreen());
                }
                if(mouseEvent.getButton() == MouseEvent.BUTTON3){
                    JPopupMenu jp = new JPopupMenu("Stats");
                    jp.add(new JLabel("Used: "+ modelCheese.isUsed()));
                    jp.add(new JLabel("Time: "+ modelCheese.getTime()));
                
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
