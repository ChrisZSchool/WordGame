import java.util.Scanner;

public class Turn {

    public static Scanner sc = new Scanner(System.in);
    public static Numbers num = new Numbers();

    public Boolean takeTurn(Players player, Hosts hosts) {

        // Initial Guess Prompt
        if (player.getLastName() != "")
            System.out.println(
                    String.format("[%s]: %s %s, guess what number im thinking between 0-100", hosts.getFirstName(),
                            player.getFirstName(), player.getLastName()));
        else
            System.out.println(
                    String.format("[%s]: %s, guess what number im thinking between 0-100", hosts.getFirstName(),
                            player.getFirstName()));

        int playerGuess = sc.nextInt();

        if (num.compareNumber(playerGuess)) {
            player.setMoney(player.getMoney() + 500);
            System.out.println(player.toString());
            return true;
        } else {
            player.setMoney(player.getMoney() - 200);
            System.out.println(player.toString());
            return false;
        }

    }
}
