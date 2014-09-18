package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;


public class GameRunner {

    private static boolean notAWinner;

    public static void main(String[] args) {
        final PrintStream globalOut = System.out;

        try {
            final File testOutputFolder = new File("test", String.format("%d",
                    System.currentTimeMillis()));
            testOutputFolder.mkdirs();

            for (int i = 0; i < 1000; i++) {
                final int gameSeed = 147621 + 13 * i;

                final File gameOutputFile = new File(testOutputFolder, String.format
                        ("test-%d.txt", gameSeed));

                System.setOut(new PrintStream(new FileOutputStream(gameOutputFile)));

                Game aGame = new Game();

                aGame.add("Chet");
                aGame.add("Pat");
                aGame.add("Sue");

                Random rand = new Random(gameSeed);

                do {
                    aGame.roll(rand.nextInt(5) + 1);

                    if (rand.nextInt(9) == 7) {
                        notAWinner = aGame.wrongAnswer();
                    } else {
                        notAWinner = aGame.wasCorrectlyAnswered();
                    }
                } while (notAWinner);
            }
        } catch (FileNotFoundException logged) {
            logged.printStackTrace();
        } finally {
            System.setOut(globalOut);
        }
    }
}
