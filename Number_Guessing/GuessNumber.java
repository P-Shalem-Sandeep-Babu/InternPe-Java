package Number_Guessing;

import java.util.Scanner;
import java.util.Random;

public class GuessNumber {

    public static void displayWelcomeMessage() {
        System.out.println("===================================");
        System.out.println("     Welcome to Guess the Number!  ");
        System.out.println("===================================");
        System.out.println("Choose a difficulty level and try to guess the correct number.");
        System.out.println("You can play alone or compete with a friend.");
    }

    public static int[] setDifficultyLevel(Scanner scanner) {
        System.out.println("\nChoose Difficulty Level:");
        System.out.println("1. Easy (1-50)");
        System.out.println("2. Medium (1-100)");
        System.out.println("3. Hard (1-200)");
        System.out.print("Enter your choice (1, 2, or 3): ");
        int choice = scanner.nextInt();
        int[] range = new int[2];

        switch (choice) {
            case 1:
                range[0] = 1;
                range[1] = 50;
                break;
            case 2:
                range[0] = 1;
                range[1] = 100;
                break;
            case 3:
                range[0] = 1;
                range[1] = 200;
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Medium (1-100).");
                range[0] = 1;
                range[1] = 100;
                break;
        }
        return range;
    }

    public static int generateRandomNumber(int minRange, int maxRange) {
        Random random = new Random();
        return random.nextInt(maxRange - minRange + 1) + minRange;
    }

    public static int playSinglePlayer(int numberToGuess, Scanner scanner, String playerName) {
        int guess = -1;
        int attempts = 0;
        
        System.out.println("\n" + playerName + "! Try to guess the number.");
        System.out.println("Good luck!\n");
        
        while (guess != numberToGuess) {
            System.out.print("Enter your guess: ");
            guess = scanner.nextInt();
            attempts++;
            
            if (guess < numberToGuess) {
                System.out.println("The number you gave is too 'low'! Try again.");
            } else if (guess > numberToGuess) {
                System.out.println("The number you gave is too 'high'! Try again.");
            }
        }
        System.out.println("Congratulations, " + playerName + "! You've guessed the number in " + attempts + " attempts.");
        return attempts;
    }

    public static void playTwoPlayers(int numberToGuess, Scanner scanner, String player1, String player2) {
        System.out.println("\nTwo players mode! " + player1 + " and " + player2 + ", try to guess the number.");
        
        int attemptsPlayer1 = playSinglePlayer(numberToGuess, scanner, player1);
        int attemptsPlayer2 = playSinglePlayer(numberToGuess, scanner, player2);
        
        if (attemptsPlayer1 < attemptsPlayer2) {
            System.out.println("\n" + player1 + " wins with fewer attempts (" + attemptsPlayer1 + " attempts)!");
        } else if (attemptsPlayer2 < attemptsPlayer1) {
            System.out.println("\n" + player2 + " wins with fewer attempts (" + attemptsPlayer2 + " attempts)!");
        } else {
            System.out.println("\nIt's a tie! Both players guessed the number in " + attemptsPlayer1 + " attempts.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        displayWelcomeMessage();

        int[] range = setDifficultyLevel(scanner);
        int numberToGuess = generateRandomNumber(range[0], range[1]);

        System.out.println("\nChoose Game Mode:");
        System.out.println("1. Single Player ");
        System.out.println("2. Two Players");
        System.out.print("Enter your choice (1 or 2): ");
        int modeChoice = scanner.nextInt();

        if (modeChoice == 1) {
            scanner.nextLine();  
            System.out.print("Enter your name: ");
            String playerName = scanner.nextLine();
            playSinglePlayer(numberToGuess, scanner, playerName);
        }
    
        else if (modeChoice == 2) {
            scanner.nextLine();  
            System.out.print("Enter Player 1's name: ");
            String player1 = scanner.nextLine();
            System.out.print("Enter Player 2's name: ");
            String player2 = scanner.nextLine();
            playTwoPlayers(numberToGuess, scanner, player1, player2);
        } else {
            System.out.println("Invalid choice. Exiting the game.");
        }

        System.out.println("\nThank you for playing Guess the Number!");
        scanner.close();
    }
}

