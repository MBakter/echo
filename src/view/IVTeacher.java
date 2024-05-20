package view;

import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.IController;
import model.player.IVMTeacher;

public interface IVTeacher {
    IVMTeacher getModelTeacher();
    void draw(IVStudent curPlayer, JButton btn, IController c);
}
