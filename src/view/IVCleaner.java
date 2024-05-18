package view;

import java.awt.GridBagConstraints;

import javax.swing.JPanel;

import model.player.IVMCleaner;

public interface IVCleaner {
    IVMCleaner getModelCleaner();
    JPanel draw(IVStudent curPlayer, GridBagConstraints c);

}
