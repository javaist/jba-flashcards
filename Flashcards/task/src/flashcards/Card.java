package flashcards;

public class Card {
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
}
