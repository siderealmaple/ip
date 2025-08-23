package briar.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import briar.exception.BriarException;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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

    public void write(String textToAdd) throws BriarException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException exception) {
            throw new BriarException();
        }
    }
}
