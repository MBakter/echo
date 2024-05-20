package view;

import java.awt.*;
import java.awt.Insets;

import javax.swing.*;

import controller.IController;
import model.player.IVMStudent;
import model.player.Student;

public class VStudent implements IVStudent{

    IVMStudent modelStudent;

    public VStudent(Student s){
        modelStudent = s;
    }

    @Override
    public IVMStudent getModelStudent() {
        return modelStudent;
    }

    @Override
    public JPanel draw(IVStudent curPlayer, JButton btn, IController c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }


}
