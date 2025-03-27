package org.example;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class MenuManager {
    private static final String RESET_COLOR = "\u001B[0m";
    private static final String BLUE_COLOR = "\u001B[34m";

    private final Scanner scanner;

    public MenuManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayWelcomeMessage() {
        System.out.println(BLUE_COLOR + "\nBienvenido al Cifrado César" + RESET_COLOR);
    }

    public void displayGoodbyeMessage() {
        System.out.println(BLUE_COLOR + "\n¡Hasta pronto!" + RESET_COLOR);
    }

    public int getMenuOption() {
        System.out.println("\nPor favor seleccione una opción:");
        System.out.println("1. Cifrar texto");
        System.out.println("2. Descifrar texto con clave");
        System.out.println("3. Salir");
        System.out.print("Ingrese su opción: ");

        while (!scanner.hasNextInt()) {
            System.out.println("¡Entrada inválida! Debe ingresar un número.");
            scanner.next(); // Limpiar entrada incorrecta
            System.out.print("Ingrese su opción: ");
        }
        return scanner.nextInt();
    }

    public Path getValidInputFile(FileManager fileManager) {
        Path filePath;
        scanner.nextLine(); // Limpiar buffer
        while (true) {
            System.out.print("Ingrese la ruta del archivo: ");
            String input = scanner.nextLine();
            filePath = Paths.get(input);

            if (fileManager.fileExists(filePath)) {
                return filePath;
            }
            System.out.println("¡El archivo no existe! Intente nuevamente.");
        }
    }

    public int getValidKey() {
        System.out.print("Ingrese la clave (número entero): ");
        while (!scanner.hasNextInt()) {
            System.out.println("¡La clave debe ser un número entero!");
            scanner.next(); // Limpiar entrada incorrecta
            System.out.print("Ingrese la clave: ");
        }
        return scanner.nextInt();
    }

    public Path getOutputFilePath() {
        System.out.print("Ingrese la ruta para guardar el resultado: ");
        scanner.nextLine(); // Limpiar buffer
        return Paths.get(scanner.nextLine());
    }
}
