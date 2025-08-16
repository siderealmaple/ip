public class Briar {

    private Briar() {

    }

    public static void main(String[] args) {
        Briar briar = new Briar();
        briar.greet();
        briar.exit();
    }

    private void greet() {
        System.out.println("Nice to meet you! I'm hungry... I mean Briar!");
        System.out.println("What can I do for you?");
    }

    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
