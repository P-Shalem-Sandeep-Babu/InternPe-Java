package RockPaperScissors;
import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        String[] choices = {"Rock", "Paper", "Scissors"};
        
        System.out.println("Welcome to Rock-Paper-Scissors!");
        System.out.println("Enter 0 for Rock, 1 for Paper, 2 for Scissors");
        
        System.out.print("Choose number of rounds (3 or 5): ");
        int maxRounds = scanner.nextInt();
        int playerScore = 0;
        int computerScore = 0;
        
        for (int round = 1; round <= maxRounds && playerScore < maxRounds / 2 + 1 && computerScore < maxRounds / 2 + 1; round++) {
            System.out.print("\nRound " + round + " - Your choice: ");
            int playerChoice = scanner.nextInt();
            int computerChoice = random.nextInt(3);
            
            System.out.println("You chose: " + choices[playerChoice]);
            System.out.println("Computer chose: " + choices[computerChoice]);
            
            if (playerChoice == computerChoice) {
                System.out.println("It's a tie!");
            } else if ((playerChoice == 0 && computerChoice == 2) || 
                       (playerChoice == 1 && computerChoice == 0) || 
                       (playerChoice == 2 && computerChoice == 1)) {
                System.out.println("You win this round!");
                playerScore++;
            } else {
                System.out.println("Computer wins this round!");
                computerScore++;
            }
 
            System.out.println("Score - You: " + playerScore + " | Computer: " + computerScore);
        }

        if (playerScore > computerScore) {
            System.out.println("\nCongratulations! You won the game.");
        } else {
            System.out.println("\nGame over! The computer won the game.");
        }
        
        scanner.close();
    }
}
