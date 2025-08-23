public abstract class Command {

    public Command() {

    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BriarException;

    public boolean isExit() {
        return false;
    }
}
