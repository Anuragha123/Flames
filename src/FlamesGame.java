import java.util.*;

public class FlamesGame {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}

class Game {
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Welcome to the FLAMES Game!");
        while (true) {
            System.out.print("Enter first name (or type exit to quit): ");
            String name1 = scanner.nextLine().trim().toLowerCase();
            if (name1.equalsIgnoreCase("exit")) break;

            System.out.print("Enter second name (or type exit to quit): ");
            String name2 = scanner.nextLine().trim().toLowerCase();
            if (name2.equalsIgnoreCase("exit")) break;

            Flames flames = new Flames(name1, name2);
            String result = flames.calculateRelationship();
            System.out.println("Relationship result: " + result);
            System.out.println();
        }
    }
}

class Flames {
    private String name1;
    private String name2;

    public Flames(String name1, String name2) {
        this.name1 = name1.replaceAll("\\s+", "");
        this.name2 = name2.replaceAll("\\s+", "");
    }

    public String calculateRelationship() {
        int count = getUniqueCharCount();
        return getFlamesResult(count);
    }

    // Count letters remaining after removing common characters
    private int getUniqueCharCount() {
        StringBuilder s1 = new StringBuilder(name1);
        StringBuilder s2 = new StringBuilder(name2);

        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            int index = s2.indexOf(String.valueOf(c));
            if (index != -1) {
                s1.deleteCharAt(i);
                s2.deleteCharAt(index);
                i--; // Adjust because of removal
            }
        }
        return s1.length() + s2.length();
    }

    // Calculate FLAMES result based on count
    private String getFlamesResult(int count) {
        String flames = "FLAMES";
        StringBuilder flamesBuilder = new StringBuilder(flames);

        int index = 0;
        while (flamesBuilder.length() > 1) {
            index = (count % flamesBuilder.length()) - 1;
            if (index < 0) index = flamesBuilder.length() - 1;
            flamesBuilder.deleteCharAt(index);
            flamesBuilder = new StringBuilder(flamesBuilder.substring(index) + flamesBuilder.substring(0, index));
        }

        char result = flamesBuilder.charAt(0);
        switch (result) {
            case 'F': return "Friends";
            case 'L': return "Love";
            case 'A': return "Affection";
            case 'M': return "Marriage";
            case 'E': return "Enemies";
            case 'S': return "Siblings";
            default: return "Unknown";
        }
    }
}

