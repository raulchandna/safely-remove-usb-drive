package system.command;

import exception.UnmountException;

import java.util.ArrayList;
import java.util.List;

public class MockSystemInteractor implements SystemInteractor {

    public List<String> executeCommand(String command) throws UnmountException {
        if (command.equals(SystemEnum.CONNECTED_DEVICES.getConnectedDeviceCommand()))
            return new MockConnectedDevice().getConnectedDevices();
        return new ArrayList<String>();
    }
}
