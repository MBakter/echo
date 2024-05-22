package view;

import java.io.File;

import javax.swing.*;

import controller.IController;
import model.player.IVMTeacher;
import model.player.Teacher;

public class VTeacher implements IVTeacher {

    IVMTeacher modelTeacher;

    public VTeacher(Teacher t) {
        modelTeacher = t;
    }

    @Override
    public IVMTeacher getModelTeacher() {
        return modelTeacher;
    }

    @Override
    public void draw(IVStudent curPlayer, JLabel label, IController c) {
        System.out.println("VTEACHER DRAW");

        label.setIcon(new ImageIcon("textures" + File.separator + "Teacher.png"));
    }

}
