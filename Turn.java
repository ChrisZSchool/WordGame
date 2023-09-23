import java.util.Random;
import java.util.Scanner;

public class Turn {

    public static Scanner sc = new Scanner(System.in);
    public static Phrases phrase = new Phrases();

    public boolean takeTurn(Players player, Hosts hosts) {
        try {

            // Initialize Random Class
            Random r = new Random();

            // Initial Guess Prompt -- Grab Data
            if (player.getLastName() != "")
                System.out.println(
                        String.format("[%s]: %s %s, guess a letter from the word %s", hosts.getFirstName(),
                                player.getFirstName(), player.getLastName(), phrase.getPhrase()));
            else
                System.out.println(
                        String.format("[%s]: %s, guess a letter from the word %s", hosts.getFirstName(),
                                player.getFirstName(), phrase.getPhrase()));

            String playerGuess = sc.nextLine();
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
                System.out.println(player.toString());
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
