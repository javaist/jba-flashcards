package flashcards;

public class Main {
    public static void main(String[] args) {
        Card card = new Card("Hello!", "Привет!");
        System.out.println("Card:");
        System.out.println(card.getQuestion());
        System.out.println("Definition:");
        System.out.println(card.getDefinition());
    }
}
