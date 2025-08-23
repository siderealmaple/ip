package briar.command;

import briar.task.TaskList;
import briar.ui.Ui;
import briar.ui.Storage;
import briar.exception.BriarException;

public abstract class Command {

    public Command() {

    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BriarException;

    public boolean isExit() {
        return false;
    }
}
