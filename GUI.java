
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
    private JLabel playingPhraseLabel;
    public static boolean isGameStart = false;
    public static boolean isGameOver = false;
    public static Turn turnMaking = new Turn();

    public GUI() {
        // Main GUI Declarations
        setTitle("Word Game");

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        JButton addPlayerButton = new JButton("Add Player");
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
        JButton hostManageButton = new JButton("Manage Host and Gamephrase");
        hostManageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hostName = JOptionPane.showInputDialog("Enter Name for Host");
                gamePhrase = JOptionPane.showInputDialog("Enter gamephrase");
                if (hostName != null && !hostName.isEmpty()) {
                    hostHeader.setText(String.format("<html>Current Host:<br>%s</html>", hostName));
                }

            }
        });

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
        playerListPanel.add(hostManageButton);

        // Adding Elements to Main GUI
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(playerListPanel, BorderLayout.WEST);
        getContentPane().add(gameBoard, BorderLayout.EAST);
        getContentPane().add(startGameButton, BorderLayout.SOUTH);

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

}
