public class UnmarkCommand extends Command {
    int taskNumber;

    public UnmarkCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BriarException {
        String taskString = tasks.unmark(taskNumber);
        ui.showUnmarkedTask(taskString);
        storage.write(tasks.taskToTextString());
    }
}
