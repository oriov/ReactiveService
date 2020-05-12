import command.CommandExecutor;
import command.PrintStateCommand;
import command.ReactiveService;
import command.UpdateStateCommand;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String cells = InputHandler.readAndPrintFile(args);
        ReactiveService reactiveService = new ReactiveService();
        CommandExecutor commandExecutor = new CommandExecutor();
        boolean includeMessage = true;
        while (true) {
            String choice = InputHandler.readCommandFromUser(includeMessage);
            includeMessage = false;
            if (choice.equals("# a")) {
                commandExecutor.addCommand(new PrintStateCommand(reactiveService, cells));
            } else if (choice.contains("# b ")) {
                cells = InputHandler.updateCells(cells, choice);
                commandExecutor.addCommand(new UpdateStateCommand(reactiveService, cells));
            } else {
                System.out.println("wrong input, please try again");
            }
            commandExecutor.executeCommand();
        }
    }
}
