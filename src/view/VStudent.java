package view;

import java.awt.*;
import java.awt.Insets;
import java.io.File;

import javax.swing.*;

import controller.IController;
import model.items.IItem;
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
    public void draw(IVStudent curPlayer, JButton btn, IController c) {
        System.out.println("VSTUDENT DRAW");

        btn.setIcon(new ImageIcon("textures" + File.separator + "Student.png"));
    }


}
