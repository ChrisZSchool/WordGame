public class Players extends Person {

    private int money;

    public Players(String firstName, String lastName) {
        super(firstName, lastName);
        this.money = 1000;
    }

    public Players(String firstName) {
        super(firstName);
        this.money = 1000;
    }

    public int getMoney() {
        return this.money;
    }

    public void setMoney(int value) {
        this.money = value;
    }

    public String toString() {
        return String.format("%s, current monies: %d", getFirstName(), this.money);
    }

}
