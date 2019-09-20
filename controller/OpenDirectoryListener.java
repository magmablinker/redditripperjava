package controller;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import ressource.FrameConstants;
import util.ReferenceFinder;
import view.MainFrame;

public class OpenDirectoryListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame frame = (MainFrame) ReferenceFinder.findFrame((Component) e.getSource());
		try {
			File imageDir = new File(FrameConstants.IMAGE_DIR);
			
			if(imageDir.exists() && imageDir.isDirectory()) {
				Desktop.getDesktop().open(imageDir);	
			} else {
				frame.printToConsole("The image directory doesn't exist.");
			}
			
		} catch (IOException e1) {
			frame.printToConsole("Opening the directory failed.");
		}
	}

}
