import java.util.Scanner;

public class Briar {
    private Scanner scanner;

    private Briar() {
        scanner = new Scanner(System.in);
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
                System.out.println(command);
            }
        }
    }

    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
