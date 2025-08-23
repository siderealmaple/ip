package briar.command;

import briar.task.TaskList;
import briar.ui.Ui;
import briar.ui.Storage;
import briar.exception.BriarException;

public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BriarException {

    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String toString() {
        return "Exit";
    }
}
