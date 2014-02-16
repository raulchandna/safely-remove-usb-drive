package system.command;

import java.util.ArrayList;
import java.util.List;

public class MockConnectedDevice {
    public List<String> getConnectedDevices() {
        List<String> connectedDevices = new ArrayList<String>();
        for (String connectedDevice : getConnectedDeviceResponse()) {
            connectedDevices.add(connectedDevice);
        }
        return connectedDevices;
    }

    private String[] getConnectedDeviceResponse() {
        final String[] connectedDeviceResponse = {
                "/dev/sdb1 /media/code/UUI",
                "/dev/sdc2 /media/code/E44A-28A8",
                "/dev/sda1 /"
        };
        return connectedDeviceResponse;
    }
}
