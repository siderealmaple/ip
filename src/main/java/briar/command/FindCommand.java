package briar.command;

import briar.exception.BriarException;
import briar.task.Task;
import briar.task.TaskList;
import briar.ui.Storage;
import briar.ui.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BriarException {
        String taskListString = tasks.find(keyword);
        ui.showFoundTasks(taskListString);
    }

    @Override
    public String toString() {
        return "Find: " + keyword;
    }
}
