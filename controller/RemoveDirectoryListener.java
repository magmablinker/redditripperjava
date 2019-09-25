package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import ressource.FrameConstants;
import util.ReferenceFinder;
import view.MainFrame;

public class RemoveDirectoryListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame frame = (MainFrame) ReferenceFinder.findFrame((Component) e.getSource());
		File imageDirectory = new File(FrameConstants.IMAGE_DIR);

		if (imageDirectory.exists()) {
			File[] imageDirectoryContents = imageDirectory.listFiles();
			if (imageDirectoryContents != null) {
				for (File file : imageDirectoryContents) {
					if (deleteFile(file)) {
						frame.printToConsole(
								"Removing the directory " + file.getAbsolutePath() + " has been successful");
					} else {
						frame.printToConsole("Removing the directory " + file.getAbsolutePath() + " has been failed");
					}
				}
			}
		}

	}

	public boolean deleteFile(File file) {
		if (file.isDirectory() && file.exists()) {
			File[] files = file.listFiles();
			
			for (File f : files) {
				if(f.isDirectory()) {
					return deleteFile(file);
				} else {
					f.delete();
				}
			}
			
		}

		return file.delete();
	}

}
