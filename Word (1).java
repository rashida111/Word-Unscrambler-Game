// Mehmet Sezer & Mateen Rashid 12-06-24

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Word {
    // Variables for answer and current state of the word
    private String answer;
    private String current;

    // Default constructor
    public Word(String answer) {
        this.answer = answer;
    }

    // Getter for answer
    public String getAnswer() {
        return this.answer;
    }

    // Getter for current
    public String getCurrent() {
        return current;
    }

    // Setter for current
    public void setCurrent(String current) {
        this.current = current;
    }

    // Function to scarmble word
    public String scramble() {
        // Add characters to array then loop through the word to add characters to list
        List<Character> character = new ArrayList<>();
        for (char c : answer.toCharArray()) {
            character.add(c);
        }
        // Using collections class to shuffle the list
        Collections.shuffle(character);
        // String builder turns the shuffled characters into a string by going through the for loop
        StringBuilder shuffledWord = new StringBuilder();
        for (char c : character) {
            shuffledWord.append(c);
        }
        // Setting current equal to the shuffled word
        current = shuffledWord.toString();
        return current;

    }


}