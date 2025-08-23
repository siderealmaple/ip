public class InvalidDateException extends BriarException {

    public InvalidDateException() {
        super("Aww, wrong date format! Use yyyy-mm-dd instead! >.<");
    }
}
