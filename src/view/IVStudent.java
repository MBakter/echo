package view;

import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.IController;
import model.player.IVMStudent;

public interface IVStudent {
    public IVMStudent getModelStudent();
    public void draw(IVStudent curPlayer, JButton btn, IController c);
}
