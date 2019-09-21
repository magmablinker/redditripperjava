package util;

import java.util.ArrayList;

import ressource.TokenTypes;

public class JSONParser {
	
	private ArrayList<String> map = new ArrayList<String>();
	private ArrayList<String> urls = new ArrayList<String>();
	private char[] data;
	
	public JSONParser(String data) {
		this.data = data.replace("\n", "").toCharArray();
	}
	
	public ArrayList<String> getMap() {
		return map;
	}
	
	public void parseJSON() {
		tokenizeJSON();
		
		for (int i = 0; i < this.map.size(); i++) {
			if(this.map.get(i).contains("https//i.")) {
				String item = this.map.get(i);
				String url = "https://" + item.substring((item.indexOf("//")+2));
				this.urls.add(url);
			}
		}
	}
	
	public void printURLS() {
		for (int i = 0; i < this.urls.size(); i++) {
			System.out.println(this.urls.get(i));
		}
	}
	
	private void tokenizeJSON() {
		int index = 0;
		int curHigh = 0;
		
		for (int i = 0; i < this.data.length; i++) {
			String dataTemp = Character.toString(this.data[i]);
			switch (dataTemp) {
			case TokenTypes.JSON_CURLY_BRACKET_LEFT:
				break;
			case TokenTypes.JSON_CURLY_BRACKET_RIGHT:
				break;
			case TokenTypes.JSON_SQUARE_BRACKET_LEFT:
				break;
			case TokenTypes.JSON_SQUARE_BRACKET_RIGHT:
				break;
			case TokenTypes.JSON_KEY_VALUE_DELIMITER:
				break;
			case TokenTypes.JSON_COMMA:
				break;
			case TokenTypes.JSON_HIGH:
				if((curHigh % 2) == 0 && curHigh != 0) {
					index++;
				}
				curHigh++;
				break;
			default:
				try {
					this.map.set(index, this.map.get(index).concat(dataTemp));
				} catch (Exception e) {
					this.map.add(dataTemp);
				}
				
				break;
			}
		}
	}
	
	public ArrayList<String> getUrls() {
		return urls;
	}
}
