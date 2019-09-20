package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import util.HttpRequest;
import util.ReferenceFinder;
import view.MainFrame;

public class AddListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame frame = (MainFrame) ReferenceFinder.findFrame((Component) e.getSource());
		JList<String> subreddits = frame.getmJListSubReddits();
		
		DefaultListModel<String> model = (DefaultListModel<String>) subreddits.getModel();
		
		String input = JOptionPane.showInputDialog("Add subreddit");

		if(input != null) {
			if(input.replace(" ", "").length() > 1) {
				model.addElement(input);
				frame.printToConsole("Subreddit " + input + " has been added to the download list!");
			}
		}

	}
	
}
