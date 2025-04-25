import java.util.Random;
import java.util.Scanner;

class Player {
    String name;
    int id;
    int score;
    int currentLevel;

    public Player(String name, int id) {
        this.name = name;
        this.id = id;
        this.score = 0;
        this.currentLevel = 1;
    }
}

public class game {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();

        // Player setup
        System.out.print("Enter your name: ");
        String name = input.nextLine();
        System.out.print("Enter your ID: ");
        int id = input.nextInt();

        Player player1 = new Player(name, id);
        int highestScore = 0;

        // Stage 1: 0-10, 3 attempts
        if (!playGame(input, random, 10, 3, player1)) {
            System.out.println("You lost! Better luck next time.");
            printScores(player1, highestScore);
            return;
        }
        highestScore = player1.score;
        player1.currentLevel++;

        // Stage 2: 0-20, 4 attempts
        if (!playGame(input, random, 20, 4, player1)) {
            System.out.println("You lost! Better luck next time.");
            printScores(player1, highestScore);
            return;
        }
        highestScore = player1.score;
        player1.currentLevel++;

        // Stage 3: 0-100, 6 attempts
        if (playGame(input, random, 100, 6, player1)) {
            System.out.println("Congratulations! You won the game!");
            player1.currentLevel++;
            highestScore = player1.score;
        } else {
            System.out.println("You lost! Better luck next time.");
        }

        // Final scores
        printScores(player1, highestScore);
    }

    public static boolean playGame(Scanner input, Random random, int range, int attempts, Player player) {
        int target = random.nextInt(range + 1);
        System.out.println("\nLevel " + player.currentLevel + ": Guess a number between 0 and " + range + ". You have " + attempts + " trials.");

        for (int i = 1; i <= attempts; i++) {
            System.out.print("Attempt " + i + ": ");
            int guess = input.nextInt();

            if (guess == target) {
                System.out.println("Correct! Moving to the next level.");
                player.score += 10; // Award 10 points per successful level
                return true;
            } else if (guess < target) {
                System.out.println("Too low. Try again.");
            } else {
                System.out.println("Too high. Try again.");
            }
        }

        System.out.println("The correct number was: " + target);
        return false;
    }

    public static void printScores(Player player, int highestScore) {
        System.out.println("\n=== Game Over ===");
        System.out.println("Player Name: " + player.name);
        System.out.println("Player ID: " + player.id);
        System.out.println("Current Level Reached: " + player.currentLevel);
        System.out.println("Score: " + player.score);
        System.out.println("Highest Score: " + highestScore);
    }
}