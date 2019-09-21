package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {

	public static String getResponseData(String aURL) throws IOException {
		StringBuilder responseData = new StringBuilder();
		try {
			URL url = new URL(aURL);
			boolean redirect = false;
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("GET");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
			
			int status = conn.getResponseCode();
			if (status != HttpURLConnection.HTTP_OK) {
				if (status == HttpURLConnection.HTTP_MOVED_TEMP
					|| status == HttpURLConnection.HTTP_MOVED_PERM
						|| status == HttpURLConnection.HTTP_SEE_OTHER)
				redirect = true;
			}
			
			if (redirect) {
				String newUrl = conn.getHeaderField("Location");

				conn = (HttpURLConnection) new URL(newUrl).openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
			}

			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String inputLine;
			while((inputLine = reader.readLine()) != null) {
				responseData.append(inputLine);
			}
			reader.close();
		} catch (Exception e) {
			
		}
		
		return responseData.toString();
	}
	
	public static InputStream getResponseData(String aURL, boolean stream) throws IOException {
		URL url = new URL(aURL);
		InputStream mStream = null;
		
		try {
			mStream = url.openStream();
		} catch (Exception e) {
			
		}
		
		return mStream;
	}
	
}
