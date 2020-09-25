package flashcards;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Card implements Comparable<Card> {
    private final String term;
    private final String definition;
    private int errors = 0;

    public Card(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }

    public Card(String term, String definition, int errors) {
        this.term = term;
        this.definition = definition;
        this.errors = errors;
    }

    public String getTerm() {
        return term;
    }

    public String getDefinition() {
        return definition;
    }

    public boolean check(String userInput) {
        return definition.equals(userInput);
    }

    @Override
    public String toString() {
        return "Card{" +
                "term='" + term + '\'' +
                ", definition='" + definition + '\'' +
                '}';
    }

    @Override
    public int compareTo(@NotNull Card card) {
        return Integer.compare(card.errors, this.errors);
    }

    public int increaseErrors() {
        errors += 1;
        return errors;
    }

    public void resetErrors() {
        errors = 0;
    }

    public int getErrors() {
        return errors;
    }

    public boolean equals(@NotNull Card card) {
        return Objects.equals(this.term, card.term) && Objects.equals(this.definition, card.definition);
    }
}
