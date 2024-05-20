package view;

import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.IController;
import model.player.IVMCleaner;

public interface IVCleaner {
    IVMCleaner getModelCleaner();
    void draw(IVStudent curPlayer, JButton btn, IController c);

}
