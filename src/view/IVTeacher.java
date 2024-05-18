package view;

import java.awt.GridBagConstraints;

import javax.swing.JPanel;

import model.player.IVMTeacher;

public interface IVTeacher {
    IVMTeacher getModelTeacher();
    JPanel draw(IVStudent curPlayer, GridBagConstraints c);
}
