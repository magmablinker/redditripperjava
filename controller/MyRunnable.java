package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JList;
import javax.swing.JProgressBar;

import ressource.FrameConstants;
import util.HttpRequest;
import util.JSONParser;
import util.ReferenceFinder;
import view.MainFrame;

public class MyRunnable implements Runnable {

	private ActionEvent e;
	private boolean exit = false;

	public MyRunnable(ActionEvent e) {
		this.e = e;
	}

	@Override
	public void run() {
		MainFrame frame = (MainFrame) ReferenceFinder.findFrame((Component) this.e.getSource());
		JList<String> subreddits = frame.getmJListSubReddits();
		int i = 0;
		long startTime = 0;
		int total = 0;
		int skipped = 0;
		int failed = 0;
		ImageDownloader dl = null;
		JProgressBar progress = frame.getProgressBar();

		while (!exit) {
			startTime = System.nanoTime();
			for (i = 0; i < subreddits.getModel().getSize(); i++) {
				String data = subreddits.getModel().getElementAt(i);
				JSONParser parser = null;
				try {
					frame.clearConsole();
					frame.printToConsole("******************************************");
					frame.printToConsole("Getting " + FrameConstants.POST_RANKING_TYPE + " "
							+ FrameConstants.POST_AMOUNT_POSTS + " posts for " + data);
					frame.printToConsole("******************************************");
					
					progress.setString("Getting data for " + data);
					
					parser = new JSONParser(
							HttpRequest.getResponseData(String.format("https://api.reddit.com/r/%s/%s?limit=%d", data,
									FrameConstants.POST_RANKING_TYPE, FrameConstants.POST_AMOUNT_POSTS)));
					parser.parseJSON();
					
					dl = new ImageDownloader(parser.getUrls(), data, frame);
					if (dl.makeSubredditDir()) {
						dl.getImages();
					}
				} catch (Exception e1) {
					frame.printToConsole("Error: " + e1.getMessage());
				}
				failed += dl.getFailed();
				skipped += dl.getSkipped();
				total += dl.getTotal();

				progress.setValue(0);
			}
			exit = true;
		}

		long endTime = System.nanoTime();

		long duration = ((endTime - startTime) / 1000000) / 1000;

		frame.clearConsole();
		frame.printToConsole("+-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-+");
		frame.printToConsole("|                 END STATS               |");
		frame.printToConsole("*******************************************");
		frame.printToConsole("[+] Finished downloading " + i + " subreddits in " + duration + " seconds");
		frame.printToConsole("*******************************************");
		frame.printToConsole("[+] Total files: " + total);
		frame.printToConsole("[-] Files failed: " + failed);
		frame.printToConsole("[+] Files skipped: " + skipped);
		frame.printToConsole("*******************************************");
		frame.setThreadStarted(false);
	}

	public void stop() {
		exit = true;
	}

	public boolean isExit() {
		return exit;
	}

}
