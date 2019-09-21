package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import util.ReferenceFinder;
import view.MainFrame;

public class RemoveSubredditsListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame frame = (MainFrame) ReferenceFinder.findFrame((Component) e.getSource());
		JList<String> listLeft = frame.getmJListSubReddits();
		DefaultListModel<String> model = (DefaultListModel<String>) listLeft.getModel();
		
		model.clear();
	}

}
