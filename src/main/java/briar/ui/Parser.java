package briar.ui;

import java.time.format.DateTimeParseException;

import briar.command.*;
import briar.task.Task;
import briar.exception.BriarException;
import briar.exception.EmptyCommandException;
import briar.exception.NonNumberException;
import briar.exception.InvalidDateException;
import briar.exception.InvalidCommandException;

/**
 * Represents the parser to parse strings into commands for the chatbot Briar.
 */
public class Parser {

    /**
     * Converts the string command into a Command to be executed.
     * @param command String to be parsed and interpreted by the parser.
     * @return Command to be executed by the chatbot Briar.
     * @throws BriarException If the command cannot be parsed properly.
     */
    public static Command parse(String command) throws BriarException {
        String[] splitCommand = command.split(" ");
        switch (splitCommand[0]) {
        case "bye":
            return new ExitCommand();
            // No break as code either returns
        case "list":
            return new ListCommand();
            // No break as code either returns
        case "mark":
            if (splitCommand.length <= 1) {
                throw new EmptyCommandException(command);
            }
            try {
                return new MarkCommand(Integer.parseInt(splitCommand[1]) - 1);
            } catch (NumberFormatException exception) {
                throw new NonNumberException(splitCommand[0]);
            }
            // No break as code either returns or throws exception
        case "unmark":
            if (splitCommand.length <= 1) {
                throw new EmptyCommandException(command);
            }
            try {
                return new UnmarkCommand(Integer.parseInt(splitCommand[1]) - 1);
            } catch (NumberFormatException exception) {
                throw new NonNumberException(splitCommand[0]);
            }
            // No break as code either returns or throws exception
        case "todo":
            if (splitCommand.length <= 1) {
                throw new EmptyCommandException(command);
            }
            return new AddCommand(Task.createTask(Task.TaskType.TODO, command.substring(splitCommand[0].length() + 1)));
            // No break as code either returns or throws exception
        case "deadline":
            if (splitCommand.length <= 2) {
                throw new EmptyCommandException(command);
            }
            try {
                return new AddCommand(Task.createTask(Task.TaskType.DEADLINE, command.substring(splitCommand[0].length() + 1)));
            } catch (DateTimeParseException exception) {
                throw new InvalidDateException();
            }
            // No break as code either returns or throws exception
        case "event":
            if (splitCommand.length <= 3) {
                throw new EmptyCommandException(command);
            }
            return new AddCommand(Task.createTask(Task.TaskType.EVENT, command.substring(splitCommand[0].length() + 1)));
            // No break as code either returns or throws exception
        case "delete":
            if (splitCommand.length <= 1) {
                throw new EmptyCommandException(command);
            }
            try {
                return new DeleteCommand(Integer.parseInt(splitCommand[1]) - 1);
            } catch (NumberFormatException exception) {
                throw new NonNumberException(splitCommand[0]);
            }
            // No break as code either returns or throws exception
        case "find":
            if (splitCommand.length <= 1) {
                throw new EmptyCommandException(command);
            }
            return new FindCommand(splitCommand[1]);
        default:

            break;
        }

        throw new InvalidCommandException();
    }
}
