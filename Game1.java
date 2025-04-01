// Mehmet Sezer & Mateen Rashid 12-06-24
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game1 {

    private static int counter = 0;
    private static int menuInput;
    private static int wordLength;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                menu(scanner);
            }
        }
    }

    public static void menu(Scanner scanner) {
        // Create new random object used to find a random word from the list
        Random rand = new Random();
        // List to add words from file
        ArrayList<String> wordsArray = new ArrayList<>();
        // Try to find words list file then create a scanner to run through and add each line to list, if file not found then print error
        try {
            File dataBase = new File("dataBase.txt");
            Scanner wordReader = new Scanner(dataBase);
            while (wordReader.hasNextLine()) {
                wordsArray.add(wordReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        // Total number of words from file
        int wordsArrayLength = wordsArray.size();

        // Set word to be from a random word in the list
        String dataBaseWord = wordsArray.get(rand.nextInt(wordsArrayLength));
        // Create object from word class
        Word word = new Word(dataBaseWord); // Change "example" to your desired word
        // Set word length equal to the length of word object
        wordLength = word.getAnswer().length();

        // Scramble the word and set it as current
        String scrambledWord = word.scramble();
        word.setCurrent(scrambledWord);

        // Display the initial scrambled word
        System.out.println("Scrambled word: " + scrambledWord);
        System.out.println("\nEnter 1 to swap letters.\nEnter 2 to solve.\nEnter 3 to quit.");
        menuInput = checkMenuInput(scanner);

        switch (menuInput) {
            case 1: // Swap letters
                System.out.println("Enter the indices separated by spaces: ");
                String updatedWord = swapLetters(word.getCurrent(), scanner);
                word.setCurrent(updatedWord);
                System.out.println("Current word: " + word.getCurrent());
                if (word.getCurrent().equals(word.getAnswer())) {
                    System.out.println("Congratulations! You unscrambled the word \"" + word.getAnswer() + "\" in " + counter + " steps.\n");
                    menu(scanner);
                }
                break;

            case 2: // Solve
                System.out.println("The original word is: " + word.getAnswer() + "\n");
                menu(scanner);
                break;

            case 3: // Quit
                System.out.println("Goodbye!");
                System.exit(0);
                break;

            default:
                System.out.println("Invalid input. Please try again.");
        }
    }

    // Function to check menu input
    public static int checkMenuInput(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (input >= 1 && input <= 3) {
                    return input;
                }
            }
            System.out.println("Invalid input. Please enter 1, 2, or 3.");
            scanner.nextLine(); // Clear invalid input
        }
    }

    // Function to swap letters
    public static String swapLetters(String currentWord, Scanner scanner) {
        while (true) {
            try {
                int index1 = scanner.nextInt();
                int index2 = scanner.nextInt();

                if (index1 < 0 || index1 >= wordLength || index2 < 0 || index2 >= wordLength) {
                    System.out.println("Invalid indices. Please try again.");
                    continue;
                }

                char[] letters = currentWord.toCharArray();
                char temp = letters[index1];
                letters[index1] = letters[index2];
                letters[index2] = temp;

                counter++; // Increment the counter
                return new String(letters);
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter valid indices.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
}
