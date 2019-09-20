package ressource;

import javax.swing.filechooser.FileSystemView;

public class FrameConstants {
	public static String FRAME_TITLE = "RedditRipper Java Edition";
	
	// File/Directory related
	public static String BASE_DIR = FileSystemView.getFileSystemView().getHomeDirectory() + "\\redditripper\\";
	public static String SUBREDDIT_FILE = BASE_DIR + "subreddits.txt";
	public static String IMAGE_DIR = BASE_DIR + "images\\";
	public static String PREFERENCES_FILE = BASE_DIR + "preferences.txt";
	
	// Post Ranking 'sort'
	public static String POST_RANKING_TYPE = "hot";
	public static String[] POST_RANKING_TYPE_ARRAY = {"hot", "top", "new", "rising", "controversial"};
	
	// Buttons
	public static String BUTTON_START_TEXT = "Start";
	public static String BUTTON_CANCEL_TEXT = "Cancel";
	public static String BUTTON_ADD_TEXT = "Add";
	public static String BUTTON_REMOVE_TEXT = "Remove";
	public static String BUTTON_CLEAR_TEXT = "Clear";
	
	// Labels
	public static String LABEL_RANKING_TEXT = "Post ranking type";
	
	public static void setImageDir(String imageDir) {
		FrameConstants.IMAGE_DIR = imageDir;
	}
	
	public static void setPostRankingType(String type) {
		FrameConstants.POST_RANKING_TYPE = type;
	}
}
