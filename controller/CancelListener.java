package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import util.ReferenceFinder;
import view.MainFrame;

public class CancelListener implements ActionListener {

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame frame = (MainFrame) ReferenceFinder.findFrame((Component) e.getSource());
		
		if(frame.isThreadStarted()) {
			Thread thread = frame.getThread();
			frame.printToConsole("Canceling action.");
			thread.stop();
			frame.setThreadStarted(false);
		}

	}

}
