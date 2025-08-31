package briar.command;

import briar.exception.BriarException;
import briar.task.TaskList;
import briar.ui.Storage;
import briar.ui.Ui;

/**
 * Represents a command that finds tasks with the input keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a command to find tasks with descriptions that contain a keyword.
     * @param keyword String to look for in task descriptions.
     */
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
