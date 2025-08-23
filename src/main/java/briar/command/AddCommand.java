package briar.command;

import briar.task.TaskList;
import briar.ui.Ui;
import briar.ui.Storage;
import briar.exception.BriarException;
import briar.task.Task;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task taskToAdd) {
        super();
        task = taskToAdd;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BriarException {
        tasks.add(task);
        ui.showAddedTask(task);
        ui.showTaskNumber(tasks);
        storage.write(tasks.taskToTextString());
    }
}
