public class AddCommand extends Command {
    Task task;

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
