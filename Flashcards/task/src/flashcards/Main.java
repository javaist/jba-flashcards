package flashcards;

import java.util.Objects;

public class Main {


    public static void main(String[] args) {
        View view = new View();
        String exportFileName = null;

        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if ("-import".equals(args[i])) {
                    view.readFromFile(args[i + 1]);
                    i++;
                }
                if ("-export".equals(args[i])) {
                    exportFileName = args[i+ 1];
                    i++;
                }
            }
        }

        // Building first line
        String action;
        String[] actions = new String[]{
                "add", "remove", "import", "export", "ask",
                "log", "hardest card", "reset stats",
                "exit"
        };
        StringBuilder menu = new StringBuilder("Input the action (");
        for (int i = 0; i < actions.length - 1; i++) {
            menu.append(actions[i]);
            menu.append(", ");
        }
        menu.append(actions[actions.length-1]);
        menu.append("):");
        // Building first line finished
        do {
            Log.println(menu.toString());
//            System.out.println(menu.toString());
            action = Log.nextLine();
            switch (action) {
                case "exit":
                    Log.println("Bye bye!");
                    if (exportFileName != null) {
                        view.writeToFile(exportFileName);
                    }
                    return;
                case "add":
                    view.add();
                    break;
                case "remove":
                    view.remove();
                    break;
                case "import":
                    view.readFromFile();
                    break;
                case "export":
                    view.writeToFile();
                    break;
                case "ask":
                    view.ask();
                    break;
                case "log":
                    view.log();
                    break;
                case "hardest card":
                    view.hardest();
                    break;
                case "reset stats":
                    view.reset();
                    break;
                case "show":
                    view.show();
                    break;
                default:
                    break;
            }
            System.out.println();
        } while (!Objects.equals("exit", action));
    }
}
