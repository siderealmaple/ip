package briar.command;

import briar.task.TaskList;
import briar.ui.Ui;
import briar.ui.Storage;
import briar.exception.BriarException;

public class MarkCommand extends Command {
    int taskNumber;

    public MarkCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BriarException {
        String taskString = tasks.mark(taskNumber);
        ui.showMarkedTask(taskString);
        storage.write(tasks.taskToTextString());
    }

    @Override
    public String toString() {
        return "Mark task " + taskNumber;
    }
}
