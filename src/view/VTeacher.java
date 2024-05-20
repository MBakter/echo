package view;

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
    public void draw(IVStudent curPlayer, JButton btn, IController c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }

 
}
