package experiments;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TrayTest extends JFrame {
	private SystemTray systemTray = SystemTray.getSystemTray();
	private TrayIcon trayIcon;

	public TrayTest() throws IOException {
		super("Tray test");
		getContentPane().add(new JColorChooser());
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		trayIcon = new TrayIcon(ImageIO.read(new File("ico.gif")), "Tray test application");

		trayIcon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(true);
				setState(Frame.NORMAL);
				removeTrayIcon();
			}
		});
		addWindowStateListener(new WindowStateListener() {
			@Override
			public void windowStateChanged(WindowEvent e) {
				if (e.getNewState() == Frame.ICONIFIED) {
					setVisible(false);
					addTrayIcon();
				}
			}
		});
	}

	private void removeTrayIcon() {
		systemTray.remove(trayIcon);
	}

	private void addTrayIcon() {
		try {
			systemTray.add(trayIcon);
			trayIcon.displayMessage("Tray test", "Window minimised to tray, double click to show",
					TrayIcon.MessageType.INFO);
		} catch (AWTException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		TrayTest trayTest = new TrayTest();
		trayTest.pack();
		trayTest.setLocationRelativeTo(null);
		trayTest.setVisible(true);
	}

}