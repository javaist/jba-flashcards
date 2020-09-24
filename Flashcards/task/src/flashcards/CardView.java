package flashcards;

import java.io.FileNotFoundException;
import java.util.Objects;

public class CardView {
    private final CardCollection cards = new CardCollection();


    public void add() {
        System.out.println("The card:");
        String term = Main.scanner.nextLine();
        if (cards.have(term) || Objects.equals("", term)) {
            System.out.printf("The card \"%s\" already exists\n", term);
            return;
        }
        System.out.println("The definition of the card:");
        String definition = Main.scanner.nextLine();
        if (cards.have(definition, true)  || Objects.equals("", definition)) {
            System.out.printf("The definition \"%s\" already exists\n", definition);
            return;
        }
        cards.put(term, definition);
        System.out.printf("The pair (\"%s\":\"%s\") has been added.\n", term, definition);
    }

    public void remove() {
        System.out.println("The card:");
        String term = Main.scanner.nextLine();
        if (cards.remove(term)) {
            cards.remove(term);
            System.out.println("The card has been removed.");
        } else {
            System.out.printf("Can't remove \"%s\": there is no such card.\n", term);
        }
    }

    public void readFromFile() {
        System.out.println("File name:");
        String fileName = Main.scanner.nextLine();
        try {
            int loads = cards.loadFromFile(fileName);
            System.out.printf("%d cards have been loaded.\n", loads);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

    }

    public void writeToFile() {
        System.out.println("File name:");
        String fileName = Main.scanner.nextLine();
        int saves = cards.saveToFile(fileName);
        System.out.printf("%d cards have been saved.\n", saves);
    }

    public void ask() {
        System.out.println("How many times to ask?");
        int iterations = Integer.parseInt(Main.scanner.nextLine());
        Card card;
        String answer;
        for (int i = 0; i < iterations; i++) {
            card = cards.getRandom();
            System.out.printf("Print the definition of \"%s\":\n", card.getTerm());
            answer = Main.scanner.nextLine();
            if (Objects.equals(answer, card.getDefinition())) {
                System.out.println("Correct!");
            } else if (cards.getByDefinition(answer) != null &&
                    Objects.equals(answer, cards.getByDefinition(answer).getDefinition())) {
                System.out.printf("Wrong. The right answer is \"%s\", but your definition is correct for \"%s\".\n",
                        card.getDefinition(), cards.getByDefinition(answer).getTerm());
            } else {
                System.out.printf("Wrong. The right answer is \"%s\".\n", card.getDefinition());
            }
        }
    }

    void show(){
        cards.show();
    }
}
