public class Hosts extends Person {

    public Hosts(String name) {
        super(name);
    }

    public void randomizeNum() {
        Numbers rng = new Numbers();
        rng.generateNumber();
    }
}
