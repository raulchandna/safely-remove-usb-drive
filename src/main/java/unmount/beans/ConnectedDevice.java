package unmount.beans;

public class ConnectedDevice {
    private String device;
    private String deviceFileSystem;
    private String deviceName;

    public ConnectedDevice(String deviceFileSystem, String deviceName) {
        this.deviceFileSystem = deviceFileSystem;
        this.device = deviceFileSystem.substring(0, deviceFileSystem.length() - 1);
        this.deviceName = deviceName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getDeviceFileSystem() {
        return deviceFileSystem;
    }

    public String getDevice() {
        return device;
    }
}
