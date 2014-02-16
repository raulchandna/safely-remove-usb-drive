package system.command;

import exception.UnmountException;

import java.util.List;

public interface SystemInteractor {
    List<String> executeCommand(String command) throws UnmountException;
}
