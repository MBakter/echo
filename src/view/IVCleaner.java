package view;

import javax.swing.JLabel;

import controller.IController;
import model.player.IVMCleaner;

public interface IVCleaner {
    IVMCleaner getModelCleaner();
    void draw(IVStudent curPlayer, JLabel label, IController c);

}
