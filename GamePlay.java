import java.util.Scanner;

public class GamePlay {

    // Global Vars
    private static Person person;
    public static boolean isGameOver = false;
    public static Scanner sc = new Scanner(System.in);
    public static Numbers randomNumberClass = new Numbers();

    // Functs
    public static void main(String[] args) {
        // Initialize Local Vars
        String firstName = "";
        String lastName = "";

        // Grab Data
        System.out.println("What is your name :) ?");
        firstName = sc.nextLine();
        System.out.println("Enter a last name? 'yes' 'no' ");
        String result = sc.nextLine().trim();

        // Compare Data
        if (result.equalsIgnoreCase("yes")) {
            System.out.print("Enter a Last Name: ");
            lastName = sc.next();
            person = new Person(firstName, lastName);
        } else
            person = new Person(firstName);

        // Generate Num
        randomNumberClass.generateNumber();

        // Game Loop
        while (!isGameOver) {
            // Compare for formatting
            if (lastName != "")
                System.out.println(
                        String.format("Hi %s %s, I'm thinking of a number between 1-100?", firstName, lastName));
            else
                System.out.println(String.format("Hi %s, I'm thinking of a number between 1-100?", firstName));

            // Grab Guess
            int guess = sc.nextInt();

            // Final Comparison
            if (guess == randomNumberClass.getRandomNum()) {
                System.out.println(String.format("CORRECT! Nice work %s", firstName));
                isGameOver = true;
            } else
                System.out.println("WRONG!");

        }

    }
}
