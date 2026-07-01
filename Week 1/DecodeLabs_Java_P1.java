import java.util.Random;
import java.util.Scanner;

/**
 * DecodeLabs_Java_P1.java
 * Project 1: Number Guessing Game
 * 
 * A complete, interactive number guessing game that demonstrates:
 *   - Random number generation (java.util.Random)
 *   - User input handling (java.util.Scanner)
 *   - Control flow: loops and conditionals
 *   - Exception handling for invalid input
 *   - Score tracking across multiple rounds
 *
 * Author  : DecodeLabs Intern
 * Batch   : 2026
 */
public class DecodeLabs_Java_P1 {

    // ──────────────────────────────────────────────
    //  GAME CONSTANTS
    // ──────────────────────────────────────────────

    /** The lower bound of the secret number range (inclusive). */
    static final int MIN_NUMBER = 1;

    /** The upper bound of the secret number range (inclusive). */
    static final int MAX_NUMBER = 100;

    /** Maximum guesses allowed per round before the player loses. */
    static final int MAX_ATTEMPTS = 10;

    // ──────────────────────────────────────────────
    //  ENTRY POINT
    // ──────────────────────────────────────────────

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random  random  = new Random();

        int totalScore  = 0;   // Cumulative score across all rounds
        int roundNumber = 0;   // Counts how many rounds were played

        printBanner();

        // ── OUTER LOOP: keep playing rounds until the user says "no" ──
        boolean playAgain = true;

        do {
            roundNumber++;
            System.out.println("\n");
            System.out.println("  ROUND " + roundNumber);
            System.out.println("");

            // Play one round and collect the score earned
            int roundScore = playRound(scanner, random);
            totalScore += roundScore;

            System.out.println("\n  Round score  : " + roundScore  + " pts");
            System.out.println("  Total score  : " + totalScore   + " pts");

            // Ask if the user wants another round
            playAgain = askPlayAgain(scanner);

        } while (playAgain);

        // ── SESSION SUMMARY ──
        printSummary(roundNumber, totalScore);

        scanner.close();   // Always close the Scanner when done
    }

    // ──────────────────────────────────────────────
    //  CORE GAME METHOD  –  plays a single round
    // ──────────────────────────────────────────────

    /**
     * Runs one complete round of the guessing game.
     *
     * @param scanner  shared Scanner for reading user input
     * @param random   shared Random for generating secret numbers
     * @return         score earned this round (0 if player used all attempts)
     */
    static int playRound(Scanner scanner, Random random) {

        // Generate a secret number between MIN_NUMBER and MAX_NUMBER (inclusive).
        // random.nextInt(MAX_NUMBER) produces 0 … 99, so +1 shifts it to 1 … 100.
        int secretNumber = random.nextInt(MAX_NUMBER) + 1;

        System.out.println("\n  I'm thinking of a number between "
                           + MIN_NUMBER + " and " + MAX_NUMBER + ".");
        System.out.println("  You have " + MAX_ATTEMPTS + " attempts. Good luck!\n");

        int attemptsUsed = 0;
        boolean won      = false;

        // ── INNER LOOP: keep asking for guesses until win or out of attempts ──
        while (attemptsUsed < MAX_ATTEMPTS) {

            int guessesLeft = MAX_ATTEMPTS - attemptsUsed;
            System.out.print("  Attempt " + (attemptsUsed + 1)
                             + "/" + MAX_ATTEMPTS
                             + "  (" + guessesLeft
                             + (guessesLeft == 1 ? " chance" : " chances")
                             + " left) → Enter guess: ");

            // ── INPUT VALIDATION: handle non-integer input gracefully ──
            int guess = -1;
            try {
                guess = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                // User typed something like "abc" instead of a number
                System.out.println("  ⚠  Invalid input! Please enter a whole number.");
                scanner.nextLine();   // flush the bad token from the buffer
                continue;            // retry this iteration without incrementing attempts
            }
            scanner.nextLine();       // flush the leftover newline after nextInt()

            // ── RANGE CHECK ──
            if (guess < MIN_NUMBER || guess > MAX_NUMBER) {
                System.out.println("  ⚠  Out of range! Guess between "
                                   + MIN_NUMBER + " and " + MAX_NUMBER + ".");
                continue;             // don't count out-of-range as an attempt
            }

            attemptsUsed++;

            // ── FEEDBACK LOGIC ──
            if (guess == secretNumber) {
                System.out.println("\n  ✅  Correct! The number was " + secretNumber + ".");
                won = true;
                break;                // exit the guessing loop
            } else if (guess > secretNumber) {
                System.out.println("  🔻  Too HIGH!  Try a lower number.");
            } else {
                System.out.println("  🔺  Too LOW!   Try a higher number.");
            }
        }

        // ── ROUND RESULT ──
        if (!won) {
            System.out.println("\n  ❌  Out of attempts! The number was " + secretNumber + ".");
            return 0;   // no points for a failed round
        }

        // Score formula: more points for fewer attempts
        //   Base points = 100
        //   Penalty     = (attempts used - 1) × 10
        //   Minimum     = 10 (always award something for winning)
        int score = Math.max(10, 100 - (attemptsUsed - 1) * 10);
        System.out.println("  🏆  You guessed it in " + attemptsUsed
                           + (attemptsUsed == 1 ? " attempt" : " attempts") + "!");
        return score;
    }

    // ──────────────────────────────────────────────
    //  HELPER: ask the user whether to play again
    // ──────────────────────────────────────────────

    /**
     * Prompts the user to play another round.
     *
     * @param scanner  shared Scanner
     * @return         true if user wants to continue, false otherwise
     */
    static boolean askPlayAgain(Scanner scanner) {
        while (true) {   // keep asking until we get a valid answer
            System.out.print("\n  Play again? (Y / N): ");
            String response = scanner.nextLine().trim().toUpperCase();

            if (response.equals("Y") || response.equals("YES")) {
                return true;
            } else if (response.equals("N") || response.equals("NO")) {
                return false;
            } else {
                System.out.println("  Please type Y or N.");
            }
        }
    }

    // ──────────────────────────────────────────────
    //  DISPLAY HELPERS
    // ──────────────────────────────────────────────

    /** Prints the welcome banner at the start of the session. */
    static void printBanner() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("  DecodeLabs  –  Java Project 1          ");
        System.out.println("        N U M B E R   G A M E            ");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("  Guess the secret number (1–100).        ");
        System.out.println("  Each round gives you " + MAX_ATTEMPTS + " attempts.       ");
        System.out.println("  Score more by using fewer guesses!      ");
        System.out.println("════════════════════════════════════════════");
    }

    /** Prints the final session summary with a performance rating. */
    static void printSummary(int rounds, int totalScore) {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("  GAME OVER  –  Session Summary");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.printf("  Rounds played  : %d%n", rounds);
        System.out.printf("  Total score    : %d pts%n", totalScore);

        // Performance rating based on average score per round
        int average = (rounds > 0) ? totalScore / rounds : 0;
        String rating;
        if      (average >= 90) rating = "🌟 LEGENDARY – Near-perfect play!";
        else if (average >= 70) rating = "🥇 EXCELLENT – Really sharp!";
        else if (average >= 50) rating = "🥈 GOOD      – Solid effort!";
        else if (average >= 20) rating = "🥉 FAIR      – Keep practising!";
        else                    rating = "💪 KEEP GOING – You'll get there!";

        System.out.println("  Rating         : " + rating);
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("  Thanks for playing. Happy coding! 🚀    ");
        System.out.println("════════════════════════════════════════════\n");
    }
}