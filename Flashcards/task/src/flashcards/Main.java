package flashcards;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String term;
        String definition;
        String userInput;
        int size = Integer.parseInt(scanner.nextLine());
        Card[] cards = new Card[size];
        for (int i = 0; i < size; i++) {
            System.out.println("The card #" + (i + 1) + ":");
            term = scanner.nextLine();
            System.out.println("The definition of the card #" + (i + 1) + ":");
            definition = scanner.nextLine();
            cards[i] = new Card(term, definition);
        }
        for (int i = 0; i < size; i++) {
            cards[i].print();
            userInput = scanner.nextLine();
            cards[i].check(userInput);
        }
    }
}
