package briar.exception;

public class TaskNotInListException extends BriarException {

    public TaskNotInListException() {
        super("Aww, that's not in your list! >.<");
    }
}
