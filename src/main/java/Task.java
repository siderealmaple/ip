public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        String str = "[";
        if (isDone) {
            str += "X";
        } else {
            str += " ";
        }
        str += "] " + description;
        return str;
    }
}
