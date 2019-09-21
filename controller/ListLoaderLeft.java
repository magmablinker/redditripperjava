package controller;

import java.util.ArrayList;
import java.util.Iterator;

import model.DataStore;
import model.IDataStore;

public class ListLoaderLeft implements Iterator<String> {

	private ArrayList<String> data;
	private int elm;

	public ListLoaderLeft() {
		this.data = new ArrayList<String>();
		this.elm = 0;

		loadData();
	}

	private void loadData() {
		IDataStore store = new DataStore();
		this.data = store.load();
	}

	@Override
	public boolean hasNext() {
		return (this.elm < data.size());
	}

	@Override
	public String next() {
		return data.get(this.elm++);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}