package briar.command;

import briar.task.TaskList;
import briar.ui.Ui;
import briar.ui.Storage;
import briar.exception.BriarException;
import briar.task.Task;

/**
 * Represents a command that adds a Task into a TaskList.
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task taskToAdd) {
        super();
        task = taskToAdd;
    }

    /**
     * Executes the command to add the stored task to the task list.
     * @param tasks Task list of the chatbot.
     * @param ui Ui used by the chatbot to display.
     * @param storage Storage used to save and load the task list.
     * @throws BriarException If writing to file fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BriarException {
        tasks.add(task);
        ui.showAddedTask(task);
        ui.showTaskNumber(tasks);
        storage.write(tasks.taskToTextString());
    }

    /**
     * Returns the string representation of the command.
     */
    @Override
    public String toString() {
        return "Add: " + task;
    }
}
