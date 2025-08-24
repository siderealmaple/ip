package briar.ui;

import java.util.Scanner;
import briar.task.Task;
import briar.task.TaskList;

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

    public void showTaskNumber(TaskList tasks) {
        System.out.println("You now have " + tasks.getSize() + " tasks in the list!");
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("Here's your task list:" + System.lineSeparator() + tasks.toString());
    }

    public void showAddedTask(Task task) {
        System.out.println("Okie! I've added this task:" + System.lineSeparator() + task);
    }

    public void showDeletedTask(String taskString) {
        System.out.println("Okie! I've removed this task:" + System.lineSeparator() + taskString);
    }

    public void showMarkedTask(String taskString) {
        System.out.println("Nice! I've marked this task as done:" + System.lineSeparator() + taskString);
    }

    public void showUnmarkedTask(String taskString) {
        System.out.println("OK, I've marked this task as not done yet:" + System.lineSeparator() + taskString);
    }

    public void showFoundTasks(String taskString) {
        if (taskString.isEmpty()) {
            System.out.println("Oops! No tasks match the keyword given! >.<");
        } else {
            System.out.println("Okie! Here are the tasks that match in your list:" + System.lineSeparator() + taskString);
        }
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
