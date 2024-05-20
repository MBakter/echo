package view;

import javax.swing.JButton;

import controller.IController;
import model.player.IVMCleaner;

public interface IVCleaner {
    IVMCleaner getModelCleaner();
    void draw(IVStudent curPlayer, JButton btn, IController c);

}
