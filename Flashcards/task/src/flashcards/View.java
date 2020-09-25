package flashcards;

import java.io.FileNotFoundException;
import java.util.Objects;

public class View {
    private final CardCollection cards = new CardCollection();


    void add() {
        Log.println("The card:");
        String term = Log.nextLine();
        if (cards.have(term) || Objects.equals("", term)) {
            Log.printf("The card \"%s\" already exists\n", term);
            return;
        }
        Log.println("The definition of the card:");
        String definition = Log.nextLine();
        if (cards.have(definition, true)  || Objects.equals("", definition)) {
            Log.printf("The definition \"%s\" already exists\n", definition);
            return;
        }
        cards.create(term, definition);
        Log.printf("The pair (\"%s\":\"%s\") has been added.\n", term, definition);
    }

    void remove() {
        Log.println("The card:");
        String term = Log.nextLine();
        if (cards.have(term)) {
            cards.remove(term);
            Log.println("The card has been removed.");
        } else {
            Log.printf("Can't remove \"%s\": there is no such card.\n", term);
        }
    }

    void readFromFile() {
        Log.println("File name:");
        String fileName = Log.nextLine();
        try {
            int loads = cards.loadFromFile(fileName);
            Log.printf("%d cards have been loaded.\n", loads);
        } catch (FileNotFoundException e) {
            Log.println("File not found.");
        }

    }

    void writeToFile() {
        Log.println("File name:");
        String fileName = Log.nextLine();
        int saves = cards.saveToFile(fileName);
        Log.printf("%d cards have been saved.\n", saves);
    }

    void ask() {
        Log.println("How many times to ask?");
        int iterations = Integer.parseInt(Log.nextLine());
        Card card;
        String answer;
        for (int i = 0; i < iterations; i++) {
            card = cards.getRandom();
            Log.printf("Print the definition of \"%s\":\n", card.getTerm());
            answer = Log.nextLine();
            if (Objects.equals(answer, card.getDefinition())) {
                Log.println("Correct!");
            } else if (cards.getByDefinition(answer) != null &&
                    Objects.equals(answer, cards.getByDefinition(answer).getDefinition())) {
                Log.printf("Wrong. The right answer is \"%s\", but your definition is correct for \"%s\".\n",
                        card.getDefinition(), cards.getByDefinition(answer).getTerm());
                cards.updateHardest(card);
            } else {
                Log.printf("Wrong. The right answer is \"%s\".\n", card.getDefinition());
                cards.updateHardest(card);
            }
        }
    }

    void log() {
        Log.println("File name:");
        Log.writeToFile(Log.nextLine());
        Log.println("The log has been saved.");
    }

    void hardest() {
        Card[] hardest = cards.getHardest();
        if (hardest.length > 0 && hardest[0].getErrors() == 0) {
            Log.println("There are no cards with errors.");
        } else if (hardest.length == 1) {
            Log.printf("The hardest card is \"%s\". You have %d errors answering it.",
                    hardest[0].getTerm(), hardest[0].getErrors());
        } else if (hardest.length > 1) {
            StringBuilder names = new StringBuilder();
            for (int i = 0; i < hardest.length - 1; i++) {
                names.append(hardest[i].getTerm());
                names.append(", ");
            }
            names.append(hardest[hardest.length - 1].getTerm());
            Log.printf("The hardest cards are %s. You have %d errors answering them.",
                    names.toString(), hardest[0].getErrors());
        } else {
            Log.println("There are no cards with errors.");
        }
    }

    void reset() {
        cards.reset();
        Log.println("Card statistics has been reset.");
    }

    void show(){
        cards.show();
    }
}
