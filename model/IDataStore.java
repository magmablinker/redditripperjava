package model;

import java.util.ArrayList;

import javax.swing.JList;

public interface IDataStore {

	/**
	 * load list
	 * 
	 * @param type (LEFT_LIST or RIGHT_LIST)
	 * @return ArrayList<String>
	 */
	public ArrayList<String> load();

	/**
	 * save list
	 * 
	 * @param data ArrayList<String>
	 * @param type (LEFT_LIST or RIGHT_LIST)
	 */
	public void save(JList<String> data);

}
