package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.ChangeDataSaveListener;
import controller.ExitListener;
import controller.RemoveSubredditsListener;
import controller.OpenDirectoryListener;


public class MyMenuBar extends JMenuBar {
	
	private static final long serialVersionUID = 1L;

	public MyMenuBar() {
		createToolsMenu();	
	}

	private void createToolsMenu() {
		final JMenu menuTools = new JMenu("Menu");
		
		final JMenuItem itemOpenDir = new JMenuItem("Open image directory");
		itemOpenDir.addActionListener(new OpenDirectoryListener());
		
		final JMenuItem itemExit = new JMenuItem("Exit");
		itemExit.addActionListener(new ExitListener());
		
		final JMenu menuSettings = new JMenu("Settings");
		
		final JMenuItem itemRemoveSubreddits = new JMenuItem("Clear subreddits");
		itemRemoveSubreddits.addActionListener(new RemoveSubredditsListener());
		
		final JMenuItem itemChangeDataSave = new JMenuItem("Choose image save location");
		itemChangeDataSave.addActionListener(new ChangeDataSaveListener());
		
		menuTools.add(itemOpenDir);
		menuTools.add(itemExit);
		
		menuSettings.add(itemRemoveSubreddits);
		menuSettings.add(itemChangeDataSave);
		
		this.add(menuTools);
		this.add(menuSettings);
	}
}
