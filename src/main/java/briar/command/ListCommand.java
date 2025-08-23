package briar.command;

import briar.task.TaskList;
import briar.ui.Ui;
import briar.ui.Storage;
import briar.exception.BriarException;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BriarException {
        ui.showTaskList(tasks);
    }

    @Override
    public String toString() {
        return "List";
    }
}
