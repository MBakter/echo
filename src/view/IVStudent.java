package view;

import javax.swing.JButton;
import javax.swing.JLabel;

import controller.IController;
import model.player.IVMStudent;

public interface IVStudent {
    public IVMStudent getModelStudent();
    public void draw(IVStudent curPlayer, JLabel label, IController c);
}
