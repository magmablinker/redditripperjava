package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import model.PreferenceStore;
import ressource.FrameConstants;
import util.ReferenceFinder;
import view.MainFrame;

public class ChangeDataSaveListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame frame = (MainFrame) ReferenceFinder.findFrame((Component) e.getSource());
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File(FrameConstants.IMAGE_DIR));
		chooser.setDialogTitle("Choose location for images");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			File choosenDir = chooser.getSelectedFile();
			frame.printToConsole("Image output dir set to: " + choosenDir.getAbsolutePath());
			FrameConstants.setImageDir(choosenDir.getAbsolutePath() + "\\");
			PreferenceStore store = new PreferenceStore();
			store.save(FrameConstants.IMAGE_DIR);
			frame.setLabelImageDirText();
		} else {
			frame.printToConsole("No new directory choosen.");
		}
	}

}
