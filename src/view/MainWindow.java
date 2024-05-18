package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollPaneUI.ViewportChangeHandler;

import controller.ICommands;
import controller.IController;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;

public class MainWindow extends JFrame implements IMainWindow {

    final int wHeight = 1130, wWidth = 1920;

    IController controller;
    ICommands commands;

    ArrayList<IVItems> VItemsOfCP;
    VStudent currentVPlayer;
    VRoom currentVRoom;
    ArrayList<IVItems> itemsInRoom;
    ArrayList<IVRoom> neighbouringRooms;
    ArrayList<IVCleaner> cleanersInRoom;
    ArrayList<IVTeacher> teachersInRoom;
    ArrayList<IVStudent> studentsInRoom;

    private BackgroundPanel mainPanel;
    private BackgroundPanel optionPanel;
    private BackgroundPanel gamePanel;
    private JPanel roomPanel;
    private JPanel teacherPanel;
    private JPanel cleanerPanel;
    private JPanel roomItemPanel;
    private JPanel studentPanel;
    private JPanel itemPanel;

    private String mapName;

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
            // teacher.setContentAreaFilled(false);
            // teacher.setIcon(new ImageIcon("textures" + File.separator + "Teacher1.png"));
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

    // TODO: ActionListenerek a commandokhoz
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

        gamePanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        gamePanel.add(currentVRoom.draw(currentVPlayer, c));

        gamePanel.add(currentVPlayer.draw(currentVPlayer, c));

        for (IVItems ivItems : VItemsOfCP) {
            gamePanel.add(ivItems.draw(currentVPlayer, c));
        }

        for (IVTeacher item : currentVRoom.modelRoom.getTeacherList()) {
            gamePanel.add(item.draw(currentVPlayer, c));
        }
/* 
        roomPanel = createRoomGrid(c);
        gamePanel.add(roomPanel, c);

        teacherPanel = createTeacherPanel(c);
        gamePanel.add(teacherPanel, c);

        cleanerPanel = createCleanerPanel(c);
        gamePanel.add(cleanerPanel, c);

        roomItemPanel = createRoomItemPanel(c);
        gamePanel.add(roomItemPanel, c);

        studentPanel = createStudentPanel(c);
        gamePanel.add(studentPanel, c);

        itemPanel = createItemPanel(c);
        gamePanel.add(itemPanel, c); */

    }

    private void AddPopupMenu() {
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("ITT VAGYOK!!!!! : " + e.getKeyCode());
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

                    // The menu Dialog
                    JDialog menuDialog = new JDialog();
                    menuDialog.setLayout(new FlowLayout());
                    menuDialog.add(new JLabel("Game paused"));

                    // Resume Button
                    JButton resumeButton = new JButton("Resume");
                    resumeButton.addActionListener(al -> {
                        menuDialog.setVisible(false);
                    });
                    menuDialog.add(resumeButton);

                    // Exit Button
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

    public MainWindow(IController controller, String title) {
        super(title);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        JFrame.setDefaultLookAndFeelDecorated(true);

        this.controller = controller;
        mapName = controller.getMapName();
    }

    private void startGame() {

        AddPopupMenu();

        gamePanel = new BackgroundPanel("textures" + File.separator + "BackgroundEdited.png");

        createGridBag();

        getContentPane().add(gamePanel, BorderLayout.CENTER);

        setPreferredSize(new Dimension(wWidth, wHeight));

        pack();

    }

    private void drawMenu() {

        mainPanel = new BackgroundPanel("textures" + File.separator + "BackgroundBlurred.png");
        mainPanel.setLayout(new GridBagLayout());

        JPanel menuPanel = new JPanel(new GridLayout(3, 1, 0, 50));
        menuPanel.setPreferredSize(new Dimension(500, 500));
        menuPanel.setOpaque(false);

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e -> {
            getContentPane().remove(mainPanel);
            controller.startGame();
            //startGame();
        });
        startButton.setContentAreaFilled(false);
        startButton.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
        startButton.setFont(new Font("Courier New", Font.PLAIN, 50));
        menuPanel.add(startButton);

        JButton optionsButton = new JButton("Options");
        optionsButton.addActionListener(e -> {
            getContentPane().remove(mainPanel);
            drawOptions();
        });
        optionsButton.setContentAreaFilled(false);
        optionsButton.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
        optionsButton.setFont(new Font("Courier New", Font.PLAIN, 50));
        menuPanel.add(optionsButton);

        JButton exitButton = new JButton("Exit to desktop");
        exitButton.addActionListener(e -> {
            exitGame();
        });
        exitButton.setContentAreaFilled(false);
        exitButton.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
        exitButton.setFont(new Font("Courier New", Font.PLAIN, 50));
        menuPanel.add(exitButton);

        mainPanel.add(menuPanel);
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        setPreferredSize(new Dimension(wWidth, wHeight));
        pack();

    }

    private JLabel mapNameL;

    private void drawOptions() {

        optionPanel = new BackgroundPanel("textures" + File.separator + "BackgroundBlurred.png");
        optionPanel.setLayout(new GridBagLayout());
        JPanel optionsPanel = new JPanel(new GridBagLayout());
        optionsPanel.setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();

        JLabel studentText = new JLabel("Enter the number of students");
        studentText.setPreferredSize(new Dimension(550, 60));
        studentText.setFont(new Font("Courier New", Font.PLAIN, 25));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        optionsPanel.add(studentText, c);

        JLabel teacherText = new JLabel("Enter the number of teachers");
        teacherText.setPreferredSize(new Dimension(550, 60));
        teacherText.setFont(new Font("Courier New", Font.PLAIN, 25));
        c.gridx = 0;
        c.gridy = 1;
        optionsPanel.add(teacherText, c);

        JLabel cleanerText = new JLabel("Enter the number of cleaners");
        cleanerText.setPreferredSize(new Dimension(550, 60));
        cleanerText.setFont(new Font("Courier New", Font.PLAIN, 25));
        c.gridx = 0;
        c.gridy = 2;
        optionsPanel.add(cleanerText, c);

        JButton mapButton = new JButton("Select labyrinth");
        mapButton.setPreferredSize(new Dimension(550, 60));
        mapButton.setContentAreaFilled(false);
        mapButton.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
        mapButton.setFont(new Font("Courier New", Font.PLAIN, 25));
        mapButton.addActionListener(e -> {
            mapSelect();
        });
        c.gridx = 0;
        c.gridy = 3;
        optionsPanel.add(mapButton, c);

        SpinnerModel studentSelect = new SpinnerNumberModel(controller.getStudentNum(), 1, 10, 1);
        c.gridx = 2;
        c.gridy = 0;
        c.ipadx = 10;
        c.ipady = 10;
        c.insets = new Insets(0, 30, 0, 0);
        optionsPanel.add(new JSpinner(studentSelect), c);

        SpinnerModel teacherSelect = new SpinnerNumberModel(controller.getTeacherNum(), 1, 10, 1);
        c.gridx = 2;
        c.gridy = 1;
        c.ipadx = 10;
        c.ipady = 10;
        optionsPanel.add(new JSpinner(teacherSelect), c);

        SpinnerModel cleanerSelect = new SpinnerNumberModel(controller.getCleanerNum(), 0, 10, 1);
        c.gridx = 2;
        c.gridy = 2;
        c.ipadx = 10;
        c.ipady = 10;
        optionsPanel.add(new JSpinner(cleanerSelect), c);

        mapNameL = new JLabel(controller.getMapName().replace(".txt", ""));
        mapNameL.setFont(new Font("Courier New", Font.PLAIN, 25));
        c.gridx = 2;
        c.gridy = 3;
        c.ipadx = 10;
        c.ipady = 10;
        optionsPanel.add(mapNameL, c);

        JButton save = new JButton("Save options");
        save.setContentAreaFilled(false);
        save.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
        save.setFont(new Font("Courier New", Font.PLAIN, 25));
        save.addActionListener(e -> {
            saveOptions(studentSelect.getValue(), teacherSelect.getValue(), cleanerSelect.getValue(), mapName);
            getContentPane().remove(optionPanel);
            drawMenu();
        });
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.ipadx = 40;
        c.ipady = 20;
        c.insets = new Insets(40, 0, 0, 0);
        optionsPanel.add(save, c);

        JButton back = new JButton("Return");
        back.setContentAreaFilled(false);
        back.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
        back.setFont(new Font("Courier New", Font.PLAIN, 25));
        back.addActionListener(e -> {
            getContentPane().remove(optionPanel);
            drawMenu();
        });
        c.gridx = 1;
        c.gridy = 4;
        c.ipadx = 60;
        c.ipady = 10;
        c.insets = new Insets(40, 70, 0, 0);
        optionsPanel.add(back, c);

        optionPanel.add(optionsPanel);

        getContentPane().add(optionPanel, BorderLayout.CENTER);

        setPreferredSize(new Dimension(1920, 1130));

        pack();
    }

    private void mapSelect() {
        File mapLocation = new File(controller.getMapFolderLocation());
        if (!mapLocation.exists())
            mapLocation.mkdir();
        JFileChooser fc = new JFileChooser(mapLocation);

        if (fc.showDialog(this, "Select map") == JFileChooser.APPROVE_OPTION) {
            mapName = fc.getSelectedFile().getName();
            mapNameL.setText(mapName.replace(".txt", ""));
        }

    }

    private void saveOptions(Object sVal, Object tVal, Object cVal, Object mVal) {
        controller.setParameters((int) sVal, (int) tVal, (int) cVal, (String) mVal);
    }

    private void exitGame() {
        System.exit(0);
    }

    @Override
    public void RefreshView() {        
        VItemsOfCP = controller.getVItemsOfCP();
        currentVPlayer = (VStudent) controller.getCP();
        currentVRoom = (VRoom) currentVPlayer.getModelStudent().getVRoom();
        itemsInRoom = currentVRoom.modelRoom.getRoomItems();
        neighbouringRooms = currentVRoom.modelRoom.getNeighBourList();
        cleanersInRoom = currentVRoom.modelRoom.getCleanerList();
        teachersInRoom = currentVRoom.modelRoom.getTeacherList();
        studentsInRoom = currentVRoom.modelRoom.getStudentList();

        startGame();

        mainPanel.revalidate();
    }

    @Override
    public void InitWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        drawMenu();
    }

    @Override
    public void showError(String title) {
        JOptionPane.showMessageDialog(this, title);
    }
}
