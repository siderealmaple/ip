public abstract class Task {
    private String description;
    private boolean isDone;

    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static Task createTask(TaskType taskType, String command) throws WrongFormatException {
        String[] splitCommand;
        switch (taskType) {
        case TODO:
            return new Todo(command);
        case DEADLINE:
            if (!command.contains("/by")) {
                throw new WrongFormatException();
            }
            splitCommand = command.split("/by");
            return new Deadline(splitCommand[0], splitCommand[1]);
        case EVENT:
            if (!command.contains("/from") || !command.contains("/to")) {
                throw new WrongFormatException();
            }
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
