import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

public class Phrases {
    private static String gamePhrase;
    private static String playingPhrase;
    public static GUI gui = new GUI();

    public Phrases(String gamePhrase) {
        Phrases.gamePhrase = gamePhrase;
        Phrases.playingPhrase = convertToUnderline(gamePhrase);
    }

    public Phrases() {

    }

    private void playSound(Boolean isWinning) {
        try {
            String soundType;
            if (isWinning)
                soundType = "Elevator_Ding.wav";
            else
                soundType = "buzzer.wav";

            File audioFile = new File("Assets/" + soundType); // Replace with the path to your WAV file

            // Get an audio input stream from the WAV file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);

            // Get the format of the audio data
            AudioFormat audioFormat = audioInputStream.getFormat();

            // Create a DataLine.Info object to represent the source data line
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);

            // Open the source data line
            SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceDataLine.open(audioFormat);
            sourceDataLine.start();

            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesRead;

            // Read and play the audio data
            while ((bytesRead = audioInputStream.read(buffer, 0, buffer.length)) != -1) {
                sourceDataLine.write(buffer, 0, bytesRead);
            }

            // Drain and close the source data line
            sourceDataLine.drain();
            sourceDataLine.close();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
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

        if (charFound) {
            gui.addGameMessages(String.format("Letter %s was correct", letter));
        } else {
            gui.addGameMessages("Incorrect Guess!");
            playSound(false);

        }
        if (!Phrases.playingPhrase.contains("_")) {
            System.out.println("Congrats you have guessed the word!");
            return true;
        } else
            return false;
    }
}
