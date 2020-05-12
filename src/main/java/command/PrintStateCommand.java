package command;

import java.util.List;

public class PrintStateCommand implements Command {
    private ReactiveService reactiveService;
    private String cells;

    public PrintStateCommand(ReactiveService reactiveService, String cells) {
        this.reactiveService = reactiveService;
        this.cells = cells;
    }

    @Override
    public void execute() {
        reactiveService.buildUserInputAsObserverPattern(cells);
        List<Double> results = reactiveService.calculateResults();
        int size = results.size();
        for (int i = 0; i < size; i++) {
            System.out.print("[" + i + ": " + results.get(i) + "]" + (i == size -1 ? "" : ", "));
        }
        System.out.println();
        System.out.println();
    }
}
