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

    private JPanel mainPanel;
    private JButton addItemButton;
    private JPanel itemPanel;

    private List<IItem> itemList = new ArrayList<IItem>();
    private JLabel[] itemLabels;

    private void createGridBag() {
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JButton button;
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        button = new JButton("Button 1");
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(button, c);

        button = new JButton("Button 2");
        c.gridx = 1;
        c.gridy = 2;
        mainPanel.add(button, c);

        button = new JButton("Button 3");
        c.gridx = 2;
        c.gridy = 0;
        mainPanel.add(button, c);

        button = new JButton("Long-Named Button 4");
        c.ipady = 40;      //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        mainPanel.add(button, c);

        button = new JButton("5");
        c.ipady = 0;       //reset to default
        c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(10,0,0,100);  //top padding
        c.gridx = 1;       //aligned with button 2
        c.gridwidth = 2;   //2 columns wide
        c.gridy = 2;       //third row
        mainPanel.add(button, c);
        
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
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
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
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
            }
            
        });
    }

    public MainWindow(String title) {
        super(title);

        // Set default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setResizable(false);
        AddPopupMenu();

        mainPanel = new JPanel();

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
        MainWindow window = new MainWindow("`(*>﹏<*)′");
        window.setVisible(true);
    }
}
