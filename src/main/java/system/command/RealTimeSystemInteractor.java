package system.command;

import exception.UnmountException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RealTimeSystemInteractor implements SystemInteractor {

    public List<String> executeCommand(String command) throws UnmountException {
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            return getResponse(process);
        } catch (Exception exception) {
            throw new UnmountException(exception);
        }
    }

    private List<String> getResponse(Process process) throws IOException {
        List<String> commandResponse = new ArrayList<String>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String responseLine = null;
        while ((responseLine = bufferedReader.readLine()) != null) {
            commandResponse.add(responseLine);
        }
        return commandResponse;
    }
}
