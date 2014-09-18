package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;

import java.io.PrintStream;
import java.util.Random;


public class GameRunner {

    private static boolean notAWinner;

    public static void main(String[] args) {
        final PrintStream globalOut = System.out;

        try {
            Game aGame = new Game();

            aGame.add("Chet");
            aGame.add("Pat");
            aGame.add("Sue");

            Random rand = new Random(4);

            do {
                aGame.roll(rand.nextInt(5) + 1);

                if (rand.nextInt(9) == 7) {
                    notAWinner = aGame.wrongAnswer();
                } else {
                    notAWinner = aGame.wasCorrectlyAnswered();
                }
            } while (notAWinner);
        } finally {
            System.setOut(globalOut);
        }
    }
}
