package controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.JProgressBar;

import ressource.FrameConstants;
import util.HttpRequest;
import view.MainFrame;

public class ImageDownloader {

	private ArrayList<String> urls;
	private MainFrame frame;
	private String subreddit;
	private int skipped = 0;
	private int failed = 0;
	private int total = 0;
	
	public ImageDownloader(ArrayList<String> urls, String subreddit, MainFrame frame) {
		this.urls = urls;
		this.subreddit = subreddit;
		
		checkDirExists();
		
		this.frame = frame;
	}

	private void checkDirExists() {
		File dir = new File(FrameConstants.IMAGE_DIR);
		
		if(!dir.exists()) {
			try {
				dir.mkdirs();
			} catch (Exception e) {
				frame.printToConsole("Creating the subreddit directory failed!\nExiting.");
				System.exit(1);
			}
		}
		
	}

	public void getImages() {
		// Download the images
		try {
			iDownload();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void iDownload() throws IOException {
		JProgressBar progress = frame.getProgressBar();
		progress.setMinimum(0);
		progress.setMaximum((int)urls.size());
		for (String url : this.urls) {
			try {				
				String fileFull = url.substring(url.lastIndexOf("/") + 1);
				
				if(fileFull.contains("?")) {
					fileFull = fileFull.substring(0, fileFull.indexOf("?") - 1);
				}
			
				File newImage = new File(FrameConstants.IMAGE_DIR + this.subreddit + "/" + fileFull);
				String fileExtension = fileFull.substring(fileFull.lastIndexOf(".") + 1);
				
				if(!newImage.isFile() && (fileExtension.contains("jpg") 
						|| fileExtension.contains("png"))) {
					
					frame.printToConsole("[+] Downloading image " + newImage.getName());
					InputStream in = new BufferedInputStream(HttpRequest.getResponseData(url, true));

					ByteArrayOutputStream out = new ByteArrayOutputStream();
					byte[] buf = new byte[1024];
					int n = 0;
					while (-1 != (n = in.read(buf))) {
						out.write(buf, 0, n);
					}
					out.close();
					in.close();
					byte[] response = out.toByteArray();
							
					FileOutputStream fos = new FileOutputStream(FrameConstants.IMAGE_DIR + this.subreddit + "\\" + fileFull);
					fos.write(response);
					fos.close();
				} else {
					frame.printToConsole("[?] Image " + newImage.getName() + " exists, skipping it.");
					skipped++;
				}
			} catch (Exception e) {
				frame.printToConsole("[!] A not so epic error has happened!");
				frame.printToConsole("[-] " + e.getMessage());
				failed++;
			}
			
			progress.setValue(total);
			progress.setString((int) ((progress.getPercentComplete() * 100) + 1) + "%");
			total++;
		}
	}

	public boolean makeSubredditDir() {
		boolean retval = true;
		
		File subDir = new File(FrameConstants.IMAGE_DIR + this.subreddit);
		
		if(!subDir.exists()) {
			frame.printToConsole("Making dir " + FrameConstants.IMAGE_DIR + this.subreddit);
			try {
				subDir.mkdir();
			} catch (Exception e) {
				e.printStackTrace();
				retval = false;
			}
		}
		
		return retval;
	}

	public int getSkipped() {
		return skipped;
	}

	public int getFailed() {
		return failed;
	}
	
	public int getTotal() {
		return total;
	}

}
