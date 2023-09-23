public class Phrases {
    private static String gamePhrase;
    private static String playingPhrase;

    public Phrases(String gamePhrase) {
        Phrases.gamePhrase = gamePhrase;
        Phrases.playingPhrase = convertToUnderline(gamePhrase);
    }

    public Phrases() {

    }

    private String convertToUnderline(String input) {
        String result = input.replaceAll("[a-zA-Z]", "_");
        return result;
    }

    public String getPhrase() {
        return Phrases.playingPhrase;
    }

    public boolean findLetters(String letter) throws MultipleLettersException {
        if (letter.length() > 1)
            throw new MultipleLettersException();

        boolean charFound = false;
        char playerGuess = letter.toCharArray()[0];
        char[] plainText = gamePhrase.toCharArray();
        char[] gameText = playingPhrase.toCharArray();

        for (int i = 0; i < plainText.length; i++) {
            char currentLetter = plainText[i];
            if (currentLetter == playerGuess) {
                gameText[i] = playerGuess;
                charFound = true;
            }
        }

        Phrases.playingPhrase = String.valueOf(gameText);

        if (charFound)
            System.out.println(String.format("Letter %s was correct", letter));
        else
            System.out.println("Incorrect Guess!");

        if (!Phrases.playingPhrase.contains("_")) {
            System.out.println("Congrats you have guessed the word!");
            return true;
        } else
            return false;
    }
}
