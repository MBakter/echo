package view;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import controller.IController;
import model.items.IItem;
import model.items.IVMSponge;
import model.items.Sponge;

public class VSponge implements IVItems{
    IVMSponge modelSponge;
    public VSponge(Sponge s){
        modelSponge = s;
    }

    @Override
    public void draw(IVStudent curPlayer, JButton btn, IController c) {
        System.out.println("VSPONGE DRAWG");

        btn.setIcon(new ImageIcon("textures" + File.separator + "Sponge.png"));
        btn.addActionListener(e -> { c.getCommands().pickUpItem(modelSponge); });
    }

    @Override
    public void draw(IVStudent curPlayer, JLabel label, IController c) {
        System.out.println("VSPONGE DRAW");

        label.setIcon(new ImageIcon("textures" + File.separator + "Sponge.png"));
        //btn.addActionListener(e -> { c.getCommands().useItem((IItem)modelSponge); });

        JPopupMenu jp = new JPopupMenu("Choose action");
        jp.add(new JMenuItem("Use item")).addActionListener(e -> {c.getCommands().useItem(modelSponge);});
    }

    @Override
    public boolean isPairable() {
        return false;
    }


}
