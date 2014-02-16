package unmount;

import exception.UnmountException;
import system.command.RealTimeSystemInteractor;
import system.command.SystemEnum;
import system.command.SystemInteractor;
import unmount.beans.ConnectedDevice;

import java.util.ArrayList;
import java.util.List;

public class UnmountGUICommand {
    private SystemInteractor systemInteractor;

    public void setSystemInteractor(SystemInteractor systemInteractor) {
        this.systemInteractor = systemInteractor;
    }

    private SystemInteractor getSystemInteractor() {
        if (systemInteractor == null) {
            return new RealTimeSystemInteractor();
        }
        return systemInteractor;
    }

    public List<ConnectedDevice> getConnectedDevices() throws UnmountException {
        List<String> connectedDevicesResponse = executeCommand(SystemEnum.CONNECTED_DEVICES.getConnectedDeviceCommand());
        return getConnectedDeviceBeanList(connectedDevicesResponse);
    }

    private List<ConnectedDevice> getConnectedDeviceBeanList(List<String> connectedDevicesResponse) {
        List<ConnectedDevice> connectedDevices = new ArrayList<ConnectedDevice>();
        for (String connectedDeviceInfo : connectedDevicesResponse) {
            ConnectedDevice connectedDevice = getConnectedDevice(connectedDeviceInfo);
            if (connectedDevice != null) {
                connectedDevices.add(connectedDevice);
            }
        }
        return connectedDevices;
    }

    private ConnectedDevice getConnectedDevice(String connectedDevice) {
        for (String deviceLocation : SystemEnum.CONNECTED_DEVICES.getDeviceLocations()) {
            if (!connectedDevice.contains(deviceLocation)) {
                return null;
            }
        }
        final String deviceFileSystem = connectedDevice.substring(0, connectedDevice.indexOf(" "));
        final String deviceMountLocation = connectedDevice.substring(connectedDevice.indexOf(" ") + 1, connectedDevice.length());
        final String deviceName = deviceMountLocation.substring(deviceMountLocation.lastIndexOf('/') + 1, deviceMountLocation.length());
        return new ConnectedDevice(deviceFileSystem, deviceName);
    }

    public void safelyRemoveDevice(ConnectedDevice connectedDevice) throws UnmountException {
        unMountDevice(connectedDevice);
        detachDevice(connectedDevice);
    }

    private void detachDevice(ConnectedDevice connectedDevice) throws UnmountException {
        executeCommand(getDetachDeviceString(connectedDevice.getDevice()));
        executeCommand(getDetachDeviceString(connectedDevice.getDevice()));
    }

    private void unMountDevice(ConnectedDevice connectedDevice) throws UnmountException {
        executeCommand(getUnMountDeviceCommand(connectedDevice.getDeviceFileSystem()));
    }

    private String getUnMountDeviceCommand(String deviceFileSystem) {
        return new StringBuilder()
                .append(SystemEnum.CONNECTED_DEVICES.getUnMountDeviceCommand())
                .append(" ")
                .append(deviceFileSystem).toString();
    }

    private String getDetachDeviceString(String device) {
        return new StringBuilder()
                .append(SystemEnum.CONNECTED_DEVICES.getDetachDeviceCommand())
                .append(" ")
                .append(device).toString();
    }

    private List<String> executeCommand(String command) throws UnmountException {
        return getSystemInteractor().executeCommand(command);
    }
}
