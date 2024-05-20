package view;

import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.IController;

public interface IVItems {
    void draw(IVStudent curPlayer, JButton btn, IController c);
}
