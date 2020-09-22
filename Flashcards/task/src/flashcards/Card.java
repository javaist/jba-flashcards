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
        if (definition.equals(userInput)) {
            System.out.println("Correct!");
            return true;
        } else {
            System.out.println("Wrong. The right answer is \"" + this.definition + "\".");
            return false;
        }
    }
}
