import java.util.Random;
import java.util.Scanner;

public class Turn {

    public static Scanner sc = new Scanner(System.in);
    public static Numbers num = new Numbers();

    public Boolean takeTurn(Players player, Hosts hosts) {
        // Initialize Random Class
        Random r = new Random();

        // Initial Guess Prompt -- Grab Data
        if (player.getLastName() != "")
            System.out.println(
                    String.format("[%s]: %s %s, guess what number im thinking between 0-100", hosts.getFirstName(),
                            player.getFirstName(), player.getLastName()));
        else
            System.out.println(
                    String.format("[%s]: %s, guess what number im thinking between 0-100", hosts.getFirstName(),
                            player.getFirstName()));

        int playerGuess = sc.nextInt();
        int rewardOptionRoll = r.nextInt(2);

        // Final Comparison
        if (num.compareNumber(playerGuess)) {
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
            if (rewardOptionRoll == 1) {
                Money money = new Money();
                int reward = money.displayWinnings(player, false);
                player.setMoney(player.getMoney() + reward);

            } else {
                Physical physical = new Physical();
                physical.displayWinnings(player, false);
            }
            System.out.println(player.toString());
            return false;
        }

    }
}
