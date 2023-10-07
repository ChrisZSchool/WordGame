import javax.swing.JOptionPane;

public class Money implements Award {

    private static int winningAmount = 500;
    private static int losingAmount = -200;

    @Override
    public int displayWinnings(Players player, boolean guess) {
        if (guess) {
            JOptionPane.showMessageDialog(null,
                    String.format("Player %s has won! Your winnings are %d ", player.getFirstName(), winningAmount));
            return winningAmount;
        } else {
            JOptionPane.showMessageDialog(null,
                    String.format("Player %s has won! Your have lost %d ", player.getFirstName(), losingAmount));
            return losingAmount;
        }
    }

}