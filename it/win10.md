## Tip ##
**Wallpaper**
C:\Users\\**linhai**\AppData\Local\Packages\Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy\LocalState\Assets

**Display hostname in toolbar**
Right-click on Taskbar -> 
Go to Toolbars -> 
Choose New Toolbar, 
type in \\%computername%,  and Click Select Folder. 
Done.

## Installation
**Lenovo M900P**  
- Burn a UEFI USB boot disk.
- In BIOS, disable CPU Turbo Mode
- When system restart, make sure boot up from hard disk and the USB is inserted.

**Stuck when startup**  
Start your PC, just as Windows attempts to load (spinning dots), press and hold Power Button for 5 - 10 seconds to perform a Hard Shut Down

Do this twice

On the third start Windows will boot into the Recovery Environment and from there you can access System Repair, Safe Mode, Command Prompt . . . etc.

Please try each option one at a time, then see of Windows will boot . . .

1. If there is an option to uninstall the most recent update,  that will be the best option
2. Otherwise, go to Troubleshoot - Advanced Option - Startup Repair  
See if Windows startup can repair itself
3. Go to Troubleshoot - Advanced Option - System Restore  
Check to see if you have a restore point you can go back to
4. If you do not have a Restore Point, go to Troubleshoot - Advanced Option - Startup Settings and click Restart
Upon restart, press 4 to enter Safe Mode  
Once in Safe Mode, backup your data if you do not already have a backup
Open Device Manager and check to make sure all your Drivers are up to date
Reboot to see if Windows will start Normally
5. If the above fails, go to Troubleshoot - Advanced Options - Reset this PC  
Choose to keep your files and apps, just your files or to keep nothing  
Then start the Reset  

https://answers.microsoft.com/en-us/windows/forum/windows_10-update/pc-stuck-on-lenovo-logo/fd2141e4-ed54-4576-8a1b-acf2d2723991