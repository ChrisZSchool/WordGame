
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class GUI extends JFrame {

    // Globals
    Font labelFont = new Font("Arial", Font.BOLD, 24);
    Font gameFont = new Font("Arial", Font.PLAIN, 18);
    public static ArrayList<Players> playerList = new ArrayList<Players>();
    public static String gamePhrase;
    private static Phrases phrase = new Phrases();
    public static String hostName;
    private JLabel playerListLabel;
    private JLabel hostHeader;
    private JTextArea gameMessageBox;
    private JLabel playingPhraseLabel;
    public static boolean isGameStart = false;
    public static boolean isGameOver = false;
    public static DefaultListModel<String> gameMessages = new DefaultListModel<>();
    private JList<String> gameMessagesList;
    public static Turn turnMaking = new Turn();

    public GUI() {
        // Main GUI Declarations
        setTitle("Word Game");

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenu aboutMenu = new JMenu("About");

        // Menu Buttons
        // Game Menu
        JMenuItem addPlayerButton = new JMenuItem("Add Player");
        JMenuItem addHostButton = new JMenuItem("Add Host");
        // About Menu
        JMenuItem layoutButton = new JMenuItem("Layout");
        JMenuItem attributionButton = new JMenuItem("Attributions");

        // JPanel for player list
        JPanel playerListPanel = new JPanel();
        playerListPanel.setLayout(new BoxLayout(playerListPanel, BoxLayout.Y_AXIS));
        playerListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // PlayerList Header Label
        JLabel playerListHeaderLabel = new JLabel("Player List");
        playerListHeaderLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        playerListHeaderLabel.setFont(labelFont);
        // Player List Label
        this.playerListLabel = new JLabel();
        playerListLabel.setFont(gameFont);

        // Add Player Button
        JPanel hostButtonPanel = new JPanel(new BorderLayout());
        addPlayerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = JOptionPane.showInputDialog("Enter Player Name");
                if (playerName != null && !playerName.isEmpty() && playerList.size() < 3) {
                    playerList.add(new Players(playerName));
                }
                updatePlayerList();
            }
        });
        // Host Header
        hostHeader = new JLabel("Current Host");
        hostHeader.setFont(labelFont);
        hostHeader.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        // Host Manage Button
        addHostButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hostName = JOptionPane.showInputDialog("Enter Name for Host");
                gamePhrase = JOptionPane.showInputDialog("Enter gamephrase");
                if (hostName != null && !hostName.isEmpty()) {
                    hostHeader.setText(String.format("<html>Current Host:<br>%s</html>", hostName));
                }

            }
        });

        // Layout button in About
        layoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "I went with a grid layout, because it was easy to get each item into each grid row and column.");
            }
        });

        attributionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Nintendo Switch Image ~ PixaBay ~ ant2506\n" +
                        "Flatscreen TV - freepik - <a href=\"https://www.freepik.com/free-photo/landscape-nature-scene-tv-appliance-generative-ai_41060528.htm#query=curved%20tv&position=0&from_view=search&track=ais\">Image by vecstock</a> on Freepik\n"
                        +
                        "Backyard pool - freepik - <a href=\"https://www.freepik.com/free-photo/landscape-nature-scene-tv-appliance-generative-ai_41060528.htm#query=curved%20tv&position=0&from_view=search&track=ais\">Image by vecstock</a> on Freepik\n"
                        +
                        "Jeep Truck - free pick - <a href=\"https://www.freepik.com/free-photo/landscape-nature-scene-tv-appliance-generative-ai_41060528.htm#query=curved%20tv&position=0&from_view=search&track=ais\">Image by vecstock</a> on Freepik\n"
                        +
                        "Gift Voucher - G - hotelsafloat.com - https://www.hotelsafloat.com/gift\n" +
                        "Ding Sound - freesoundeffects - https://www.freesoundeffects.com/free-track/elevator-ding-426631/ding/\n"
                        +
                        "Buzzer Sound - zaps plat - https://www.zapsplat.com/music/gameshow-buzzer-buzz-short-2/");
            }
        });

        // Text Area to hold game messages
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(1, 1)); // 1 row, 1 column
        gameMessagesList = new JList<>(gameMessages);

        // Scroll for that Text Area
        JScrollPane scrollPane = new JScrollPane(gameMessagesList);
        scrollPane.setPreferredSize(new Dimension(100, 100));

        textPanel.add(scrollPane);

        // Checkbox
        JCheckBox saveMessagesCheck = new JCheckBox("Save Messages");
        saveMessagesCheck.setToolTipText("Saves messages in box to the left");

        // Layout Action

        // Game Phrase
        JPanel gameBoard = new JPanel();
        gameBoard.setLayout(new BoxLayout(gameBoard, BoxLayout.Y_AXIS));
        JLabel gameHeader = new JLabel("Active Phrase");
        gameHeader.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        gameHeader.setFont(labelFont);
        gameBoard.add(gameHeader);
        gameBoard.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 30));
        playingPhraseLabel = new JLabel();
        this.playingPhraseLabel.setFont(gameFont);
        gameBoard.add(this.playingPhraseLabel);

        // // Image popup for prize
        // JFrame prizePopUp = new JFrame("Image Pop Up");
        // prizePopUp.setSize(400, 400);
        // prizePopUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // ImageIcon imageIcon = new ImageIcon("Assets/nintendo_switch.jpg");
        // JLabel label = new JLabel(imageIcon);
        // prizePopUp.add(label);
        // prizePopUp.setVisible(true);

        // Start Game
        JButton startGameButton = new JButton("Start");
        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (playerList.size() > 0 && hostName != null && !hostName.isEmpty()) {
                    isGameStart = true;
                    isGameOver = false;
                    Phrases phrase = new Phrases(gamePhrase);
                    playingPhraseLabel.setText(phrase.getPhrase());
                    Hosts host = new Hosts(hostName);

                    while (!isGameOver) {

                        if (isGameStart) {
                            System.out.println("started");
                            playingPhraseLabel.setText(phrase.getPhrase());

                            Boolean gameResult = false;

                            for (int i = 0; i < playerList.size(); i++) {
                                gameResult = turnMaking.takeTurn(playerList.get(i), host);

                                // Final Comparison
                                if (gameResult) {
                                    int result = JOptionPane.showConfirmDialog(null, "Do you want to continue?",
                                            "Confirmation", JOptionPane.YES_NO_OPTION);
                                    if (result == JOptionPane.YES_OPTION) {
                                        if (!saveMessagesCheck.isSelected())
                                            gameMessages.clear();
                                        String newPhrase = JOptionPane.showInputDialog("Enter GamePhrase");
                                        phrase = new Phrases(newPhrase);

                                    } else {
                                        isGameOver = true;
                                        return;
                                    }
                                }
                            }
                        }
                    }

                } else
                    JOptionPane.showMessageDialog(null, "Not Enough Players || Invalid Host Name");

            }
        });

        // Player List Panel
        playerListPanel.add(playerListHeaderLabel);
        playerListPanel.add(playerListLabel);
        playerListPanel.add(addPlayerButton);
        playerListPanel.add(hostHeader);

        // Setting Mnemonics
        gameMenu.setMnemonic('G');

        // Adding Elements to Main GUI
        getContentPane().setLayout(new GridLayout(2, 2));
        getContentPane().add(startGameButton);

        getContentPane().add(playerListPanel, BorderLayout.WEST);
        getContentPane().add(gameBoard, BorderLayout.EAST);
        getContentPane().add(textPanel, BorderLayout.CENTER);
        getContentPane().add(saveMessagesCheck);

        gameMenu.add(addPlayerButton);
        gameMenu.add(addHostButton);
        aboutMenu.add(layoutButton);
        aboutMenu.add(attributionButton);
        menuBar.add(gameMenu);
        menuBar.add(aboutMenu);
        setJMenuBar(menuBar);

        // We are ready, set visible
        setVisible(true);

    }

    private void updatePlayerList() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        for (int i = 0; i < playerList.size(); i++) {
            sb.append(playerList.get(i).getFirstName());
            sb.append("<br>");
        }
        sb.append("</html>");
        playerListLabel.setText(sb.toString());
    }

    public void addGameMessages(String message) {
        // Add the message to the gameMessages list
        gameMessages.addElement(message);

        // Update the UI within the EDT
        SwingUtilities.invokeLater(() -> {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < gameMessages.size(); i++) {
                sb.append(gameMessages.get(i));
                sb.append("\n"); // Add a line break between messages
            }

            // Set the gameMessageBox text
            gameMessageBox.setText(sb.toString());
        });
    }

}
