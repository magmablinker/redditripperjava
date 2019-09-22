package ressource;

import javax.swing.filechooser.FileSystemView;

public class FrameConstants {
	// Titles
	public static final String FRAME_TITLE = "RedditRipper Java Edition";
	public static final String LEFT_LIST_TITLE = "Subreddit List";
	
	// File/Directory related
	public static String BASE_DIR = FileSystemView.getFileSystemView().getHomeDirectory() + "/redditripper/";
	public static String SUBREDDIT_FILE = BASE_DIR + "subreddits.txt";
	public static String IMAGE_DIR = BASE_DIR + "images/";
	public static String PREFERENCES_FILE = BASE_DIR + "preferences.txt";
	
	// Post Ranking 'sort'
	public static String POST_RANKING_TYPE = "hot";
	public static String[] POST_RANKING_TYPE_ARRAY = {"hot", "top", "new", "rising", "controversial"};
	public static int POST_AMOUNT_POSTS = 100;
	
	// Buttons
	public static String BUTTON_START_TEXT = "Start";
	public static String BUTTON_CANCEL_TEXT = "Cancel";
	public static String BUTTON_ADD_TEXT = "Add";
	public static String BUTTON_REMOVE_TEXT = "Remove";
	public static String BUTTON_CLEAR_TEXT = "Clear";
	
	// Labels
	public static final String LABEL_DIRECTORY_TEXT = "Image output directory: ";
	public static final String LABEL_RANKING_TEXT = "Post ranking type";
	public static final String LABEL_AMOUNT_POSTS_TEXT = "Amount posts";
	
	// Preferences
	public static final String PREFERENCES_DELIMITER = ";";
	public static final String PREFERENCES_AMOUNT_POSTS = "amountPosts";
	public static final String PREFERENCES_POST_RANKING_TYPE = "postRankingType";
	public static final String PREFERENCES_DEST_DIR = "destDir";
	
	public static void setImageDir(String imageDir) {
		FrameConstants.IMAGE_DIR = imageDir;
	}
	
	public static void setPostRankingType(String type) {
		FrameConstants.POST_RANKING_TYPE = type;
	}
	
	public static void setPostAmountPosts(int amount) {
		FrameConstants.POST_AMOUNT_POSTS = amount;
	}
}
