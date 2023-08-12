package exercise;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

class App {

    public static void main(String[] args) {

    }

    public static CompletableFuture<String> unionFiles(String file1, String file2, String result) {
        CompletableFuture<String> textOfFile1 = CompletableFuture.supplyAsync(() -> {
                    try {
                        return Files.readString(Path.of(file1));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .exceptionally(e -> {
                    System.out.println(e.getMessage());
                    return null;
                });

        CompletableFuture<String> textOfFile2 = CompletableFuture.supplyAsync(() -> {
                    try {
                        return Files.readString(Path.of(file2));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .exceptionally(e -> {
                    System.out.println(e.getMessage());
                    return null;
                });

        return textOfFile1.thenCombine(textOfFile2, (text1, text2) -> {
            String resultText = text1 + text2;
            byte[] bytes = resultText.getBytes();
            try {
                Files.write(Path.of(result), bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return resultText;
        });
    }
}

