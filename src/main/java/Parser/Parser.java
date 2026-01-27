package Parser;
import Command.Command;
import Command.DeadlineCommand;
import Command.DeleteCommand;
import Command.DukeException;
import Command.EventCommand;
import Command.ExitCommand;
import Command.ListCommand;
import Command.MarkCommand;
import Command.TodoCommand;
import Command.UnmarkCommand;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand == null || fullCommand.trim().isEmpty()) {
            throw new DukeException("Empty command.");
        }

        String trimmed = fullCommand.trim();
        String[] parts = trimmed.split(" ", 2);
        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1].trim() : "";

        switch (commandWord) {
        case "todo":
            return new TodoCommand(arguments);
        case "deadline":
            return new DeadlineCommand(arguments);
        case "event":
            return new EventCommand(arguments);
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(arguments);
        case "unmark":
            return new UnmarkCommand(arguments);
        case "delete":
            return new DeleteCommand(arguments);
        case "bye":
            return new ExitCommand();
        default:
            throw new DukeException("Invalid command");
        }
    }
}
