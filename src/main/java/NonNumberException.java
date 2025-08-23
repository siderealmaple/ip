public class NonNumberException extends BriarException {

    public NonNumberException(String command) {
        super("Aww, " + command + " only takes in a number! >.<");
    }
}
