public class Money implements Award {

    private static int winningAmount = 500;
    private static int losingAmount = -200;

    @Override
    public int displayWinnings(Players player, boolean guess) {
        if (guess) {
            System.out.println(
                    String.format("Player %s has won! Your winnings are %d ", player.getFirstName(), winningAmount));
            return winningAmount;
        } else {
            System.out.println(
                    String.format("Player %s has lost! Your winnings are %d ", player.getFirstName(), losingAmount));
            return losingAmount;
        }
    }

}