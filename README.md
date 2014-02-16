safely-remove-usb-drive
=======================

I use Ubuntu operating system and currently if I use "safely remove" option to remove my usb hard drive, I have to wait for a few minutes for it to spin down.

Ubuntu 13 comes with a command line program called udisks that safely removes the usb drives.

So I wrote this java program that uses runtime to execute udisks command to safely remove external storage devices.

This is a simple java applet that executes, udisks --unmount and udisks --detach commands to saftely remove the usb external storage.

The default location of usb drives is set to "/media", which can be edited or additional location can be added to the list present in SystemEnum file. 

The command to run the program is 

java -jar SafelyRemoveUsbDrive-1.0-SNAPSHOT.jar
