package briar.command;

import briar.task.TaskList;
import briar.ui.Ui;
import briar.ui.Storage;
import briar.exception.BriarException;

/**
 * Represents a command that lists all Tasks from a TaskList.
 */
public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    /**
     * Executes the command to list all tasks in the task list.
     * @param tasks Task list of the chatbot.
     * @param ui Ui used by the chatbot to display.
     * @param storage Storage used to save and load the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BriarException {
        ui.showTaskList(tasks);
    }

    /**
     * Returns the string representation of the command.
     */
    @Override
    public String toString() {
        return "List";
    }
}
