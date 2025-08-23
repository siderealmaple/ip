import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Briar {
    private Ui ui;

    private TaskList tasks;

    private Storage storage;

    private Briar(String filePath) {
        this.storage = new Storage(filePath);
        ui = new Ui();
        try {
            String taskString = storage.load();
            tasks = new TaskList(taskString);
        } catch (FileNotFoundException exception) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        listen();
        ui.showExit();
    }

    public static void main(String[] args) {
        Briar briar = new Briar("./data/Briar.txt");
        briar.run();
    }

    private void listen() {
        String command = "";
        while (true) {
            command = ui.readCommand();
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
                storage.write(tasks.taskToTextString());
            } catch (IOException exception) {

            }
        } catch (BriarException exception) {
            ui.showError(exception.getMessage());
        } catch (IndexOutOfBoundsException exception) {
            ui.showError("Aww, that's not in your list! >.<");
        } catch (NumberFormatException exception) {
            ui.showError("Aww, " + command + " only takes in a number! >.<");
        } catch (DateTimeParseException exception) {
            ui.showError("Aww, wrong date format! >.<");
        }
    }

    private void list() {
        ui.showMessage("Here's your task list:");
        ui.showMessage(tasks.toString());
    }

    private void add(Task.TaskType taskType, String command) throws BriarException{
        Task task;
        task = Task.createTask(taskType, command);
        tasks.add(task);
        ui.showMessage("Okie! I've added this task:");
        ui.showMessage(task.toString());
        ui.showTaskNumber(tasks);
    }

    private void delete(int taskNumber) {
        String selectedTask = tasks.getTaskString(taskNumber);
        tasks.delete(taskNumber);
        ui.showMessage("Okie! I've removed the task:");
        ui.showMessage(selectedTask);
        ui.showTaskNumber(tasks);
    }

    private void mark(int taskNumber) {
        tasks.mark(taskNumber);
        String selectedTask = tasks.getTaskString(taskNumber);
        ui.showMessage("Nice! I've marked this task as done:");
        ui.showMessage(selectedTask);
    }

    private void unmark(int taskNumber) {
        tasks.unmark(taskNumber);
        String selectedTask = tasks.getTaskString(taskNumber);
        ui.showMessage("OK, I've marked this task as not done yet:");
        ui.showMessage(selectedTask);
    }
}
