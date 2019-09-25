package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import ressource.FrameConstants;
import util.ReferenceFinder;
import view.MainFrame;

public class EditListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame frame = (MainFrame) ReferenceFinder.findFrame((Component) e.getSource());
		JList<String> list = frame.getmJListSubReddits();
		DefaultListModel<String> model = (DefaultListModel<String>) list.getModel();
		int selectedIndex = list.getSelectedIndex();

		if (model.getSize() > 0) {
			if (selectedIndex > -1) {
				String input = JOptionPane.showInputDialog(FrameConstants.OPTION_DIALOG_TEXT_EDIT, model.getElementAt(selectedIndex));

				if (input != null) {
					if (input.replace(" ", "") != "") {
						model.set(selectedIndex, input);
						frame.printToConsole("[+] Successfully changed the entry to " + input);
					} else {
						frame.printToConsole("[!] Invalid input, no changes have been applied.");
					}
				} else {
					frame.printToConsole("[?] No changes have been applied.");
				}

			} else {
				frame.printToConsole("[!] You have to select an entry first.");
			}

		}

	}

}
