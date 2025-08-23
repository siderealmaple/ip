package briar.ui;

import java.util.Scanner;
import briar.task.Task;
import briar.task.TaskList;

/**
 * Represents the user interface between the chatbot and the user.
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays welcome message.
     */
    public void showWelcome() {
        System.out.println("Nice to meet you! I'm hungry... I mean Briar!");
        System.out.println("What can I do for you?");
    }

    /**
     * Reads next command that user inputs.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays error message.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Displays number of tasks in the list.
     */
    public void showTaskNumber(TaskList tasks) {
        System.out.println("You now have " + tasks.getSize() + " tasks in the list!");
    }

    /**
     * Displays task list.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("Here's your task list:" + System.lineSeparator() + tasks.toString());
    }

    /**
     * Displays task that was added.
     * @param task Task to be displayed.
     */
    public void showAddedTask(Task task) {
        System.out.println("Okie! I've added this task:" + System.lineSeparator() + task);
    }

    /**
     * Displays task that was deleted.
     * @param taskString String representation of task that was deleted.
     */
    public void showDeletedTask(String taskString) {
        System.out.println("Okie! I've removed this task:" + System.lineSeparator() + taskString);
    }

    /**
     * Displays task that was marked as done.
     * @param taskString String representation of task that was marked.
     */
    public void showMarkedTask(String taskString) {
        System.out.println("Nice! I've marked this task as done:" + System.lineSeparator() + taskString);
    }

    /**
     * Displays task that was marked as undone.
     * @param taskString String representation of task that was unmarked.
     */
    public void showUnmarkedTask(String taskString) {
        System.out.println("OK, I've marked this task as not done yet:" + System.lineSeparator() + taskString);
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
