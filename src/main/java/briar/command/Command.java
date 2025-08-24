package briar.command;

import briar.task.TaskList;
import briar.ui.Ui;
import briar.ui.Storage;
import briar.exception.BriarException;
/**
 * Represents a command that user can give.
 */
public abstract class Command {

    public Command() {

    }

    /**
     * Executes a command defined by subclasses.
     * @param tasks Task list of the chatbot.
     * @param ui Ui used by the chatbot to display.
     * @param storage Storage used to save and load the task list.
     * @throws BriarException If the command cannot be executed properly.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BriarException;

    /**
     * Returns whether the command is an ExitCommand.
     */
    public boolean isExit() {
        return false;
    }
}
