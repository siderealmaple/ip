package briar.task;

import java.util.ArrayList;

import briar.exception.BriarException;
import briar.exception.TaskNotInListException;

/**
 * Represents list of tasks.
 */
public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(String taskListString) {
        tasks = stringToTasks(taskListString);
    }

    private static ArrayList<Task> stringToTasks(String taskListString) {
        String[] splitTaskStrings = taskListString.split("\n");
        ArrayList<Task> tasks = new ArrayList<Task>();
        for (int i = 0; i < splitTaskStrings.length; ++i) {
            String[] splitStrings = splitTaskStrings[i].split("\\|");
            switch (splitStrings[0]) {
            case "T":
                Task toDoTask = new Todo(splitStrings[2]);
                boolean isToDoDone = Integer.parseInt(splitStrings[1]) != 0;
                toDoTask.setDone(isToDoDone);
                tasks.add(toDoTask);
                break;
            case "D":
                Task deadlineTask = new Deadline(splitStrings[2], splitStrings[3]);
                boolean isDeadlineDone = Integer.parseInt(splitStrings[1]) != 0;
                deadlineTask.setDone(isDeadlineDone);
                tasks.add(deadlineTask);
                break;
            case "E":
                Task eventTask = new Event(splitStrings[2], splitStrings[3], splitStrings[4]);
                boolean isEventDone = Integer.parseInt(splitStrings[1]) != 0;
                eventTask.setDone(isEventDone);
                tasks.add(eventTask);
                break;
            default:
            }
        }
        return tasks;
    }

    /**
     * Returns the representation of the task to be written into a file as text.
     */
    public String taskToTextString() {
        String str = "";
        for (Task task : tasks) {
            str += task.toText() + System.lineSeparator();
        }
        return str;
    }

    /**
     * Marks a task as done.
     * @param taskNumber Task number of the task to be marked as done.
     * @return String representation of the task marked.
     * @throws TaskNotInListException If taskNumber is not in the task list.
     */
    public String mark(int taskNumber) throws TaskNotInListException {
        try {
            Task task = tasks.get(taskNumber);
            task.setDone(true);
            return task.toString();
        } catch (IndexOutOfBoundsException exception) {
            throw new TaskNotInListException();
        }
    }

    /**
     * Marks a task as undone.
     * @param taskNumber Task number of the task to be marked as undone.
     * @return String representation of the task unmarked.
     * @throws TaskNotInListException If taskNumber is not in the task list.
     */
    public String unmark(int taskNumber) throws TaskNotInListException {
        try {
            Task task = tasks.get(taskNumber);
            task.setDone(false);
            return task.toString();
        } catch (IndexOutOfBoundsException exception) {
            throw new TaskNotInListException();
        }
    }

    /**
     * Adds a task to the task list.
     * @param task Task to be added to the task list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list.
     * @param taskNumber Task number of the task to be deleted.
     * @return String representation of the task deleted.
     * @throws TaskNotInListException If taskNumber is not in the task list.
     */
    public String delete(int taskNumber) throws TaskNotInListException {
        try {
            Task task = tasks.get(taskNumber);
            tasks.remove(taskNumber);
            return task.toString();
        } catch (IndexOutOfBoundsException exception) {
            throw new TaskNotInListException();
        }
    }

    /**
     * Returns the number of tasks in the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the string representation of the task list.
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < tasks.size(); ++i) {
            str +=  (i + 1) + ". " + tasks.get(i).toString() + System.lineSeparator();
        }
        return str;
    }
}
