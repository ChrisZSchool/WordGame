import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Turn {

    public static Scanner sc = new Scanner(System.in);
    public static Phrases phrase = new Phrases();
    public static GUI gui = new GUI();

    public boolean takeTurn(Players player, Hosts hosts) {
        try {

            // Initialize Random Class
            Random r = new Random();

            String playerGuess = JOptionPane
                    .showInputDialog(String.format("Guess a letter player %s", player.getFirstName()));
            if (playerGuess.length() > 1) {
                gui.addGameMessages("Invalid Guess");
                playerGuess = "0";
            }
            boolean result = phrase.findLetters(playerGuess);
            int rewardOptionRoll = r.nextInt(2);

            // Final Comparison
            if (result) {
                if (rewardOptionRoll == 1) {
                    Money money = new Money();
                    int reward = money.displayWinnings(player, true);
                    player.setMoney(player.getMoney() + reward);

                } else {
                    Physical physical = new Physical();
                    physical.displayWinnings(player, true);
                }
                gui.addGameMessages(player.toString());
                return true;
            } else {
                return false;
            }

        } catch (MultipleLettersException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return false;
    }
}
