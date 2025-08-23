public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BriarException {

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
