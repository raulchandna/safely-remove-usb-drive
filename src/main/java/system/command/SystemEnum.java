package system.command;

import java.util.ArrayList;
import java.util.List;

public enum SystemEnum {
    CONNECTED_DEVICES("df -h");
    private String command;

    SystemEnum(String command) {
        this.command = command;
    }

    public List<String> getDeviceLocations() {
        List<String> deviceLocations = new ArrayList<String>();
        setDeviceLocations(deviceLocations);
        return deviceLocations;
    }

    private void setDeviceLocations(List<String> deviceLocations) {
        deviceLocations.add("/media");
    }

    public String getConnectedDeviceCommand() {
        return command;
    }

    public String getUnMountDeviceCommand() {
        return "udisks --unmount";
    }

    public String getDetachDeviceCommand() {
        return "udisks --detach";
    }
}
