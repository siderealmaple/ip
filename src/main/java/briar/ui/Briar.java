package briar.ui;

import briar.command.Command;
import briar.exception.BriarException;
import briar.task.TaskList;

/**
 * Represents the chatbot Briar.
 */
public class Briar {
    private Ui ui;

    private TaskList tasks;

    private Storage storage;

    private Briar(String filePath) {
        this.storage = new Storage(filePath);
        ui = new Ui();
        try {
            String taskString = storage.load();
            tasks = new TaskList(taskString);
        } catch (BriarException exception) {
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Briar chatbot.
     */
    public void run() {
        ui.showWelcome();
        listen();
        ui.showExit();
    }

    public static void main(String[] args) {
        Briar briar = new Briar("./data/Briar.txt");
        briar.run();
    }

    private void listen() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String commandString = "";
                commandString = ui.readCommand();
                Command command = Parser.parse(commandString);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (BriarException exception) {
                ui.showError(exception.getMessage());
            }
        }
    }

}
