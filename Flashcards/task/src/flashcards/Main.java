package flashcards;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CardView cards = new CardView();
        String action;
        do {
            System.out.println("Input the action (add, remove, import, export, ask, exit):");
            action = scanner.nextLine();
            switch (action) {
                case "exit":
                    System.out.println("Bye bye!");
                    return;
                case "add":
                    cards.add();
                    break;
                case "remove":
                    cards.remove();
                    break;
                case "import":
                    cards.readFromFile();
                    break;
                case "export":
                    cards.writeToFile();
                    break;
                case "ask":
                    cards.ask();
                    break;
                case "show":
                    cards.show();
                    break;
                default:
                    break;
            }
            System.out.println();
        } while (!Objects.equals("exit", action));
    }
}
