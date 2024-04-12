import java.io.*;
import java.util.*;

public class EnumGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Willkommen zum Enum-Generator!");
        System.out.println("Gib den Namen der Enum-Klasse ein:");
        String enumName = scanner.nextLine();
        
        System.out.println("Gib die Namen der Konstanten ein (getrennt durch Leerzeichen):");
        String[] constantNames = scanner.nextLine().split(" ");
        
        System.out.println("Möchtest du die Konstanten mit oder ohne Wert initialisieren? (mit/ohne):");
        String initChoice = scanner.nextLine();
        
        try {
            PrintWriter writer = new PrintWriter(enumName + ".java");
            writer.println("public enum " + enumName + " {");
            for (int i = 0; i < constantNames.length; i++) {
                if (initChoice.equalsIgnoreCase("mit")) {
                    writer.println("    " + constantNames[i] + "(" + i + (i == constantNames.length - 1 ? ");" : "),"));
                } else {
                    writer.println("    " + constantNames[i] + (i == constantNames.length - 1 ? ";" : ","));
                }
            }
            writer.println("\n    private final int value;");
            writer.println("\n    private " + enumName + "(int value) {");
            writer.println("        this.value = value;");
            writer.println("    }");
            writer.println("\n    public int getValue() {");
            writer.println("        return value;");
            writer.println("    }");
            writer.println("\n    public static " + enumName + " getByValue(int value) {");
            writer.println("        for (" + enumName + " constant : values()) {");
            writer.println("            if (constant.value == value) {");
            writer.println("                return constant;");
            writer.println("            }");
            writer.println("        }");
            writer.println("        throw new IllegalArgumentException(\"Ungültiger Wert: \" + value);");
            writer.println("    }");
            writer.println("}");
            writer.close();
            System.out.println("Enum-Klasse wurde erfolgreich als " + enumName + ".java gespeichert.");
        } catch (FileNotFoundException e) {
            System.out.println("Fehler beim Speichern der Datei: " + e.getMessage());
        }
        
        scanner.close();
    }
}
