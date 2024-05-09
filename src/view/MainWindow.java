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
    private JPanel itemPanel;

    private List<IItem> itemList = new ArrayList<IItem>();
    private JLabel[] itemLabels;

    private void createGridBag() {

        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        JPanel roomPanel = new JPanel();
        roomPanel.setLayout(new GridBagLayout());
        roomPanel.setOpaque(false);
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 85, 0, 0);
        c.ipady = 60;

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 4; i++) {
                JButton b = new JButton("room");
                GridBagConstraints c2 = new GridBagConstraints();
                b.setPreferredSize(new Dimension(75, 150));
                c2.gridx = i;
                c2.gridy = j;
                roomPanel.add(b, c2);
            }
        }
        mainPanel.add(roomPanel, c);

        JPanel teacherPanel = new JPanel();
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 1;
        c.gridy = 1;
        c.weighty = 2;
        c.ipadx = 500;
        c.ipady = 30;

        teacherPanel.add(new JLabel("teach"));
        mainPanel.add(teacherPanel, c);

        JPanel cleanerPanel = new JPanel();
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 1;
        c.ipadx = 300;
        c.ipady = 0;

        cleanerPanel.add(new JLabel("clean"));
        mainPanel.add(cleanerPanel, c);

        JPanel roomItemPanel = new JPanel();
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 1;
        c.ipadx = 40;
        c.ipady = 60;

        roomItemPanel.add(new JLabel("roomItem"));
        mainPanel.add(roomItemPanel, c);

        JPanel studentPanel = new JPanel();
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 2;
        c.gridy = 3;
        c.weighty = 1;
        c.ipadx = 0;
        c.ipady = 30;

        studentPanel.add(new JLabel("study"));
        mainPanel.add(studentPanel, c);

        JPanel itemPanel = new JPanel();
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 1;
        c.gridy = 4;
        c.weighty = 1;
        c.ipadx = 0;
        c.ipady = 30;

        itemPanel.add(new JLabel("use"));
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
        //setResizable(false);
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
        setPreferredSize(new Dimension(1920, 1080));

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
