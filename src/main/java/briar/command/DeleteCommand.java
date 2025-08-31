package briar.command;

import briar.exception.BriarException;
import briar.task.TaskList;
import briar.ui.Storage;
import briar.ui.Ui;

/**
 * Represents a command that deletes a Task from a TaskList.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Creates a command to add the stored task to a task list.
     * @param taskNumber Index of the task to be deleted from the task list.
     */
    public DeleteCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to delete the task with the stored task number to the task list.
     * @param tasks Task list of the chatbot.
     * @param ui Ui used by the chatbot to display.
     * @param storage Storage used to save and load the task list.
     * @throws BriarException If stored task number is not in task list or writing to file fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BriarException {
        String taskString = tasks.delete(taskNumber);
        ui.showDeletedTask(taskString);
        ui.showTaskNumber(tasks);
        storage.write(tasks.taskToTextString());
    }

    /**
     * Returns the string representation of the command.
     */
    @Override
    public String toString() {
        return "Delete task " + taskNumber;
    }
}
