package briar.task;

import briar.exception.WrongFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void createTask_todoTask_success() {
        try {
            assertEquals("[T][ ] test todo task", Task.createTask(Task.TaskType.TODO, "test todo task").toString());
        } catch (WrongFormatException e) {

        }
    }

    @Test
    public void createTask_deadlineTask_success() {
        try {
            assertEquals("[D][ ] test todo task (by:Oct 15 2019)", Task.createTask(Task.TaskType.DEADLINE, "test todo task /by 2019-10-15").toString());
        } catch (WrongFormatException e) {

        }
    }

    @Test
    public void createTask_eventTask_success() {
        try {
            assertEquals("[E][ ] test todo task (from:2pm to:4pm)", Task.createTask(Task.TaskType.DEADLINE, "test todo task /from 2pm /to 4pm").toString());
        } catch (WrongFormatException e) {

        }
    }
}
