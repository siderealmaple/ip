package briar.command;

import briar.exception.BriarException;
import briar.task.TaskList;
import briar.ui.Storage;
import briar.ui.Ui;

/**
 * Represents a command that causes the chatbot to exit.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BriarException {

    }

    /**
     * Returns whether the command is an ExitCommand.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Returns the string representation of the command.
     */
    @Override
    public String toString() {
        return "Exit";
    }
}
