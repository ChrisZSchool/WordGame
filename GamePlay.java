import java.util.Scanner;

public class GamePlay {

    // Global Vars
    public static boolean isGameOver = false;
    public static Turn turnMaking = new Turn();
    public static Scanner sc = new Scanner(System.in);
    public static Numbers randomNumberClass = new Numbers();

    // Functs
    public static void main(String[] args) {
        // Initialize Local Vars
        String firstName = "";
        String lastName = "";
        Players person;
        Hosts host = new Hosts("Jefferson");

        // Grab Data
        System.out.println("What is your name :) ?");
        firstName = sc.nextLine();
        System.out.println("Enter a last name? 'yes' 'no' ");
        String result = sc.nextLine().trim();

        // Compare Data
        if (result.equalsIgnoreCase("yes")) {
            System.out.print("Enter a Last Name: ");
            lastName = sc.next();
            sc.nextLine();
            person = new Players(firstName, lastName);
        } else
            person = new Players(firstName);

        // Generate Num
        host.randomizeNum();

        // Game Loop
        while (!isGameOver) {

            Boolean gameResult = turnMaking.takeTurn(person, host);

            // Final Comparison
            if (gameResult) {
                System.out.println("Do you want to continue? {'yes','no'}");
                String isContinue = sc.nextLine().trim();
                if (isContinue.equalsIgnoreCase("yes"))
                    host.randomizeNum();
                else
                    isGameOver = false;
            }

        }

    }
}
