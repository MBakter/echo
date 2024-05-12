package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import model.items.*;

public class MainWindow extends JFrame {

    private BackgoundPanel mainPanel;
    private JButton addItemButton;
    private JPanel roomPanel;
    private JPanel teacherPanel;
    private JPanel cleanerPanel;
    private JPanel roomItemPanel;
    private JPanel studentPanel;
    private JPanel itemPanel;

    private List<IItem> itemList = new ArrayList<IItem>();
    private JLabel[] itemLabels;

    private JPanel createRoomGrid(GridBagConstraints c) {
        JPanel roomPanel = new JPanel(new GridBagLayout());
        roomPanel.setOpaque(false);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 3;
        c.insets = new Insets(58, 85, 0, 0);
        c.ipady = 0;
        c.weightx = 0;
        c.weighty = 0;

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 4; i++) {
                JButton door = new JButton();
                GridBagConstraints c2 = new GridBagConstraints();
                door.setPreferredSize(new Dimension(75, 150));
                c2.gridx = i;
                c2.gridy = j;
                roomPanel.add(door, c2);
            }
        }
        return roomPanel;
    }

    private JPanel createTeacherPanel(GridBagConstraints c) {
        JPanel teacherPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        c.insets = new Insets(43, 0, 5, 0);
        teacherPanel.setOpaque(false);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.weighty = 1;
        c.ipadx = 00;
        c.ipady = 00;

        for (int i = 0; i < 10; i++) {
            JButton teacher = new JButton();
            //teacher.setContentAreaFilled(false);
            //teacher.setIcon(new ImageIcon("textures" + File.separator + "Teacher1.png"));
            teacher.setPreferredSize(new Dimension(55, 100));
            teacherPanel.add(teacher);
        }

        return teacherPanel;
    }

    private JPanel createCleanerPanel(GridBagConstraints c) {
        JPanel cleanerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        cleanerPanel.setOpaque(false);
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.ipadx = 60;
        c.ipady = 0;
        c.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 0; i < 10; i++) {
            JButton cleaner = new JButton();
            cleaner.setContentAreaFilled(false);
            cleaner.setIcon(new ImageIcon("textures" + File.separator + "Cleaner.png"));
            cleaner.setPreferredSize(new Dimension(65, 100));
            cleanerPanel.add(cleaner);
        }

        return cleanerPanel;
    }

    private JPanel createRoomItemPanel(GridBagConstraints c) {
        JPanel roomItemPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        roomItemPanel.setOpaque(false);
        c.insets = new Insets(80, 0, 0, 0);
        c.gridx = 2;
        c.gridy = 3;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1;
        c.ipadx = 60;
        c.ipady = 40;

        for (int i = 0; i < 10; i++) {
            JButton item = new JButton();
            item.setPreferredSize(new Dimension(70, 100));
            roomItemPanel.add(item);
        }

        return roomItemPanel;
    }

    private JPanel createStudentPanel(GridBagConstraints c) {
        JPanel studentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        studentPanel.setOpaque(false);
        c.insets = new Insets(0/*95*/, 0, 0, 0);
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

    private JPanel createItemPanel(GridBagConstraints c) {
        JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        itemPanel.setOpaque(false);
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 4;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 2;
        c.ipadx = 1;
        c.ipady = 15;
        c.anchor = GridBagConstraints.SOUTH;

        for (int i = 0; i < 10; i++) {
            JButton item = new JButton();
            item.setContentAreaFilled(false);
            switch (i) {
                case 0:
                    item.setIcon(new ImageIcon("textures" + File.separator + "Beer.png"));
                    break;
                case 1:
                    item.setIcon(new ImageIcon("textures" + File.separator + "Sponge.png"));
                    break;
                case 2:
                    item.setIcon(new ImageIcon("textures" + File.separator + "Mask.png"));
                    break;
                case 3:
                    item.setIcon(new ImageIcon("textures" + File.separator + "TVSZ.png"));
                    break;
                case 4:
                    item.setIcon(new ImageIcon("textures" + File.separator + "Purifier.png"));
                    break;
                case 5:
                    item.setIcon(new ImageIcon("textures" + File.separator + "Transistor.png"));
                    break;
                case 6:
                    item.setIcon(new ImageIcon("textures" + File.separator + "Logarlec.png"));
                    break;
                case 7:
                    item.setIcon(new ImageIcon("textures" + File.separator + "Cheese.png"));
                    break;
            
                default:
                    break;
            }
            item.setPreferredSize(new Dimension(120, 150));
            itemPanel.add(item);
        }

        return itemPanel;
    }

    private void createGridBag() {

        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        roomPanel = createRoomGrid(c);
        mainPanel.add(roomPanel, c);

        teacherPanel = createTeacherPanel(c);
        mainPanel.add(teacherPanel, c);

        cleanerPanel = createCleanerPanel(c);
        mainPanel.add(cleanerPanel, c);

        roomItemPanel = createRoomItemPanel(c);
        mainPanel.add(roomItemPanel, c);

        studentPanel = createStudentPanel(c);
        mainPanel.add(studentPanel, c);

        itemPanel = createItemPanel(c);
        mainPanel.add(itemPanel, c);

        /* addItemButton = new JButton();
        addItemButton.setSize(10, 10);
        addItemButton.setText("PickUp Logarlec");
        addItemButton.setOpaque(false);
        addItemButton.setContentAreaFilled(false);
        // addItemButton.setBorderPainted(false); 
        addItemButton.addActionListener(e -> { addItem(new Logarlec()); });
        mainPanel.add(addItemButton); */

    }

    private void AddPopupMenu() {
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("ITT VAGYOK!!!!! : " + e.getKeyCode());
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {

                    //The menu Dialog
                    JDialog menuDialog = new JDialog();
                    menuDialog.setLayout(new FlowLayout());
                    menuDialog.add(new JLabel("Game paused"));

                    //Resume Button
                    JButton resumeButton = new JButton("Resume");
                    resumeButton.addActionListener(al -> { menuDialog.setVisible(false); });
                    menuDialog.add(resumeButton);

                    //Exit Button
                    JButton exitButton = new JButton("Return to Main menu");
                    menuDialog.add(exitButton);
                    menuDialog.pack();
                    menuDialog.setVisible(true);
                }    
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
            
        });
    }

    public MainWindow(String title) {
        super(title);
    
        // Set default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        AddPopupMenu();

        mainPanel = new BackgoundPanel("textures" + File.separator + "Background.png");

        createGridBag();

        getContentPane().add(mainPanel, BorderLayout.CENTER);

        itemPanel = new JPanel();
        itemLabels = new JLabel[10];
        for (int i = 0; i < 10; i++) {
            itemLabels[i] = new JLabel();
            itemLabels[i].setBackground(Color.BLUE);
            itemPanel.add(itemLabels[i]);
        }
        getContentPane().add(itemPanel, BorderLayout.SOUTH);

        // Set window size (optional)
        setPreferredSize(new Dimension(1920, 1130));

        // Pack components to fit preferred size
        pack();
    }

    public void addItem(IItem i) {
        if(itemList.size() >= 10)
            return;
        itemList.add(i);
        itemLabels[itemList.size()-1].setIcon(new ImageIcon("textures" + File.separator + "logarlec.png"));; 
    }

    // This method would be called by the model to update the view (replace with your logic)
    public void updateView(String message) {
        mainPanel.removeAll();
        JLabel label = new JLabel(message);
        mainPanel.add(label);
        mainPanel.revalidate();
    }

    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {}

        JFrame.setDefaultLookAndFeelDecorated(true);
        
        MainWindow window = new MainWindow("`(*>﹏<*)′");
        window.setVisible(true);
    }
}
