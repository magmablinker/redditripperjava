package view;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.UIManager;

import model.DataStore;
import model.PreferenceStore;
import ressource.FrameConstants;
import view.MainFrame;

public class Main {

	public static void main(String[] args) throws IOException {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
		
		final MainFrame mainFrame = new MainFrame();
		mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		WindowListener exitListener = new WindowAdapter() {

		    @Override
		    public void windowClosing(WindowEvent e) {
				DataStore store = new DataStore();
				PreferenceStore pStore = new PreferenceStore();
					
				store.save(mainFrame.getmJListSubReddits());
				pStore.save(FrameConstants.IMAGE_DIR);
				
				mainFrame.setVisible(false);
				mainFrame.dispose();
				
				System.exit(0);
		    }
		};
		mainFrame.addWindowListener(exitListener);
		
		mainFrame.setSize(900, 700);
		mainFrame.setMinimumSize(new Dimension(400, 400));
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		
		mainFrame.getTextArea().requestFocusInWindow();
	}
}
