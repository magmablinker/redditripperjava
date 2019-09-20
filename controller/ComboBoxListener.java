package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ressource.FrameConstants;
import util.ReferenceFinder;
import view.MainFrame;

public class ComboBoxListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame frame = (MainFrame) ReferenceFinder.findFrame((Component) e.getSource());
		String selectedValue = String.valueOf(frame.getComboBox().getSelectedItem());
		
		FrameConstants.setPostRankingType(selectedValue);
		frame.printToConsole("Set post ranking type to " + selectedValue);
	}

}
