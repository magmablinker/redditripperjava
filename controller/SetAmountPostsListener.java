package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import ressource.FrameConstants;
import util.ReferenceFinder;
import view.MainFrame;

public class SetAmountPostsListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame frame = (MainFrame) ReferenceFinder.findFrame((Component) e.getSource());
		JTextField amountPosts = frame.getAmountPosts();
		int aPosts = 1;
		
		try {
			aPosts = Integer.parseInt(amountPosts.getText());
			
			if(aPosts < 101 && aPosts > 0) {
				FrameConstants.setPostAmountPosts(aPosts);
				frame.printToConsole("Amount posts has been set to " + aPosts);
			} else {
				frame.printToConsole("Amount posts has to be between 1 and 100!");
			}
		} catch (Exception e2) {
			frame.printToConsole("Amount posts has to be a number!");
		}
		
	}

}
