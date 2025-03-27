package org.example;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.IOException;

public class App {
    private static final String RESET_COLOR = "\u001B[0m";
    private static final String BLUE_COLOR = "\u001B[34m";
    private final Scanner scanner;
    private final FileManager fileManager;
    private final CipherProcessor cipherProcessor;

    public App() {
        this.scanner = new Scanner(System.in);
        this.fileManager = new FileManager();
        this.cipherProcessor = new CipherProcessor();
    }

    public void run() {
        displayWelcomeMessage();
        boolean shouldContinue = true;
        while (shouldContinue) {
            displayMenu();
            shouldContinue = processOption(getMenuOption());
        }
        displayGoodbyeMessage();
        scanner.close();
    }

    private void displayWelcomeMessage() {
        System.out.println(BLUE_COLOR + "\nBienvenido al Cifrado César" + RESET_COLOR);
    }

    private void displayGoodbyeMessage() {
        System.out.println(BLUE_COLOR + "\n¡Hasta pronto!" + RESET_COLOR);
    }

    private void displayMenu() {
        System.out.println("\nPor favor seleccione una opción:");
        System.out.println("1. Cifrar texto");
        System.out.println("2. Descifrar texto con clave");
        System.out.println("3. Salir");
        System.out.print("Ingrese su opción: ");
    }

    private int getMenuOption() {
        while (!scanner.hasNextInt()) {
            System.out.println("¡Entrada inválida! Debe ingresar un número.");
            scanner.next();
            System.out.print("Ingrese su opción: ");
        }
        return scanner.nextInt();
    }

    private boolean processOption(int option) {
        switch (option) {
            case 1 -> handleEncryption();
            case 2 -> handleDecryption();
            case 3 -> { return false; }
            default -> System.out.println("Opción no válida. Intente nuevamente.");
        }
        return true;
    }

    private void handleEncryption() {
        Path inputFile = getValidInputFile();
        int key = getValidKey();
        Path outputFile = getOutputFilePath();
        try {
            String content = fileManager.readFile(inputFile);
            String encryptedContent = cipherProcessor.encrypt(content, key);
            fileManager.writeFile(outputFile, encryptedContent);
            System.out.println("¡Archivo cifrado creado con éxito en: " + outputFile);
        } catch (IOException e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
        }
    }

    private void handleDecryption() {
        Path inputFile = getValidInputFile();
        int key = getValidKey();
        Path outputFile = getOutputFilePath();
        try {
            String content = fileManager.readFile(inputFile);
            String decryptedContent = cipherProcessor.decrypt(content, key);
            fileManager.writeFile(outputFile, decryptedContent);
            System.out.println("¡Archivo descifrado creado con éxito en: " + outputFile);
        } catch (IOException e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
        }
    }

    private Path getValidInputFile() {
        System.out.print("Ingrese la ruta del archivo: ");
        scanner.nextLine();
        return Paths.get(scanner.nextLine());
    }

    private int getValidKey() {
        System.out.print("Ingrese la clave (número entero): ");
        while (!scanner.hasNextInt()) {
            System.out.println("¡La clave debe ser un número entero!");
            scanner.next();
            System.out.print("Ingrese la clave: ");
        }
        return scanner.nextInt();
    }

    private Path getOutputFilePath() {
        System.out.print("Ingrese la ruta para guardar el resultado: ");
        scanner.nextLine();
        return Paths.get(scanner.nextLine());
    }
}
