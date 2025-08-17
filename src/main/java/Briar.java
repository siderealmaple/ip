import java.util.Scanner;
import java.util.ArrayList;

public class Briar {
    private Scanner scanner;

    private ArrayList<Task> tasks;

    private Briar() {
        scanner = new Scanner(System.in);
        tasks = new ArrayList<Task>(100);
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

    private void processCommand(String input) {
        int spaceIndex = input.indexOf(" ");
        String command = input;
        if (spaceIndex != -1) {
            command = input.substring(0, spaceIndex);
        }
        switch(command) {
            case "list":
                this.list();
                break;
            case "mark":
                this.mark(Integer.parseInt(input.substring(spaceIndex + 1)));
                break;
            case "unmark":
                this.unmark(Integer.parseInt(input.substring(spaceIndex + 1)));
                break;
            case "todo":
                this.add(0, input.substring(spaceIndex));
                break;
            default:

                break;
        }
    }

    private void list() {
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    private void add(int taskType, String command) {
        Task task = null;
        switch (taskType) {
            case 0:
                task = new Todo(command);
                tasks.add(task);
                break;
        }
        System.out.println("added: " + task);
    }

    private void mark(int taskNumber) {
        Task selectedTask = tasks.get(taskNumber - 1);
        selectedTask.setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(selectedTask.toString());
    }

    private void unmark(int taskNumber) {
        Task selectedTask = tasks.get(taskNumber - 1);
        selectedTask.setDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(selectedTask.toString());
    }

    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
