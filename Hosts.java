import java.util.Scanner;

public class Hosts extends Person {

    public Hosts(String name) {
        super(name);
    }

    public void selectWord() {
        Scanner sc = new Scanner(System.in);
        System.out.println(String.format("[%s]: Select a word to guess!", getFirstName()));
        String result = sc.nextLine();
        Phrases phrase = new Phrases(result);
    }
}
