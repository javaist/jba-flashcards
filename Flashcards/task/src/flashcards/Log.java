package flashcards;

import org.jetbrains.annotations.NotNull;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;

public class Log {
    private static final ArrayList<String> log = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    static String nextLine(){
        String line = scanner.nextLine();
        log.add("> " + line);
        return line;
    }

    static void println(String line){
        log.add(line);
        System.out.println(line);
    }

    static void printf(String line, Object...args) {
        log.add(format(line, args));
        System.out.printf(line, args);
    }

    @NotNull
    private static String format (String format, Object...args) {
        Formatter formatter = new Formatter();
        formatter.format(Locale.getDefault(Locale.Category.FORMAT), format, args);
        return formatter.toString();
    }

    static void writeToFile(String fileName){
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (String term : log) {
                fileWriter.write(term);
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}