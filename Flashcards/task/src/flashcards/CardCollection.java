package flashcards;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class CardCollection {
    private final ArrayList<Card> cards = new ArrayList<>();
    private final HashMap<String, Card> cardsTerm = new HashMap<>();
    private final HashMap<String, Card> cardsDef = new HashMap<>();
    private final Random random = new Random();


    void put(@NotNull Card card) {
        if (cardsTerm.containsKey(card.getTerm())) {
            Card oldCard = cardsTerm.get(card.getTerm());
            cards.remove(oldCard);
            cardsDef.remove(oldCard.getDefinition());
        }
        cards.add(card);
        cardsTerm.put(card.getTerm(), card);
        cardsDef.put(card.getDefinition(), card);
    }

    void create(String term, String definition) {
        this.put(new Card(term, definition));
    }


    boolean have(String word) {
        return cardsTerm.containsKey(word);
    }

//    boolean have(@NotNull Card card) {
//        return this.have(card.getTerm());
//    }

    boolean have(String word, boolean definition) {
        return definition ? cardsDef.containsKey(word) : cardsTerm.containsKey(word);
    }

    void remove(String key) {
        Card card = cardsTerm.remove(key);
        cards.remove(card);
        cardsDef.remove(card.getDefinition());
//        return card != null && cardsDef.remove(card.getDefinition()) != null;
    }

//    boolean remove(@NotNull Card card) {
//        return this.remove(card.getTerm());
//    }

    int saveToFile(String fileName) {
        int counter = 0;
        Card card;
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (String term : cardsTerm.keySet()) {
                card = cardsTerm.get(term);
                fileWriter.write(new String(Base64.getEncoder().encode(card.getTerm().getBytes())) + '\n');
                fileWriter.write(new String(Base64.getEncoder().encode(card.getDefinition().getBytes())) + '\n');
                fileWriter.write(new String(Base64.getEncoder().encode(String.valueOf(card.getErrors()).getBytes())) + '\n');
                counter += 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return counter;
    }

    int loadFromFile(String fileName) throws FileNotFoundException {
        int counter = 0;
        try (Scanner fileReader = new Scanner(new File(fileName))) {
            while (fileReader.hasNext()) {
                String term = new String(Base64.getDecoder().decode(fileReader.nextLine().getBytes()));
                String definition = new String(Base64.getDecoder().decode(fileReader.nextLine().getBytes()));
                int errors = Integer.parseInt(new String(Base64.getDecoder().decode(fileReader.nextLine().getBytes())));
                this.put(new Card(term, definition, errors));
                counter += 1;
            }
        }
        return counter;
    }

    Card getRandom() {
        Set<String> keys = cardsTerm.keySet();
        String key = String.valueOf(keys.toArray()[random.nextInt(keys.size())]);
        return cardsTerm.get(key);
    }

    Card getByDefinition(String definition) {
        return cardsDef.get(definition);
    }

//    Card getByTerm(String term) {
//        return cardsTerm.get(term);
//    }

    void reset() {
        for (String key : cardsTerm.keySet()) {
            cardsTerm.get(key).resetErrors();
        }
    }

    Card[] getHardest() {
        if (cards.isEmpty()) {
            return new Card[0];
        } else {
            cards.sort(Card::compareTo);
            ArrayList<Card> hardest = new ArrayList<>();
            Card first = cards.get(0);
            for (Card error : cards) {
                if (first.getErrors() == error.getErrors()) {
                    hardest.add(error);
                } else {
                    return hardest.toArray(Card[]::new);
                }
            }
            return hardest.toArray(Card[]::new);
        }
    }

    void updateHardest(Card card) {
        card.increaseErrors();
    }

    void show() {
        System.out.println(cardsTerm.toString());
        System.out.println(cardsDef.toString());
    }

}
