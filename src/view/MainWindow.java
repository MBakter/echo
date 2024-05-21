package view;

import javax.swing.*;
import javax.swing.border.LineBorder;

import controller.Controller;
import controller.IController;
import model.ERoomEffects;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.ResourceBundle.Control;

public class MainWindow extends JFrame implements IMainWindow {
    
    private final int WIDTH = 1920;
    private final int HEIGHT = 1080;

    //Kontroller************
    IController controller;

    //Panelek****************
    private BackgroundPanel mainPanel;
    private BackgroundPanel optionPanel;
    private BackgroundPanel gamePanel;

    //Játékbeli objektumok helye*******
    private JButton[] doors;
    private JLabel[] teachers;
    private JLabel[] cleaners;
    private JButton[] roomItems;
    private JLabel[] students;
    private JLabel[] items;

    //Játékbeli objektumok listája*****
    ArrayList<IVItems> VItemsOfCP;
    VStudent currentVPlayer;
    VRoom currentVRoom;
    ArrayList<IVItems> itemsInRoom;
    ArrayList<IVRoom> neighbouringRooms;
    ArrayList<IVCleaner> cleanersInRoom;
    ArrayList<IVTeacher> teachersInRoom;
    ArrayList<IVStudent> studentsInRoom;

    private String mapName;

    private JPanel createRoomGrid(GridBagConstraints c) {
        JPanel roomPanel = new JPanel(new GridBagLayout());
        roomPanel.setOpaque(false);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 3;
        c.insets = new Insets(62, 85, 0, 0);
        c.ipady = 0;
        c.weightx = 0;
        c.weighty = 0;
        
        doors = new JButton[12];

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 4; i++) {
                doors[i + j*4] = new JButton();
                //doors[i + j*4].setIcon(new ImageIcon("textures" + File.separator + "Door.png"));
                GridBagConstraints c2 = new GridBagConstraints();
                doors[i + j*4].setPreferredSize(new Dimension(75, 150));
                doors[i + j*4].setContentAreaFilled(false);
                
                c2.gridx = i;
                c2.gridy = j;
                roomPanel.add(doors[i + j*4], c2);
            }
        }
        return roomPanel;
    }

    private JPanel createTeacherPanel(GridBagConstraints c) {
        JPanel teacherPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        c.insets = new Insets(0, 0, 73, 0);
        teacherPanel.setOpaque(false);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weighty = 1;
        c.ipadx = 0;
        c.ipady = 0;

        teachers = new JLabel[10];
        for (int i = 0; i < 10; i++) {
            teachers[i] = new JLabel();
            /* teachers[i].setIcon(new ImageIcon("textures" + File.separator + "Teacher1.png")); */
            teachers[i].setPreferredSize(new Dimension(55, 100));
            teacherPanel.add(teachers[i]);
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

        cleaners = new JLabel[10];
        for (int i = 0; i < 10; i++) {
            cleaners[i] = new JLabel();
            /* cleaners[i].setIcon(new ImageIcon("textures" + File.separator + "Cleaner.png")); */
            cleaners[i].setPreferredSize(new Dimension(65, 100));
            cleanerPanel.add(cleaners[i]);
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
        c.ipady = 1;

        roomItems = new JButton[10];
        for (int i = 0; i < 10; i++) {
            roomItems[i] = new JButton();
            /* roomItems[i].setIcon(new ImageIcon("textures" + File.separator + "TVSZRoom.png")); */
            roomItems[i].setContentAreaFilled(false);
            roomItems[i].setPreferredSize(new Dimension(70, 100));
            roomItemPanel.add(roomItems[i]);
        }

        return roomItemPanel;
    }

    private JPanel createStudentPanel(GridBagConstraints c) {
        JPanel studentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        studentPanel.setOpaque(false);
        c.insets = new Insets(35/*95*/, 0, 2, 0);
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 4;
        c.gridheight = 1;
        c.weighty = 3;
        c.ipadx = 0;
        c.ipady = 15;
        
        students = new JLabel[10];
        for (int i = 0; i < 10; i++) {
            students[i] = new JLabel();
            /* students[i].setIcon(new ImageIcon("textures" + File.separator + "Student.png")); */
            students[i].setPreferredSize(new Dimension(150, 170));
            studentPanel.add(students[i]);
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
        c.ipady = 1;
        c.anchor = GridBagConstraints.SOUTH;

        items = new JLabel[10];
        for (int i = 0; i < 10; i++) {
            items[i] = new JLabel();
            /* items[i].setIcon(new ImageIcon("textures" + File.separator + "Sponge.png"));   */ 
               
            items[i].setPreferredSize(new Dimension(120, 120));
            itemPanel.add(items[i]);
        }

        return itemPanel;
    }

    private JPanel createBoard(GridBagConstraints c) {
        JPanel boardPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        c.insets = new Insets(170, 0, 5, 0);
        boardPanel.setOpaque(false);
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weighty = 3;
        c.ipadx = 0;
        c.ipady = 0;

        
        JButton turnEndButton = new JButton("Finish Turn");
        turnEndButton.addActionListener(e -> { controller.EndTurn(); });
        
        turnEndButton.setPreferredSize(new Dimension(400, 100));

        boardPanel.add(turnEndButton);
        return boardPanel;
    }

    private JPanel createExitDoor(GridBagConstraints c) {
        JPanel endPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        c.insets = new Insets(10, 0, 5, 0);
        endPanel.setOpaque(false);
        c.gridx = 3;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weighty = 1;
        c.ipadx = 0;
        c.ipady = 0;
        
        JButton endButton = new JButton("End game");
        endButton.addActionListener(e -> { 
            controller.endGame();
         });
        
        endButton.setPreferredSize(new Dimension(75, 150));

        endPanel.add(endButton);
        return endPanel;
    }

    private void createGridBag() {

        gamePanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        gamePanel.add(createRoomGrid(c), c);
        gamePanel.add(createBoard(c), c);
        gamePanel.add(createTeacherPanel(c), c);
        gamePanel.add(createCleanerPanel(c), c);
        gamePanel.add(createRoomItemPanel(c), c);
        gamePanel.add(createStudentPanel(c), c);
        gamePanel.add(createItemPanel(c), c);
        gamePanel.add(createExitDoor(c), c);

    }

    private void AddPopupMenu() {
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {}

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
            public void keyReleased(KeyEvent e) {}
            
        });
    }

    public MainWindow(IController controller, String title) {
        super(title);
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {}

        JFrame.setDefaultLookAndFeelDecorated(true);
        
        this.controller = controller;
        mapName = controller.getMapName();
    }

    private void startGame() {
        AddPopupMenu();

        gamePanel = new BackgroundPanel("textures" + File.separator + "Background.png");

        createGridBag();
        getContentPane().add(gamePanel, BorderLayout.CENTER);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

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
            startGame();  
            controller.startGame();
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
        exitButton.addActionListener(e -> {    exitGame();  });
        exitButton.setContentAreaFilled(false);
        exitButton.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
        exitButton.setFont(new Font("Courier New", Font.PLAIN, 50));
        menuPanel.add(exitButton);

        mainPanel.add(menuPanel);
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        setPreferredSize(new Dimension(1920, 1130));
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
        mapButton.addActionListener(e -> { mapSelect(); });
        c.gridx = 0;
        c.gridy = 3;
        optionsPanel.add(mapButton, c); 

        SpinnerModel studentSelect = new SpinnerNumberModel(controller.getStudentNum(),Controller.minStudentSize, Controller.maxPlayerSize,1);
        c.gridx = 2;
        c.gridy = 0;
        c.ipadx = 10;
        c.ipady = 10;
        c.insets = new Insets(0, 30, 0, 0);
        optionsPanel.add(new JSpinner(studentSelect), c);

        SpinnerModel teacherSelect = new SpinnerNumberModel(controller.getTeacherNum(), Controller.minTeacherSize, Controller.maxPlayerSize, 1);
        c.gridx = 2;
        c.gridy = 1;
        c.ipadx = 10;
        c.ipady = 10;
        optionsPanel.add(new JSpinner(teacherSelect), c);

        SpinnerModel cleanerSelect = new SpinnerNumberModel(controller.getCleanerNum(), Controller.minCleanerSize, Controller.maxPlayerSize, 1);
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
        if(!mapLocation.exists())
            mapLocation.mkdir();
        JFileChooser fc = new JFileChooser(mapLocation);

        if(fc.showDialog(this, "Select map") == JFileChooser.APPROVE_OPTION) {            
            mapName = fc.getSelectedFile().getName();
            mapNameL.setText(mapName.replace(".txt", ""));
        }

    }

    private void saveOptions(Object sVal, Object tVal, Object cVal, Object mVal) {
        controller.setParameters((int)sVal, (int)tVal, (int)cVal, (String)mVal);
    }

    private void exitGame() {
        System.exit(0);
    }

    private void RefreshComponents() {

        for (int i = 0; i < 12; i++) {
            doors[i].setIcon(null);
            for (ActionListener al : doors[i].getActionListeners()) 
                doors[i].removeActionListener(al);   
        }

        for (int i = 0; i < 10; i++) {
            students[i].setIcon(null);

            teachers[i].setIcon(null);

            cleaners[i].setIcon(null);

            items[i].setIcon(null);
            for (MouseListener ml : items[i].getMouseListeners()) 
                items[i].removeMouseListener(ml);

            roomItems[i].setIcon(null);
            for (ActionListener al : roomItems[i].getActionListeners()) 
                roomItems[i].removeActionListener(al);
            
        }
    }

    @Override
    public void RefreshView() {
        RefreshComponents();

        currentVPlayer = (VStudent) controller.getCP();
        currentVRoom = (VRoom) currentVPlayer.getModelStudent().getVRoom();

        if(currentVRoom == null)
            return;

        if(currentVRoom.getModelRoom().isPoisonous()) 
            gamePanel.setBackground("textures" + File.separator + "BackgroundPoisoned.png");
        if(currentVRoom.getModelRoom().isCursed()) 
            gamePanel.setBackground("textures" + File.separator + "BackgroundCursed.png");
        if(currentVRoom.getModelRoom().isClean())    
            gamePanel.setBackground("textures" + File.separator + "Background.png");

        //Doors
        for (int i = 0; i < currentVRoom.getModelRoom().getNeighBourList().size(); i++) {
            currentVRoom.getModelRoom().getNeighBourList().get(i).draw(currentVPlayer, doors[11- i], controller);
        }

        //Player's items
        for (int i = 0; i < currentVPlayer.getModelStudent().getItemList().size(); i++) {
            currentVPlayer.getModelStudent().getItemList().get(i).draw(currentVPlayer, items[i], controller);
        }

        //Items on the floor
        for (int i = 0; i < currentVRoom.getModelRoom().getRoomItems().size(); i++) {
            currentVRoom.getModelRoom().getRoomItems().get(i).draw(currentVPlayer, roomItems[i], controller);
        }

        //Teachers
        for (int i = 0; i < currentVRoom.getModelRoom().getTeacherList().size(); i++) {
            currentVRoom.getModelRoom().getTeacherList().get(i).draw(currentVPlayer, teachers[i], controller);
        }

        //Cleaners
        for (int i = 0; i < currentVRoom.getModelRoom().getCleanerList().size(); i++) {
            currentVRoom.getModelRoom().getCleanerList().get(i).draw(currentVPlayer, cleaners[i], controller);
        }

        //Students
        for (int i = 0; i < currentVRoom.getModelRoom().getStudentList().size(); i++) {
            currentVRoom.getModelRoom().getStudentList().get(i).draw(currentVPlayer, students[i], controller);
        }
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

    @Override
    public void endGame(boolean victory) {
        JDialog dialog = new JDialog(this, "gameOver", true);
        dialog.setSize(300, 150);
        dialog.setLayout(new BorderLayout());

        String message = victory == true ? "VICTORY" : "DEFEAT";

        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        dialog.add(messageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton b = new JButton("Continue");
        buttonPanel.add(b);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        b.addActionListener(e -> { 
            dialog.setVisible(false);
            RefreshComponents();
            getContentPane().remove(gamePanel);
            controller.resetGame();
            saveOptions(Controller.minStudentSize, Controller.minTeacherSize, Controller.minCleanerSize, Controller.defaultMapName);
            drawMenu();
        });

        // Center the dialog on the parent frame
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

    }

}
