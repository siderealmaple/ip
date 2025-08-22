public class Deadline extends Task {
    String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + date + ")";
    }

    @Override
    public String toText() {
        return "D|" + super.toText() + "|" + date;
    }
}