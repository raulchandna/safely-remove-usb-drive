safely-remove-usb-drive
=======================

Java program that uses runtime to execute udisks command to safely remove external storage devices.

This is a simple java applet that executes, udisks --unmount and udisks --detach commands to saftely remove the usb external storage.

The default location of usb drives is set to "/media", which can be edited or additional location can be added to the list present in SystemEnum file. 

The command to run the program is 

java -jar SafelyRemoveUsbDrive-1.0-SNAPSHOT.jar
