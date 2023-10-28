import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Turn {

    public static Scanner sc = new Scanner(System.in);
    public static Phrases phrase = new Phrases();
    public static GUI gui = new GUI();

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

    public boolean takeTurn(Players player, Hosts hosts) {
        try {

            // Initialize Random Class
            Random r = new Random();

            String playerGuess = JOptionPane
                    .showInputDialog(String.format("Guess a letter player %s", player.getFirstName()));
            if (playerGuess.length() > 1) {
                gui.addGameMessages("Invalid Guess");
                playerGuess = "0";
            }
            boolean result = phrase.findLetters(playerGuess);
            int rewardOptionRoll = r.nextInt(2);

            // Final Comparison
            if (result) {
                playSound(true);
                if (rewardOptionRoll == 1) {
                    Money money = new Money();
                    int reward = money.displayWinnings(player, true);
                    player.setMoney(player.getMoney() + reward);

                } else {
                    Physical physical = new Physical();
                    physical.displayWinnings(player, true);
                }
                gui.addGameMessages(player.toString());
                return true;
            } else {
                return false;
            }

        } catch (MultipleLettersException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return false;
    }
}
