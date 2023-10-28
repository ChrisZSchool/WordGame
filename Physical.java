import java.util.Random;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Physical implements Award {

    private static String[] physicalPrizes = { "Curved TV", "Nintendo Switch", "Jeep Truck", "Backyard Pool",
            "Cruise Voucher" };
    private static String[] prizePaths = { "flatscreen_tv.jpg", "nintendo_switch.jpg", "jeep_truck.jpg",
            "backyard_pool.jpg", "gift_voucher.jpg",
    };
    private static GUI gui = new GUI();

    @Override
    public int displayWinnings(Players player, boolean guess) {
        int rng = getRandomPrize();

        if (guess) {
            gui.addGameMessages(String.format("Player %s has won! You have won a %s ", player.getFirstName(),
                    physicalPrizes[rng]));
            JOptionPane.showMessageDialog(null,
                    String.format("Player %s has won! You have won a %s ", player.getFirstName(),
                            physicalPrizes[rng]));
            showPrize(prizePaths[rng]);

            return 0;
        } else {
            gui.addGameMessages(String.format("Player %s has lost! You could have won a %s ", player.getFirstName(),
                    physicalPrizes[rng]));
            JOptionPane.showMessageDialog(null,
                    String.format("Player %s has lost! You could have won a %s ", player.getFirstName(),
                            physicalPrizes[rng]));
            showPrize(prizePaths[rng]);
            return 0;
        }
    }

    private int getRandomPrize() {
        Random r = new Random();
        return r.nextInt(4);
    }

    private void showPrize(String prizeName) {
        // Image popup for prize
        JFrame prizePopUp = new JFrame("Image Pop Up");
        prizePopUp.setSize(800, 400);
        prizePopUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon imageIcon = new ImageIcon("Assets/" + prizeName);
        int width = imageIcon.getIconWidth();
        int height = imageIcon.getIconHeight();

        // Create a JLabel for the image
        JLabel label = new JLabel(
                new ImageIcon(imageIcon.getImage().getScaledInstance(width / 6, height / 6, Image.SCALE_SMOOTH)));

        // Add the label to the frame
        prizePopUp.add(label);
        prizePopUp.setVisible(true);

        // Create a timer for animation
        Timer animationTimer = new Timer(100, new ActionListener() {

            // Animation variables
            int initialX = label.getX();
            int wiggleAmount = 10; // Adjust the amount of wiggle as needed
            int wiggleDirection = 1; // 1 for right, -1 for left

            @Override
            public void actionPerformed(ActionEvent e) {
                int x = label.getX();
                int y = label.getY();

                // Wiggle the image horizontally
                x += wiggleDirection * wiggleAmount;

                // Change direction when reaching the limits
                if (x > initialX + wiggleAmount || x < initialX - wiggleAmount) {
                    wiggleDirection *= -1;
                }

                label.setLocation(x, y);
            }
        });

        animationTimer.start();
    }

}
