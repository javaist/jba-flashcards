package flashcards;

public class Card {
    private final String question;
    private final String definition;

    public Card(String question, String definition) {
        this.question = question;
        this.definition = definition;
    }

    public String getQuestion() {
        return question;
    }

    public String getDefinition() {
        return definition;
    }
}
