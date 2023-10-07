import java.util.Random;

import javax.swing.JOptionPane;

public class Physical implements Award {

    private static String[] physicalPrizes = { "Curved TV", "Nintendo Switch", "Jeep Truck", "Backyard Pool",
            "Cruise Voucher" };

    @Override
    public int displayWinnings(Players player, boolean guess) {
        if (guess) {
            JOptionPane.showMessageDialog(null,
                    String.format("Player %s has won! You have won a %s ", player.getFirstName(),
                            physicalPrizes[getRandomPrize()]));

            return 0;
        } else {
            JOptionPane.showMessageDialog(null,
                    String.format("Player %s has lost! You could have won a %s ", player.getFirstName(),
                            physicalPrizes[getRandomPrize()]));
            return 0;
        }
    }

    private int getRandomPrize() {
        Random r = new Random();
        return r.nextInt(4);
    }

}
