package dev.denismasterherobrine.tripmanager.example.behavioral.command;

import java.util.ArrayList;
import java.util.List;

public class BookingInvoker {
    private final List<ICommand> ICommandQueue = new ArrayList<>();

    public void addCommand(ICommand ICommand) {
        ICommandQueue.add(ICommand);
    }

    public void executeCommands() {
        for (ICommand ICommand : ICommandQueue) {
            ICommand.execute();
        }
        ICommandQueue.clear();
    }
}
