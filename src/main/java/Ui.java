import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Nice to meet you! I'm hungry... I mean Briar!");
        System.out.println("What can I do for you?");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showTaskNumber(TaskList tasks) {
        System.out.println("You now have " + tasks.getSize() + " tasks in the list!");
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
