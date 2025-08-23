import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

public class Briar {
    private Scanner scanner;

    private TaskList taskList;

    private Storage storage;

    private Briar(String filePath) {
        this.storage = new Storage(filePath);
        scanner = new Scanner(System.in);
        try {
            String taskString = storage.load();
            taskList = new TaskList(taskString);
        } catch (FileNotFoundException exception) {
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        Briar briar = new Briar("./data/Briar.txt");
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
                this.mark(Integer.parseInt(input.substring(spaceIndex + 1)) - 1);
                break;
            case "unmark":
                if (spaceIndex == -1) {
                    throw new EmptyCommandException(command);
                }
                this.unmark(Integer.parseInt(input.substring(spaceIndex + 1)) - 1);
                break;
            case "todo":
                if (spaceIndex == -1) {
                    throw new EmptyCommandException(command);
                }
                this.add(Task.TaskType.TODO, input.substring(spaceIndex + 1));
                break;
            case "deadline":
                if (spaceIndex == -1) {
                    throw new EmptyCommandException(command);
                }
                this.add(Task.TaskType.DEADLINE, input.substring(spaceIndex + 1));
                break;
            case "event":
                if (spaceIndex == -1) {
                    throw new EmptyCommandException(command);
                }
                this.add(Task.TaskType.EVENT, input.substring(spaceIndex + 1));
                break;
            case "delete":
                if (spaceIndex == -1) {
                    throw new EmptyCommandException(command);
                }
                this.delete(Integer.parseInt(input.substring(spaceIndex + 1)) - 1);
                break;
            default:
                throw new InvalidCommandException();
            }
            try {
                storage.write(taskList.taskToTextString());
            } catch (IOException exception) {

            }
        } catch (BriarException exception) {
            System.out.println(exception.getMessage());
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Aww, that's not in your list! >.<");
        } catch (NumberFormatException exception) {
            System.out.println("Aww, " + command + " only takes in a number! >.<");
        } catch (DateTimeParseException exception) {
            System.out.println("Aww, wrong date format! >.<");
        }
    }

    private void list() {
        System.out.println("Here's your task list:");
        System.out.println(taskList.toString());
    }

    private void add(Task.TaskType taskType, String command) throws BriarException{
        Task task;
        task = Task.createTask(taskType, command);
        taskList.add(task);
        System.out.println("Okie! I've added this task:");
        System.out.println(task);
        System.out.println("You now have " + taskList.getSize() + " tasks in the list!");
    }

    private void delete(int taskNumber) {
        String selectedTask = taskList.getTaskString(taskNumber);
        taskList.delete(taskNumber);
        System.out.println("Okie! I've removed the task:");
        System.out.println(selectedTask);
        System.out.println("You now have " + taskList.getSize() + " tasks in the list!");
    }

    private void mark(int taskNumber) {
        taskList.mark(taskNumber);
        String selectedTask = taskList.getTaskString(taskNumber);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(selectedTask);
    }

    private void unmark(int taskNumber) {
        taskList.unmark(taskNumber);
        String selectedTask = taskList.getTaskString(taskNumber);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(selectedTask);
    }

    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
