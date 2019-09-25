package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JProgressBar;

import util.ReferenceFinder;
import view.MainFrame;

public class CancelListener implements ActionListener {

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame frame = (MainFrame) ReferenceFinder.findFrame((Component) e.getSource());

		if(frame.isThreadStarted()) {
			JProgressBar progress = frame.getProgressBar();
			progress.setValue(0);
			progress.setString("Stopped");
			Thread thread = frame.getThread();
			frame.printToConsole("Canceling action.");
			thread.stop();
			frame.setThreadStarted(false);
		}

	}

}
