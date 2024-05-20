package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;

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
        System.out.println("VSPONGE DRAW");

        btn.setIcon(new ImageIcon("textures" + File.separator + "Sponge.png"));
        btn.addActionListener(e -> { c.getCommands().useItem((IItem)modelSponge); });
    }

}
