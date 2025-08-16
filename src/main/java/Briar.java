import java.util.Scanner;
import java.util.ArrayList;

public class Briar {
    private Scanner scanner;

    private ArrayList<String> texts;

    private Briar() {
        scanner = new Scanner(System.in);
        texts = new ArrayList<String>(100);
    }

    public static void main(String[] args) {
        Briar briar = new Briar();
        briar.greet();
        briar.listen();
        briar.exit();
    }

    private void greet() {
        System.out.println("Nice to meet you! I'm hungry... I mean Briar!");
        System.out.println("What can I do for you?");
    }

    private void listen() {
        String command = "";
        while (true) {
            command = scanner.nextLine();
            if (command.equals("bye")) {
                break;
            } else {
                this.processCommand(command);
            }
        }
    }

    private void processCommand(String command) {
        switch(command) {
            case "list":
                this.list();
                break;
            default:
                this.add(command);
                break;
        }
    }

    private void list() {
        for (int i = 0; i < texts.size(); ++i) {
            System.out.println((i + 1) + ". " + texts.get(i));
        }
    }

    private void add(String text) {
        texts.add(text);
        System.out.println("added: " + text);
    }

    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
