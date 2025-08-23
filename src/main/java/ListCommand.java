public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BriarException {
        ui.showTaskList(tasks);
    }
}
