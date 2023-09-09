import java.util.Random;

public class Numbers {

    // Global Vars
    private static int randomNum;

    // Functs

    public int getRandomNum() {
        return this.randomNum;
    }

    public void setRandomNum(int value) {
        this.randomNum = value;
    }

    public void generateNumber() {
        Random r = new Random();
        this.randomNum = r.nextInt(100);
    }

    public boolean compareNumber(int guess) {

        if (this.randomNum == guess) {
            System.out.println("Congratulations, you guessed the number!");
            return true;
        } else if (this.randomNum < guess) {
            System.out.println("I'm sorry.  That guess was too high.");
            return false;
        } else {
            System.out.println("I'm sorry, That guess was too low.");
            return false;
        }
    }

}
