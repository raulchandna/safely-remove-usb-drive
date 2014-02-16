import unmount.UnmountGUICommand;
import unmount.beans.ConnectedDevice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SafelyRemoveUsbDrive {
    private JComboBox deviceComboBox;
    private JLabel deviceComboBoxLabel;
    private JTextField createdByTextField;
    private JButton removeDeviceButton;
    private JButton refreshDeviceButton;
    private List<ConnectedDevice> connectedDevices;

    public SafelyRemoveUsbDrive() {
        initializeGUIElements();
    }

    public static void main(String[] args) {
        new SafelyRemoveUsbDrive().initializeAndLoadJFrame();
    }

    private void initializeGUIElements() {
        final String deviceComboBoxLabelText = "Connected Devices";
        final String removeDeviceButtonText = "Safely Remove";
        final String refreshDeviceButtonText = "Refresh";
        final String createdByTextFieldText = "www.rccode.blogspot.com";
        deviceComboBox = new JComboBox();
        deviceComboBoxLabel = new JLabel(deviceComboBoxLabelText);
        removeDeviceButton = new JButton(removeDeviceButtonText);
        refreshDeviceButton = new JButton(refreshDeviceButtonText);
        createdByTextField = new JTextField(createdByTextFieldText);
        createdByTextField.setEditable(false);
        addDevicesToComboBox();
        addActions();
    }

    private void addActions() {
        refreshAction();
        safelyRemoveAction();
    }

    private JFrame initializeAndLoadJFrame() {
        final int frameWidth = 350;
        final int frameHeight = 150;
        final String frameTitle = "Safely Remove Usb Devices";
        JFrame jFrame = new JFrame(frameTitle);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(frameWidth, frameHeight);
        jFrame.setResizable(false);
        jFrame.setContentPane(initializeContainer(jFrame.getContentPane()));
        jFrame.setVisible(true);
        return jFrame;
    }


    private Container initializeContainer(Container container) {
        final int rigidAreaWidth = 175;
        final int rigidAreaHeight = 0;
        container.setLayout(new FlowLayout());
        container.add(deviceComboBoxLabel);
        container.add(Box.createRigidArea(new Dimension(rigidAreaWidth, rigidAreaHeight)));
        container.add(deviceComboBox);
        container.add(Box.createRigidArea(new Dimension(rigidAreaWidth, rigidAreaHeight)));
        container.add(removeDeviceButton);
        container.add(refreshDeviceButton);
        container.add(Box.createRigidArea(new Dimension(rigidAreaWidth, rigidAreaHeight)));
        container.add(createdByTextField);
        return container;
    }

    private void addDevicesToComboBox() {
        try {
            connectedDevices = new UnmountGUICommand().getConnectedDevices();
            deviceComboBox.removeAllItems();
            updateRemoveDeviceButton(connectedDevices.size());
            for (ConnectedDevice connectedDevice : connectedDevices) {
                deviceComboBox.addItem(getDeviceName(connectedDevice));
            }
        } catch (Exception exception) {
            displayAlertMessage(exception.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateRemoveDeviceButton(int connectedDeviceCount) {
        if (connectedDeviceCount == 0) {
            removeDeviceButton.setVisible(false);
            refreshDeviceButton.setVisible(true);
        } else {
            removeDeviceButton.setVisible(true);
            refreshDeviceButton.setVisible(false);
        }
    }

    private String getDeviceName(ConnectedDevice connectedDevice) {
        final int maxDeviceNameSize = 40;
        String deviceName = connectedDevice.getDeviceName();
        if (deviceName.length() > maxDeviceNameSize) {
            deviceName = connectedDevice.getDeviceName().substring(0, maxDeviceNameSize);
        }
        return deviceName;
    }

    private void refreshAction() {
        refreshDeviceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addDevicesToComboBox();
            }
        });
    }

    private void safelyRemoveAction() {
        final String safelyRemoveMessage = "Device safely removed.";
        removeDeviceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new UnmountGUICommand().safelyRemoveDevice(getSelectedConnectedDevice());
                    displayAlertMessage(safelyRemoveMessage, JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception exception) {
                    displayAlertMessage(exception.getMessage(), JOptionPane.ERROR_MESSAGE);
                }
                addDevicesToComboBox();
            }
        });
    }

    private ConnectedDevice getSelectedConnectedDevice() {
        return connectedDevices.get(deviceComboBox.getSelectedIndex());
    }

    private void displayAlertMessage(String message, int alertIcon) {
        final String alertInfo = "";
        JOptionPane.showMessageDialog(new Frame(), message, alertInfo, alertIcon);
    }

}
