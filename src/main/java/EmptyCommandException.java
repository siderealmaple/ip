public class EmptyCommandException extends BriarException {

    public EmptyCommandException(String command) {
        super("Aww, the description of " + command + " cannot be empty! >.<");
    }
}
