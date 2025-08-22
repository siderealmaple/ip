import java.io.FileNotFoundException;
import java.io.FilePermission;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Briar {
    private Scanner scanner;

    private ArrayList<Task> tasks;

    private String filePath;

    private Briar(String filePath) {
        this.filePath = filePath;
        scanner = new Scanner(System.in);
        try {
            String taskString = FileHandler.readFromFile(filePath);
            System.out.println(taskString);
            tasks = taskParser(taskString);
        } catch (FileNotFoundException exception) {
            tasks = new ArrayList<Task>(100);
        }
    }

    public static void main(String[] args) {
        Briar briar = new Briar("./data/Briar.txt");
        briar.greet();
        briar.listen();
        briar.exit();
    }

    private static ArrayList<Task> taskParser(String taskString) {
        String[] splitTaskStrings = taskString.split("\n");
        ArrayList<Task> tasks = new ArrayList<Task>();
        for (int i = 0; i < splitTaskStrings.length; ++i) {
            String[] splitStrings = splitTaskStrings[i].split("\\|");
            switch (splitStrings[0]) {
            case "T":
                Task toDoTask = new Todo(splitStrings[2]);
                boolean isToDoDone = Integer.parseInt(splitStrings[1]) != 0;
                toDoTask.setDone(isToDoDone);
                tasks.add(toDoTask);
                break;
            case "D":
                Task deadlineTask = new Deadline(splitStrings[2], splitStrings[3]);
                boolean isDeadlineDone = Integer.parseInt(splitStrings[1]) != 0;
                deadlineTask.setDone(isDeadlineDone);
                tasks.add(deadlineTask);
                break;
            case "E":
                Task eventTask = new Event(splitStrings[2], splitStrings[3], splitStrings[4]);
                boolean isEventDone = Integer.parseInt(splitStrings[1]) != 0;
                eventTask.setDone(isEventDone);
                tasks.add(eventTask);
                break;
            default:
            }
        }
        return tasks;
    }

    private static String taskToString(ArrayList<Task> tasks) {
        String str = "";
        for (Task task : tasks) {
            str += task.toText() + System.lineSeparator();
        }
        return str;
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
                this.delete(Integer.parseInt(input.substring(spaceIndex + 1)));
                break;
            default:
                throw new InvalidCommandException();
            }
            try {
                FileHandler.writeToFile(filePath, taskToString(tasks));
            } catch (IOException exception) {

            }
        } catch (BriarException exception) {
            System.out.println(exception.getMessage());
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Aww, that's not in your list! >.<");
        } catch (NumberFormatException exception) {
            System.out.println("Aww, " + command + " only takes in a number! >.<");
        }
    }

    private void list() {
        System.out.println("Here's your task list:");
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    private void add(Task.TaskType taskType, String command) throws BriarException{
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
