package view;

import java.awt.GridBagConstraints;

import javax.swing.JPanel;

import model.player.IVMStudent;

public interface IVStudent {
    public IVMStudent getModelStudent();
    public JPanel draw(IVStudent currentPlayer, GridBagConstraints c);
}
