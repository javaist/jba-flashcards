package flashcards;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static HashMap<String, String> setup = new HashMap<>();
    private static HashMap<String, Card> cards = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String term;
        String definition;
        String userInput;
        System.out.println("Input the number of cards:");
        int size = Integer.parseInt(scanner.nextLine());
        String[] order = new String[size];
        for (int i = 0; i < size; i++) {
            boolean trigger = true;
            System.out.println("The card #" + (setup.size() + 1) + ":");
            do {
                term = scanner.nextLine().toLowerCase();
                if (setup.containsKey(term)) {
                    System.out.println("The card \"" + term + "\" already exists. Try again:");
                } else {
                    trigger = false;
                }
            } while (trigger);
            trigger = true;
            System.out.println("The definition of the card #" + (setup.size() + 1) + ":");
            do {
                definition = scanner.nextLine().toLowerCase();
                if (setup.containsValue(definition)) {
                    System.out.println("The definition \"" + definition + "\" already exists. Try again:");
                } else {
                    trigger = false;
                }
            } while (trigger);
            setup.put(term, definition);
            cards.put(definition, new Card(term, definition));
            order[i] = definition;
        }

        Card card;
        for (String key : order) {
            card = cards.get(key);
            card.print();
            userInput = scanner.nextLine().toLowerCase();
            if (card.check(userInput)) {
                System.out.println("Correct!");
            } else if (cards.containsKey(userInput)) {
                System.out.println("Wrong. The right answer is \"" +
                        card.getDefinition() + "\", but your definition is correct for \"" +
                        cards.get(userInput).getTerm() + "\".");

            } else {
                System.out.println("Wrong. The right answer is \"" + card.getDefinition() + "\".");
            }
        }
    }
}
