package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;
import java.net.Socket;

import util.ReferenceFinder;
import view.MainFrame;

public class StartListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame frame = (MainFrame) ReferenceFinder.findFrame((Component) e.getSource());

		if (!frame.isThreadStarted()) {
			if(checkInternetConnection()) {
				Runnable r = new MyRunnable(e);
				Thread thread = new Thread(r);
				frame.setThread(thread);
				frame.setThreadStarted(true);
				thread.start();
			} else {
				frame.printToConsole("Please connect your device to the internet to continue.");
			}
			
		}

	}

	public boolean checkInternetConnection() {
		boolean status = false;
		Socket sock = new Socket();
		InetSocketAddress address = new InetSocketAddress("www.google.com", 80);

		try {
			sock.connect(address, 3000);
			if (sock.isConnected())
				status = true;
		} catch (Exception e) {

		} finally {
			try {
				sock.close();
			} catch (Exception e) {
			}
		}

		return status;
	}

}
