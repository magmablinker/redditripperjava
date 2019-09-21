package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.AddListener;
import controller.CancelListener;
import controller.ClearListener;
import controller.ComboBoxListener;
import controller.ListLoaderLeft;
import controller.RemoveListener;
import controller.StartListener;
import model.PreferenceStore;
import ressource.FrameConstants;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JList<String> mJListSubReddits;
	private JTextArea textArea = new JTextArea(24, 50);
	private JScrollPane scroll;
	private MyMenuBar myMenuBar;
	private Thread thread;
	private JLabel labelImageDir;
	private JComboBox<String> comboBox;
	private boolean threadStarted = false;

	public MainFrame() {
		super(FrameConstants.FRAME_TITLE);

		this.myMenuBar = new MyMenuBar();
		this.setJMenuBar(myMenuBar);

		PreferenceStore store = new PreferenceStore();
		store.load();
		
		this.add(createContent());
	}

	private Component createContent() {
		JPanel main = new JPanel(new BorderLayout(5, 5));

		main.add(new JPanel(), BorderLayout.NORTH);
		main.add(new JPanel(), BorderLayout.WEST);
		main.add(new JPanel(), BorderLayout.EAST);
		main.add(createContentMiddle(), BorderLayout.CENTER);
		main.add(new JPanel(), BorderLayout.SOUTH);
		
		return main;
	}
	
	private Component createContentMiddle() {
		JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
		JPanel bottomPanel = new JPanel(new GridLayout(1, 3, 5, 5));
		
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.LIGHT_GRAY);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        textArea.setMargin(new Insets(2, 2, 2, 2));
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        
        this.scroll = new JScrollPane (textArea);
        this.scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		JButton bStart = new JButton(FrameConstants.BUTTON_START_TEXT);
		bStart.addActionListener(new StartListener());

		JButton bCancel = new JButton(FrameConstants.BUTTON_CANCEL_TEXT);
		bCancel.addActionListener(new CancelListener());
		
		JButton bClear = new JButton(FrameConstants.BUTTON_CLEAR_TEXT);
		bClear.addActionListener(new ClearListener());
		
		bottomPanel.add(bClear);
		bottomPanel.add(bCancel);
		bottomPanel.add(bStart);

		centerPanel.add(scroll, BorderLayout.CENTER);
		centerPanel.add(bottomPanel, BorderLayout.SOUTH);
		centerPanel.add(createContentTop(), BorderLayout.NORTH);
		centerPanel.add(createConentLeft(), BorderLayout.WEST);
		
		return centerPanel;
	}

	private Component createContentTop() {
		JPanel topPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		
		setLabelImageDir(new JLabel());
		setLabelImageDirText();
		this.labelImageDir.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
		
		JPanel splitPanel = new JPanel(new GridLayout(1, 3, 5, 5));
		JComboBox<String> rankingType = new JComboBox<String>(FrameConstants.POST_RANKING_TYPE_ARRAY);
		rankingType.setSelectedItem(FrameConstants.POST_RANKING_TYPE);
		rankingType.addActionListener(new ComboBoxListener());
		
		setComboBox(rankingType);
		
		splitPanel.add(new JPanel());
		splitPanel.add(new JLabel(FrameConstants.LABEL_RANKING_TEXT));
		splitPanel.add(rankingType);
		
		topPanel.add(this.labelImageDir);
		topPanel.add(splitPanel);
		
		return topPanel;
	}

	private Component createConentLeft() {
		JPanel leftPanel = new JPanel(new BorderLayout(5,5));
		DefaultListModel<String> listModelLeft = new DefaultListModel<String>();
		JList<String> leftList = new JList<String>(listModelLeft);
		
		leftList.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));

		JScrollPane scroll = new JScrollPane(leftList);
		
		JButton bAdd = new JButton(FrameConstants.BUTTON_ADD_TEXT);
		bAdd.addActionListener(new AddListener());
		
		JButton bRemove = new JButton(FrameConstants.BUTTON_REMOVE_TEXT);
		bRemove.addActionListener(new RemoveListener());
		
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		
		buttonPanel.add(bAdd);
		buttonPanel.add(bRemove);
		
		setmJListSubReddits(leftList);
		
		loadData();
		
		leftPanel.add(scroll, BorderLayout.CENTER);
		leftPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		return leftPanel;
	}
	
	private void loadData() {
		DefaultListModel<String> listModelLeft = (DefaultListModel<String>) this.mJListSubReddits.getModel();
		Iterator<String> data = new ListLoaderLeft();
		while (data.hasNext()) {
			listModelLeft.addElement(data.next());
		}
	}
		
	public void printToConsole(String text) {
		this.textArea.append(text + "\n");
		JScrollBar scrollBar = scroll.getVerticalScrollBar();
		scrollBar.setValue(scrollBar.getMaximum());
	}
	
	public void clearConsole() {
		this.textArea.selectAll();
		this.textArea.replaceSelection("");
	}

	public JList<String> getmJListSubReddits() {
		return mJListSubReddits;
	}

	public void setmJListSubReddits(JList<String> mJListSubReddits) {
		this.mJListSubReddits = mJListSubReddits;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public boolean isThreadStarted() {
		return threadStarted;
	}

	public void setThreadStarted(boolean threadStarted) {
		this.threadStarted = threadStarted;
	}

	public JLabel getLabelImageDir() {
		return labelImageDir;
	}

	public void setLabelImageDir(JLabel label) {
		this.labelImageDir = label;
	}
	
	public void setLabelImageDirText() {
		this.labelImageDir.setText("Image output dir: " + FrameConstants.IMAGE_DIR);
	}

	public JComboBox<String> getComboBox() {
		return this.comboBox;
	}

	public void setComboBox(JComboBox<String> comboBox) {
		this.comboBox = comboBox;
	}

}
