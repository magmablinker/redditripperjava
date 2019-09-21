package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.filechooser.FileSystemView;

import ressource.FrameConstants;

public class PreferenceStore implements IDataStore {

	@Override
	public ArrayList<String> load() {
		File preferences = new File(FrameConstants.PREFERENCES_FILE);

		if (!preferences.exists() || !preferences.isFile()) {
			createPreferencesFile(preferences);
		}

		BufferedReader read = null;

		try {
			read = new BufferedReader(new FileReader(preferences));
		} catch (FileNotFoundException e) {
			
		}

		String line;
		String[] fuck;
		try {
			while ((line = read.readLine()) != null) {
				fuck = line.split(";");
				switch (fuck[0]) {
				case "destDir":
					FrameConstants.setImageDir(fuck[1]);
					break;
				case "postRankingType":
					FrameConstants.setPostRankingType(fuck[1]);
					break;
				case "amountPosts":
					FrameConstants.setPostAmountPosts(Integer.parseInt(fuck[1]));
				default:
					break;
				}
				
			}
			read.close();
		} catch (Exception e) {
			
		}
		
		return null;
	}

	private void createPreferencesFile(File preferences) {
		try {
			File basedir = new File(FrameConstants.BASE_DIR);
			basedir.mkdirs();
			preferences.createNewFile();
			
			save(new JList<String>());
		} catch (Exception e) {
		
		}
	}

	@Override
	public void save(JList<String> data) {
		File preferences = new File(FrameConstants.PREFERENCES_FILE);
		
		
		BufferedWriter writer;
		
		try {
			writer = new BufferedWriter(new FileWriter(preferences));
			writer.write("destDir;" + FileSystemView.getFileSystemView().getHomeDirectory() + "\\redditripper\\images\\");
			writer.newLine();
			writer.write("postRankingType;" + FrameConstants.POST_RANKING_TYPE);
			writer.newLine();
			writer.write("amountPosts;" + FrameConstants.POST_AMOUNT_POSTS);
			writer.newLine();
			writer.close();
		} catch (Exception e) {

		}
	}
	
	public void save(String dir) {
		File preferences = new File(FrameConstants.PREFERENCES_FILE);
		
		
		BufferedWriter writer;
		
		try {
			writer = new BufferedWriter(new FileWriter(preferences));
			writer.write("destDir;" + dir);
			writer.newLine();
			writer.write("postRankingType;" + FrameConstants.POST_RANKING_TYPE);
			writer.newLine();
			writer.write("amountPosts;" + FrameConstants.POST_AMOUNT_POSTS);
			writer.newLine();
			writer.close();
		} catch (Exception e) {

		}
	}

}
