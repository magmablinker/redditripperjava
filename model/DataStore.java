package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import model.IDataStore;
import ressource.FrameConstants;

public class DataStore implements IDataStore {

	@Override
	public ArrayList<String> load() {
		ArrayList<String> data = new ArrayList<String>();
		File subreddits = new File(FrameConstants.SUBREDDIT_FILE);

		if(!subreddits.exists() || !subreddits.isFile()) {
			makeSubredditsDirAndFile(subreddits, new File(FrameConstants.BASE_DIR));
		}
			
		BufferedReader in = null;
		String line;
		
		try {
			in = new BufferedReader(new FileReader(subreddits));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			while((line = in.readLine()) != null) {
				data.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}

	private void makeSubredditsDirAndFile(File subreddits, File basedir) {
		try {
			basedir.mkdirs();
			subreddits.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void save(JList<String> data) {
		File subreddits = new File(FrameConstants.SUBREDDIT_FILE);
		BufferedWriter writer = null;
		
		if(!subreddits.exists() || !subreddits.isFile()) {
			makeSubredditsDirAndFile(subreddits, new File(FrameConstants.BASE_DIR));
		}
		
		try {
			writer = new BufferedWriter(new FileWriter(subreddits));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		DefaultListModel<String> model = (DefaultListModel<String>) data.getModel();
		
		for (int i = 0; i < model.getSize(); i++) {
			try {
				writer.write(model.getElementAt(i));
				writer.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
