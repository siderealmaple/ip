package briar.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import briar.exception.BriarException;

/**
 * Represents the storage used to load and save to file.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads all data from a file into a string.
     * @return String of all data in the file.
     * @throws BriarException If file cannot be found.
     */
    public String load() throws BriarException {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            String str = "";
            while (scanner.hasNext()) {
                str += scanner.nextLine() + "\n";
            }
            return str;
        } catch (FileNotFoundException exception) {
            throw new BriarException();
        }
    }

    /**
     * Writes all data from a string to a file.
     * @param textToAdd String to be written into the file.
     * @throws BriarException If unable to write to file.
     */
    public void write(String textToAdd) throws BriarException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException exception) {
            throw new BriarException("Aww, unable to write to file! >.<");
        }
    }
}
