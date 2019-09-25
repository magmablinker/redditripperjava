package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import util.ReferenceFinder;
import view.MainFrame;

public class StartListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame frame = (MainFrame) ReferenceFinder.findFrame((Component) e.getSource());
		JList<String> list = frame.getmJListSubReddits();
		DefaultListModel<String> model = (DefaultListModel<String>) list.getModel();
		
		if(model.size() > 0) {
			if (!frame.isThreadStarted()) {
				if(checkInternetConnection()) {
					Runnable r = new MyRunnable(e);
					Thread thread = new Thread(r);
					frame.setThread(thread);
					frame.setThreadStarted(true);
					thread.start();
				} else {
					frame.printToConsole("[!] Please connect your device to the internet to continue.");
				}
				
			}	
		} else {
			frame.printToConsole("[!] Please add subreddits to the list so start.");
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
