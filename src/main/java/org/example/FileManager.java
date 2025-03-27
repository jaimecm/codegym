package org.example;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {
    public String readFile(Path filePath) throws IOException {
        return Files.readString(filePath);
    }

    public void writeFile(Path filePath, String content) throws IOException {
        Files.writeString(filePath, content);
    }

    public boolean fileExists(Path filePath) {
        return Files.exists(filePath);
    }
}
