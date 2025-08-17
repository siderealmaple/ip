public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static Task createTask(int taskType, String command) {
        String[] splitCommand;
        switch (taskType) {
        case 0:
            return new Todo(command);
        case 1:
            splitCommand = command.split("/by");
            return new Deadline(splitCommand[0], splitCommand[1]);
        case 2:
            splitCommand = command.split("/from");
            String[] fromToString = splitCommand[1].split("/to");
            return new Event(splitCommand[0], fromToString[0], fromToString[1]);
        }
        return null;
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

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
}
