package view;

import java.io.File;

import javax.swing.*;

import controller.IController;
import model.player.EPlayerState;
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
    public void draw(IVStudent curPlayer, JLabel label, IController c) {
        System.out.println("VSTUDENT DRAW");
        
        if(modelStudent.equals(curPlayer.getModelStudent()))
            return;

        if(modelStudent.getPlayerState() == EPlayerState.DEAD)
            label.setIcon(new ImageIcon("textures" + File.separator + "StudentDead.png"));
        else
            label.setIcon(new ImageIcon("textures" + File.separator + "Student.png"));
    }


}
