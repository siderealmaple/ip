package briar.command;

import briar.task.TaskList;
import briar.ui.Ui;
import briar.ui.Storage;
import briar.exception.BriarException;

/**
 * Represents a command that marks a Task in a TaskList as done.
 */
public class MarkCommand extends Command {
    int taskNumber;

    public MarkCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to mark task with the stored task number as done.
     * @param tasks Task list of the chatbot.
     * @param ui Ui used by the chatbot to display.
     * @param storage Storage used to save and load the task list.
     * @throws BriarException If stored task number is not in task list or writing to file fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BriarException {
        String taskString = tasks.mark(taskNumber);
        ui.showMarkedTask(taskString);
        storage.write(tasks.taskToTextString());
    }

    /**
     * Returns the string representation of the command.
     */
    @Override
    public String toString() {
        return "Mark task " + taskNumber;
    }
}
