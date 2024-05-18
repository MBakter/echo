package view;

import java.awt.*;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.JPanel;

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
    public JPanel draw(IVStudent currentPlayer, GridBagConstraints c) {
                JPanel studentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        studentPanel.setOpaque(false);
        c.insets = new Insets(0/* 95 */, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 4;
        c.gridheight = 1;
        c.weighty = 3;
        c.ipadx = 0;
        c.ipady = 15;

        for (int i = 0; i < 10; i++) {
            JButton student = new JButton();
            student.setPreferredSize(new Dimension(150, 170));
            studentPanel.add(student);
        }

        return studentPanel;
    }
    
}
