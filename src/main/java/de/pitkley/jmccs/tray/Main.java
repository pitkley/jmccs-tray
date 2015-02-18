package de.pitkley.jmccs.tray;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        if (!SystemTray.isSupported()) {
            System.out.println("SysTray not supported");
            System.exit(1);
        }

        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final PopupMenu popup = new PopupMenu();
        final Image i = toolkit.getImage(Main.class.getClassLoader().getResource("bulb.gif"));
        final TrayIcon icon = new TrayIcon(i);
        final SystemTray tray = SystemTray.getSystemTray();

        MenuItem aboutItem = new MenuItem("About");
        CheckboxMenuItem cb1 = new CheckboxMenuItem("Set auto size");
        CheckboxMenuItem cb2 = new CheckboxMenuItem("Set tooltip");

        Menu displayMenu = new Menu("Display");
        MenuItem errorItem = new MenuItem("Error");
        MenuItem warningItem = new MenuItem("Warning");

        popup.add(aboutItem);
        popup.addSeparator();
        popup.add(cb1);
        popup.add(cb2);
        popup.addSeparator();
        popup.add(displayMenu);
        displayMenu.add(errorItem);
        displayMenu.add(warningItem);

        icon.setPopupMenu(popup);

        try {
            tray.add(icon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
