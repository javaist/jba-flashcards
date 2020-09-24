package flashcards;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class CardCollection {
    private final HashMap<String, String> cardsTerm = new HashMap<>();
    private final HashMap<String, String> cardsDef = new HashMap<>();
    private final Random random = new Random();

    void put(@NotNull Card card) {
        cardsDef.remove(cardsTerm.getOrDefault(card.getTerm(), ""));
        cardsTerm.put(card.getTerm(), card.getDefinition());
        cardsDef.put(card.getDefinition(), card.getTerm());
    }

    void put(String term, String definition) {
        cardsDef.remove(cardsTerm.getOrDefault(term, ""));
        cardsTerm.put(term, definition);
        cardsDef.put(definition, term);
    }

    boolean have(@NotNull Card card) {
        return cardsTerm.containsKey(card.getTerm());
    }

    boolean have(String word) {
        return cardsTerm.containsKey(word);
    }

    boolean have(String word, boolean definition) {
        return definition ? cardsTerm.containsValue(word) : cardsTerm.containsKey(word);
    }


    boolean remove(String key) {
        String definition = cardsTerm.remove(key);
        return definition != null && cardsDef.remove(definition) != null;
    }

    boolean remove(@NotNull Card card) {
        String definition = cardsTerm.remove(card.getTerm());
        return definition != null && cardsDef.remove(definition) != null;
    }

    int saveToFile(String fileName) {
        int counter = 0;
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (String term : cardsTerm.keySet()) {
                fileWriter.write(new String(Base64.getEncoder().encode(term.getBytes())) + '\n');
                fileWriter.write(new String(Base64.getEncoder().encode(cardsTerm.get(term).getBytes())) + '\n');
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
                this.put(term, definition);
                counter += 1;
            }
        }
        return counter;
    }

    Card getRandom() {
        Set<String> keys = cardsTerm.keySet();
        String key = String.valueOf(keys.toArray()[random.nextInt(keys.size())]);
        return new Card(key, cardsTerm.get(key));
    }

    Card getByDefinition(String definition) {
        String term = cardsDef.get(definition);
        return term == null ? null : new Card(term, definition);
    }

    Card getByTerm(String term) {
        String definition = cardsTerm.get(term);
        return definition == null ? null : new Card(definition, term);
    }

    void show() {
        System.out.println(cardsTerm.toString());
        System.out.println(cardsDef.toString());
    }

}
