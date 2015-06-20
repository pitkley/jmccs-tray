package de.pitkley.jmccs.tray;

import de.pitkley.jmccs.monitor.Monitor;
import de.pitkley.jmccs.monitor.MonitorManager;
import de.pitkley.jmccs.monitor.Monitors;
import de.pitkley.jmccs.monitor.VCPCode;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {

    final static MonitorManager monitorManager = Monitors.getMonitorManager();
    final static List<Monitor> monitors = monitorManager.getMonitors();

    private static class Brightness {
        final int mainBrightness;
        final int otherBrightness;

        Brightness(int brightness) {
            this(brightness, brightness);
        }

        Brightness(int mainBrightness, int otherBrightness) {
            this.mainBrightness = mainBrightness;
            this.otherBrightness = otherBrightness;
        }
    }

    private static void setBrightness(Brightness brightness) {
        for (Monitor monitor : monitors) {
            if (monitor.isMainMonitor()) {
                monitor.setVCPFeature(VCPCode.LUMINANCE, brightness.mainBrightness);
            } else {
                monitor.setVCPFeature(VCPCode.LUMINANCE, brightness.otherBrightness);
            }
        }
    }

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

        final Map<String, Brightness> menuItems = new LinkedHashMap<>();
        menuItems.put("1 - 1", new Brightness(1));
        menuItems.put("10 - 1", new Brightness(10, 1));
        menuItems.put("30 - 30", new Brightness(30));
        menuItems.put("50 - 50", new Brightness(50));
        menuItems.put("100 - 1", new Brightness(100, 1));
        menuItems.put("100 - 100", new Brightness(100));

        MenuItem miExit = new MenuItem("Exit");
        miExit.addActionListener(e -> {
            monitorManager.closeMonitors();
            System.exit(0);
        });
        popup.add(miExit);
        popup.addSeparator();

        for (Map.Entry<String, Brightness> entry : menuItems.entrySet()) {
            MenuItem m = new MenuItem(entry.getKey());
            m.addActionListener(e -> Main.setBrightness(entry.getValue()));
            popup.add(m);
        }

        icon.setPopupMenu(popup);

        try {
            tray.add(icon);
        } catch (AWTException e) {
            e.printStackTrace();
            monitorManager.closeMonitors();
        }
    }
}
