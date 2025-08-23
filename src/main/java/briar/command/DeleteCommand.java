package briar.command;

import briar.task.TaskList;
import briar.ui.Ui;
import briar.ui.Storage;
import briar.exception.BriarException;

public class DeleteCommand extends Command {
    int taskNumber;

    public DeleteCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BriarException {
        String taskString = tasks.delete(taskNumber);
        ui.showDeletedTask(taskString);
        ui.showTaskNumber(tasks);
        storage.write(tasks.taskToTextString());
    }

    @Override
    public String toString() {
        return "Delete task " + taskNumber;
    }
}
