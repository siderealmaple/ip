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
        try {
            switch (command) {
            case "list":
                this.list();
                break;
            case "mark":
                if (spaceIndex == -1) {
                    throw new EmptyCommandException(command);
                }
                this.mark(Integer.parseInt(input.substring(spaceIndex + 1)));
                break;
            case "unmark":
                if (spaceIndex == -1) {
                    throw new EmptyCommandException(command);
                }
                this.unmark(Integer.parseInt(input.substring(spaceIndex + 1)));
                break;
            case "todo":
                if (spaceIndex == -1) {
                    throw new EmptyCommandException(command);
                }
                this.add(0, input.substring(spaceIndex));
                break;
            case "deadline":
                if (spaceIndex == -1) {
                    throw new EmptyCommandException(command);
                }
                this.add(1, input.substring(spaceIndex));
                break;
            case "event":
                if (spaceIndex == -1) {
                    throw new EmptyCommandException(command);
                }
                this.add(2, input.substring(spaceIndex));
                break;
            case "delete":
                if (spaceIndex == -1) {
                    throw new EmptyCommandException(command);
                }
                this.delete(Integer.parseInt(input.substring(spaceIndex + 1)));
                break;
            default:
                throw new InvalidCommandException();
            }
        } catch (BriarException exception) {
            System.out.println(exception.getMessage());
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Aww, that's not in your list! >.<");
        }
    }

    private void list() {
        System.out.println("Here's your task list:");
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    private void add(int taskType, String command) throws BriarException{
        Task task;
        task = Task.createTask(taskType, command);
        tasks.add(task);
        System.out.println("Okie! I've added this task:");
        System.out.println(task);
        System.out.println("You now have " + tasks.size() + " tasks in the list!");
    }

    private void delete(int taskNumber) {
        Task selectedTask = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        System.out.println("Okie! I've removed the task:");
        System.out.println(selectedTask.toString());
        System.out.println("You now have " + tasks.size() + " tasks in the list!");
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
