import java.util.Scanner;

public class GamePlay {

    // Global Vars
    public static boolean isGameOver = false;
    public static Turn turnMaking = new Turn();
    public static Scanner sc = new Scanner(System.in);

    // Functs
    public static void main(String[] args) {
        // Initialize Local Vars

        Players[] playerList = new Players[3];
        Hosts host = new Hosts("Jefferson");

        // Grab Data
        for (int i = 0; i < playerList.length; i++) {

            Players person;

            System.out.println(String.format("What is your name player %d!", i + 1));
            String firstName = sc.nextLine();
            System.out.println("Enter a last name? 'yes' 'no' ");
            String result = sc.nextLine().trim();

            // Compare Data
            if (result.equalsIgnoreCase("yes")) {
                System.out.print("Enter a Last Name: ");
                String lastName = sc.next();
                sc.nextLine();
                person = new Players(firstName, lastName);
            } else
                person = new Players(firstName);

            playerList[i] = person;

        }

        System.out.println(playerList[0]);
        System.out.println(playerList[1]);
        System.out.println(playerList[2]);

        // Ask for word
        host.selectWord();

        // Game Loop
        while (!isGameOver) {
            Boolean gameResult = false;

            for (int i = 0; i < playerList.length; i++) {
                gameResult = turnMaking.takeTurn(playerList[i], host);

                // Final Comparison
                if (gameResult) {
                    System.out.println("Do you want to continue? {'yes','no'}");
                    String isContinue = sc.nextLine().trim();
                    if (isContinue.equalsIgnoreCase("yes"))
                        host.selectWord();
                    else
                        isGameOver = true;
                    break;
                }
            }

        }

    }
}
