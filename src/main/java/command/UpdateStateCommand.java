package command;

public class UpdateStateCommand implements Command {
    private ReactiveService reactiveService;
    private String cells;

    public UpdateStateCommand(ReactiveService reactiveService, String cells) {
        this.reactiveService = reactiveService;
        this.cells = cells;
    }

    @Override
    public void execute() {
        reactiveService.buildUserInputAsObserverPattern(cells);
    }
}
