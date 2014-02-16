package unmount;

import org.junit.Before;
import org.junit.Test;
import system.command.MockSystemInteractor;
import unmount.beans.ConnectedDevice;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UnmountGUICommandTest {
    UnmountGUICommand guiCommand;

    @Before
    public void setUp() {
        guiCommand = new UnmountGUICommand();
        guiCommand.setSystemInteractor(new MockSystemInteractor());
    }

    @Test
    public void testGetConnectedDevices() throws Exception {
        List<ConnectedDevice> connectedDevices = guiCommand.getConnectedDevices();
        firstConnectedDeviceAssert(connectedDevices.get(0));
        secondConnectedDeviceAssert(connectedDevices.get(1));
    }

    private void firstConnectedDeviceAssert(ConnectedDevice connectedDevice) {
        String[] connectedDeviceInfo = {
                "/dev/sdb1",
                "/dev/sdb",
                "UUI"
        };
        connectedDeviceAssert(connectedDevice, connectedDeviceInfo);
    }

    private void secondConnectedDeviceAssert(ConnectedDevice connectedDevice) {
        String[] connectedDeviceInfo = {
                "/dev/sdc2",
                "/dev/sdc",
                "E44A-28A8"
        };
        connectedDeviceAssert(connectedDevice, connectedDeviceInfo);
    }

    private void connectedDeviceAssert(ConnectedDevice connectedDevice, String[] connectedDeviceInfo) {
        assertEquals(connectedDevice.getDeviceFileSystem(), connectedDeviceInfo[0]);
        assertEquals(connectedDevice.getDevice(), connectedDeviceInfo[1]);
        assertEquals(connectedDevice.getDeviceName(), connectedDeviceInfo[2]);
    }
}
