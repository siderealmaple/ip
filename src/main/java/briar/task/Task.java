package briar.task;

import briar.exception.BriarException;
import briar.exception.WrongFormatException;

/**
 * Represents a task that can be stored in a TaskList.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Represents the type of Task a task is.
     */
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a task of the specified type with the given command.
     * @param taskType Type of the task.
     * @param command Details of the task.
     * @return Task of the specified type with the details in command.
     * @throws WrongFormatException If the formatting in command is wrong.
     */
    public static Task createTask(TaskType taskType, String command) throws WrongFormatException {
        String[] splitCommand;
        switch (taskType) {
        case TODO:
            return new Todo(command);
            // No break as there is a return statement
        case DEADLINE:
            if (!command.contains("/by")) {
                throw new WrongFormatException();
            }
            splitCommand = command.split("/by");
            return new Deadline(splitCommand[0].substring(0, splitCommand[0].length() - 1), splitCommand[1].substring(1));
            // No break as there is a return statement
        case EVENT:
            if (!command.contains("/from") || !command.contains("/to")) {
                throw new WrongFormatException();
            }
            splitCommand = command.split("/from");
            String[] fromToString = splitCommand[1].substring(1).split("/to");
            return new Event(splitCommand[0].substring(0, splitCommand[0].length() - 1), fromToString[0].substring(0, fromToString[0].length() - 1), fromToString[1].substring(1));
            // No break as there is a return statement
        }
        return null;
    }

    /**
     * Returns the isDone status of the task as a string.
     */
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

    /**
     * Returns the isDone status of the task as a string to be written into a file as text.
     */
    public String toText() {
        String str = "";
        if (isDone) {
            str += "1";
        } else {
            str += "0";
        }
        str += "|" + description;
        return str;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
}
