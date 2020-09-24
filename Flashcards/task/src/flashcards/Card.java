package flashcards;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Card implements Comparable<Card> {
    private final String term;
    private final String definition;

    public Card(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }

    public String getTerm() {
        return term;
    }

    public String getDefinition() {
        return definition;
    }

    public void print() {
        System.out.println("Print the definition of \"" + this.term + "\":");
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
        return this.toString().compareTo(card.toString());
    }

    public boolean equals(@NotNull Card card) {
        return Objects.equals(this.term, card.term) && Objects.equals(this.definition, card.definition);
    }
}
